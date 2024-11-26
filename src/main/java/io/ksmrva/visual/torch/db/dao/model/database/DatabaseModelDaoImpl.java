package io.ksmrva.visual.torch.db.dao.model.database;

import io.ksmrva.visual.torch.api.args.misc.RegexMatcher;
import io.ksmrva.visual.torch.db.dao.meta.helper.sql.*;
import io.ksmrva.visual.torch.db.dao.model.database.source.DatabaseModelSourceDaoImpl;
import io.ksmrva.visual.torch.db.dao.meta.DatabaseMetadataDaoImpl;
import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.model.database.DbModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.data.DbDataTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.database.name.DbModelNameDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import io.ksmrva.visual.torch.domain.entity.model.database.DbModel;
import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnModel;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.foreign.DbForeignKeyModel;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary.DbPrimaryKeyColumnModel;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary.DbPrimaryKeyModel;
import io.ksmrva.visual.torch.domain.entity.model.database.data.DbDataType;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Types;
import java.util.*;

@Repository
@Transactional
public class DatabaseModelDaoImpl implements DatabaseModelDao {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseModelDaoImpl.class);

    public static final String UNDEFINED_CATEGORY_VALUE = "Undefined";

    private final DatabaseModelSourceDaoImpl databaseModelSourceDao;

    private final DatabaseMetadataDaoImpl databaseMetadataDao;

    private final SessionFactory sessionFactory;

    @Autowired
    public DatabaseModelDaoImpl(DatabaseModelSourceDaoImpl databaseModelSourceDao, DatabaseMetadataDaoImpl databaseMetadataDao, SessionFactory sessionFactory) {
        this.databaseModelSourceDao = databaseModelSourceDao;
        this.databaseMetadataDao = databaseMetadataDao;
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create
     **/
    @Override
    public DbModelDto createDbModel(BigInteger sourceConfigId, DbModelNameDto dbModelNameDto, List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        String databaseName = validateTextIsNotNullAndMinLengthOrThrow(dbModelNameDto.getDatabaseName(), 2, "Database Name");
        String schemaName = validateTextIsNotNullAndMinLengthOrThrow(dbModelNameDto.getSchemaName(), 1, "Schema Name");

        DbModelSourceConfigDto sourceDto = databaseModelSourceDao.getSourceConfig(sourceConfigId);
        Assert.notNull(sourceDto, "Failed to find the Database Model Source with ID [" + sourceConfigId + "]");
        DbModelSourceConfig source = sourceDto.convertToEntity();

        DatabaseSqlMetadata databaseSqlMetadata = databaseMetadataDao.getDatabaseMetadata(sourceDto, databaseName, schemaName);
        Assert.notNull(sourceDto, "Failed to get the Database Metadata using Database Model Source ID [" + sourceConfigId + "], database name [" + databaseName + "], and schema name [" + schemaName + "]");

        // First create the underlying Database Model record
        DbModel dbModel = DbModel.build(source, databaseName, schemaName);
        this.sessionFactory.getCurrentSession()
                           .persist(dbModel);

        // After creating the underlying Database Model record, create the Table records for all tables in the database
        Map<String, TableSqlMetadata> tableMetadataByName = databaseSqlMetadata.getTableMetadataByName();
        Map<String, DbTableModel> createdTableModelsByNames = createTableModels(tableMetadataByName, dbModel, tableNameToCategoryMatchers, columnNameToCategoryMatchers);

        // Set the created Table Models before returning the Database Model
        dbModel.setTables(createdTableModelsByNames.values()
                                                   .stream()
                                                   .toList());

        return dbModel.convertToDto();
    }

    @Override
    public List<DbDataTypeDto> createAllDataTypes() {
        List<DbDataType> dataTypes = DatabaseModelDaoImpl.createDataTypesFromJavaSqlTypes();
        LOGGER.info("Creating [" + dataTypes.size() + "] Data Types");

        List<DbDataTypeDto> dataTypeDtosToReturn = new ArrayList<>();
        for (DbDataType dataType : dataTypes) {
            this.sessionFactory.getCurrentSession()
                               .persist(dataType);

            DbDataTypeDto dataTypeDto = dataType.convertToDto();
            dataTypeDtosToReturn.add(dataTypeDto);
        }
        return dataTypeDtosToReturn;
    }

    /**
     * Read
     **/
    @Override
    public DbModelDto getDbModel(DbModelNameDto dbModelNameDto) {
        Assert.notNull(dbModelNameDto, "Was provided a null Database Model Name");

        String databaseName = dbModelNameDto.getDatabaseName();
        String schemaName = dbModelNameDto.getSchemaName();
        Assert.notNull(databaseName, "Was provided a Database Model Name with a null database name");
        Assert.notNull(schemaName, "Was provided a Database Model Name with a null schema name");

        DbModel dbModelQueryResult = null;
        try {
            dbModelQueryResult = this.sessionFactory.getCurrentSession()
                                                    .createSelectionQuery("from DbModel where databaseName=:databaseName and schemaName=:schemaName", DbModel.class)
                                                    .setParameter("databaseName", databaseName)
                                                    .setParameter("schemaName", schemaName)
                                                    .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Database Model with database name [" + databaseName + "] and schema name [" + schemaName + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Database Model with database name [" + databaseName + "] and schema name [" + schemaName + "]", e);
        }

        return DtoFactory.fromEntity(dbModelQueryResult);
    }

    @Override
    public List<DbModelDto> getAllDbModels() {
        List<DbModel> dbModelsQueryResult;
        try {
            dbModelsQueryResult = this.sessionFactory.getCurrentSession()
                                                     .createSelectionQuery("from DbModel", DbModel.class)
                                                     .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Models", e);
            dbModelsQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(dbModelsQueryResult);
    }

    @Override
    public List<DbDataTypeDto> getOrCreateAllDataTypes() {
        List<DbDataTypeDto> allDataTypes = this.getAllDataTypes();
        if (CollectionUtils.isEmpty(allDataTypes)) {
            LOGGER.warn("Found no Data Types, attempting to create them");
            allDataTypes = this.createAllDataTypes();
        }

        if (CollectionUtils.isEmpty(allDataTypes)) {
            throw new RuntimeException("Failed to create any Data Types");
        }
        return allDataTypes;
    }

    @Override
    public List<DbDataTypeDto> getAllDataTypes() {
        List<DbDataType> dataTypesQueryResult = this.getAllDataTypeEntities();

        return DtoFactory.fromEntities(dataTypesQueryResult);
    }

    @Override
    public List<DbTableCategoryDto> getAllTableCategories() {
        List<DbTableCategory> tableCategoriesQueryResult = this.getAllTableCategoryEntities();

        return DtoFactory.fromEntities(tableCategoriesQueryResult);
    }

    @Override
    public List<DbColumnCategoryDto> getAllColumnCategories() {
        List<DbColumnCategory> columnCategoriesQueryResult = this.getAllColumnCategoryEntities();

        return DtoFactory.fromEntities(columnCategoriesQueryResult);
    }

    /**
     * Update
     **/
    @Override
    public DbModelDto updateDbModel(DbModelDto databaseModeToUpdate) {
        return null;
    }

    @Override
    public DbTableModelDto updateTableModel(DbTableModelDto tableDtoToUpdate) {
        Assert.notNull(tableDtoToUpdate, "Was provided a null Table Model to update");
        DbTableModel dbTableModel = DtoFactory.toEntity(tableDtoToUpdate);
        this.sessionFactory.getCurrentSession()
                           .merge(dbTableModel);

        return DtoFactory.fromEntity(dbTableModel);
    }

    @Override
    public DbColumnModelDto updateColumnModel(DbColumnModelDto columnDtoToUpdate) {
        Assert.notNull(columnDtoToUpdate, "Was provided a null Column Model to update");
        BigInteger columnModelIdToUpdate = columnDtoToUpdate.getId();
        if (columnModelIdToUpdate == null || columnModelIdToUpdate.compareTo(BigInteger.ZERO) < 0) {
            throw new RuntimeException("Column Model Id [" + columnModelIdToUpdate + "] is not valid for update");
        }

        DbColumnModel existingDbColumnModel = this.getColumnModelEntity(columnModelIdToUpdate);
        Assert.notNull(columnDtoToUpdate, "Failed to find the Column Model for update using ID [" + columnModelIdToUpdate + "]");

        existingDbColumnModel.setName(columnDtoToUpdate.getName());
        existingDbColumnModel.setDescription(columnDtoToUpdate.getDescription());

        DbDataType dataType = DtoFactory.toEntity(columnDtoToUpdate.getDataType());
        existingDbColumnModel.setDataType(dataType);

        DbColumnCategory dbColumnCategory = DtoFactory.toEntity(columnDtoToUpdate.getColumnCategory());
        existingDbColumnModel.setColumnCategory(dbColumnCategory);

        existingDbColumnModel.setNullable(columnDtoToUpdate.isNullable());
        existingDbColumnModel.setAutoIncrement(columnDtoToUpdate.isAutoIncrement());
        existingDbColumnModel.setColumnIndex(columnDtoToUpdate.getColumnIndex());

        this.sessionFactory.getCurrentSession()
                           .persist(existingDbColumnModel);

        return DtoFactory.fromEntity(existingDbColumnModel);
    }

    /** Delete **/

    /**
     * Private
     **/
    private DbColumnCategory getUndefinedColumnCategory() {
        List<DbColumnCategory> allColumnCategories = this.getAllColumnCategoryEntities();
        Optional<DbColumnCategory> columnCategoryUnknownOptional = allColumnCategories.stream()
                                                                                      .filter(dbColumnCategory -> dbColumnCategory.getName()
                                                                                                                                  .equalsIgnoreCase(UNDEFINED_CATEGORY_VALUE))
                                                                                      .findFirst();
        if (columnCategoryUnknownOptional.isEmpty()) {
            throw new RuntimeException("Failed to find the Column Category for value [" + UNDEFINED_CATEGORY_VALUE + "]");
        }
        return columnCategoryUnknownOptional.get();
    }

    private DbTableCategory getUndefinedTableCategory() {
        List<DbTableCategory> allTableCategories = this.getAllTableCategoryEntities();
        Optional<DbTableCategory> tableCategoryUnknownOptional = allTableCategories.stream()
                                                                                   .filter(dbTableCategory -> dbTableCategory.getName()
                                                                                                                             .equalsIgnoreCase(UNDEFINED_CATEGORY_VALUE))
                                                                                   .findFirst();
        if (tableCategoryUnknownOptional.isEmpty()) {
            throw new RuntimeException("Failed to find the Table Category representing [" + UNDEFINED_CATEGORY_VALUE + "]");
        }
        return tableCategoryUnknownOptional.get();
    }

    private List<DbTableCategory> getAllTableCategoryEntities() {
        List<DbTableCategory> tableCategoriesQueryResult;
        try {
            tableCategoriesQueryResult = this.sessionFactory.getCurrentSession()
                                                            .createSelectionQuery("from DbTableCategory", DbTableCategory.class)
                                                            .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Table Categories", e);
            tableCategoriesQueryResult = new ArrayList<>();
        }

        return tableCategoriesQueryResult;
    }

    private List<DbColumnCategory> getAllColumnCategoryEntities() {
        List<DbColumnCategory> columnCategoriesQueryResult;
        try {
            columnCategoriesQueryResult = this.sessionFactory.getCurrentSession()
                                                             .createSelectionQuery("from DbColumnCategory", DbColumnCategory.class)
                                                             .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Column Categories", e);
            columnCategoriesQueryResult = new ArrayList<>();
        }

        return columnCategoriesQueryResult;
    }

    private List<DbDataType> getAllDataTypeEntities() {
        List<DbDataType> databaseModelsQueryResultDataType;
        try {
            databaseModelsQueryResultDataType = this.sessionFactory.getCurrentSession()
                                                                   .createSelectionQuery("from DbDataType", DbDataType.class)
                                                                   .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Data Types", e);
            databaseModelsQueryResultDataType = new ArrayList<>();
        }

        return databaseModelsQueryResultDataType;
    }

    private DbModel getDatabaseModelEntity(BigInteger databaseModelId) {
        DbModel dbModelQueryResult = null;
        try {
            dbModelQueryResult = this.sessionFactory.getCurrentSession()
                                                    .createSelectionQuery("from DbModel where id=:databaseModelId", DbModel.class)
                                                    .setParameter("databaseModelId", databaseModelId)
                                                    .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Database Model with id [" + databaseModelId + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Database Model with id [" + databaseModelId + "]", e);
        }
        return dbModelQueryResult;
    }

    public DbTableModel getTableModelEntity(BigInteger tableModelId) {
        DbTableModel dbTableModelQueryResult = null;
        try {
            dbTableModelQueryResult = this.sessionFactory.getCurrentSession()
                                                         .createSelectionQuery("from DbTableModel where id=:tableModelId", DbTableModel.class)
                                                         .setParameter("tableModelId", tableModelId)
                                                         .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Table Model with id [" + tableModelId + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Table Model with id [" + tableModelId + "]", e);
        }
        return dbTableModelQueryResult;
    }

    public DbColumnModel getColumnModelEntity(BigInteger columnModelId) {
        DbColumnModel dbColumnModelQueryResult = null;
        try {
            dbColumnModelQueryResult = this.sessionFactory.getCurrentSession()
                                                          .createSelectionQuery("from DbColumnModel where id=:columnModelId", DbColumnModel.class)
                                                          .setParameter("columnModelId", columnModelId)
                                                          .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Column Model with id [" + columnModelId + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Column Model with id [" + columnModelId + "]", e);
        }
        return dbColumnModelQueryResult;
    }

    private Map<String, DbTableModel> createTableModels(Map<String, TableSqlMetadata> tableMetadataByName, DbModel dbModel, List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        Map<String, DbTableModel> createdTableModelsByNames = new HashMap<>();
        Collection<TableSqlMetadata> allTableMetadata = tableMetadataByName.values();
        for (TableSqlMetadata tableMetadata : allTableMetadata) {
            DbTableModel dbTableModel = createTableModel(tableMetadata, createdTableModelsByNames, tableMetadataByName, dbModel, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            createdTableModelsByNames.put(dbTableModel.getName(), dbTableModel);
        }
        return createdTableModelsByNames;
    }

    private DbTableModel createTableModel(TableSqlMetadata tableMetadata, Map<String, DbTableModel> createdTableModelsByNames, Map<String, TableSqlMetadata> tableMetadataByName, DbModel dbModel, List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        DbTableModel tableModel;
        String tableName = tableMetadata.getTableName();
        if (createdTableModelsByNames.containsKey(tableName)) {
            tableModel = createdTableModelsByNames.get(tableName);
        } else {
            String tableDescription = tableMetadata.getTableDescription();

            tableModel = new DbTableModel();
            tableModel.setName(tableName);
            tableModel.setDescription(tableDescription);
            tableModel.setDatabase(dbModel);

            DbTableCategoryDto tableCategoryDto = null;
            for (RegexMatcher<DbTableCategoryDto> tableNameToCategoryMatcher : tableNameToCategoryMatchers) {
                if (tableNameToCategoryMatcher.doesValueMatch(tableName)) {
                    tableCategoryDto = tableNameToCategoryMatcher.getObjectForMatch();
                    break;
                }
            }

            DbTableCategory tableCategory;
            if (tableCategoryDto != null) {
                tableCategory = DtoFactory.toEntity(tableCategoryDto);
            } else {
                tableCategory = this.getUndefinedTableCategory();
            }
            tableModel.setTableCategory(tableCategory);

            this.sessionFactory.getCurrentSession()
                               .persist(tableModel);

            Map<String, ColumnSqlMetadata> columnSqlMetadataByName = tableMetadata.getColumnMetadataByName();
            Map<String, DbColumnModel> createdColumnModelsByName = this.createColumnModels(columnSqlMetadataByName, columnNameToCategoryMatchers);
            for (DbColumnModel columnModel : createdColumnModelsByName.values()) {
                columnModel.setTable(tableModel);
            }
            tableModel.setColumns(createdColumnModelsByName.values()
                                                           .stream()
                                                           .toList());
            for (DbColumnModel columnModel : createdColumnModelsByName.values()) {
                this.sessionFactory.getCurrentSession()
                                   .persist(columnModel);
            }

            PrimaryKeySqlMetadata primaryKeySqlMetadata = tableMetadata.getPrimaryKeySqlMetadata();
            DbPrimaryKeyModel primaryKeyModel = this.createPrimaryKeyModel(primaryKeySqlMetadata, createdColumnModelsByName);
            primaryKeyModel.setTableModel(tableModel);
            tableModel.setPrimaryKey(primaryKeyModel);
            this.sessionFactory.getCurrentSession()
                               .persist(primaryKeyModel);

            List<ForeignKeySqlMetadata> allForeignKeySqlMetadata = tableMetadata.getForeignKeySqlMetadataList();
            List<DbForeignKeyModel> foreignKeyModels = createForeignKeyModels(allForeignKeySqlMetadata, tableMetadataByName, createdTableModelsByNames, createdColumnModelsByName, dbModel, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            tableModel.setForeignKeys(foreignKeyModels);
            for (DbForeignKeyModel foreignKeyModel : foreignKeyModels) {
                foreignKeyModel.setTable(tableModel);
                this.sessionFactory.getCurrentSession()
                                   .persist(foreignKeyModel);
            }
        }
        return tableModel;
    }

    private Map<String, DbColumnModel> createColumnModels(Map<String, ColumnSqlMetadata> columnSqlMetadataByName, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        Map<String, DbColumnModel> columnModelsByName = new HashMap<>();
        for (Map.Entry<String, ColumnSqlMetadata> columnSqlMetadataByNameEntry : columnSqlMetadataByName.entrySet()) {
            DbColumnModel dbColumnModel = createColumnModel(columnSqlMetadataByNameEntry.getValue(), columnNameToCategoryMatchers);
            columnModelsByName.put(dbColumnModel.getName(), dbColumnModel);
        }
        return columnModelsByName;
    }

    private DbColumnModel createColumnModel(ColumnSqlMetadata columnSqlMetadata, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        String columnName = columnSqlMetadata.getColumnName();
        String columnDescription = columnSqlMetadata.getColumnDescription();

        DbColumnCategoryDto columnCategoryDto = null;
        for (RegexMatcher<DbColumnCategoryDto> columnNameToCategoryMatcher : columnNameToCategoryMatchers) {
            if (columnNameToCategoryMatcher.doesValueMatch(columnName)) {
                columnCategoryDto = columnNameToCategoryMatcher.getObjectForMatch();
                break;
            }
        }

        DbColumnCategory columnCategory;
        if (columnCategoryDto != null) {
            columnCategory = DtoFactory.toEntity(columnCategoryDto);
        } else {
            columnCategory = this.getUndefinedColumnCategory();
        }

        int sqlDataTypeValue = columnSqlMetadata.getSqlDataTypeValue();
        List<DbDataType> matchingEntities = this.getAllDataTypeEntities()
                                                .stream()
                                                .filter(dbDataType -> dbDataType.getJavaSqlConstant()
                                                                                .equals(sqlDataTypeValue))
                                                .toList();
        if (matchingEntities.size() != 1) {
            throw new RuntimeException("Found either too many or too few entities for SQL Data Type Value [" + sqlDataTypeValue + "]; expected 1 result, got [" + matchingEntities.size() + "]");
        }
        DbDataType dbDataType = matchingEntities.getFirst();
        boolean isNullable = columnSqlMetadata.getIsNullable();
        boolean isAutoIncrement = columnSqlMetadata.getIsAutoIncrement();
        int columnIndex = columnSqlMetadata.getOrdinalPosition();

        return DbColumnModel.build(columnName, columnDescription, dbDataType, columnCategory, isNullable, isAutoIncrement, columnIndex);
    }

    private DbPrimaryKeyModel createPrimaryKeyModel(PrimaryKeySqlMetadata primaryKeySqlMetadata, Map<String, DbColumnModel> columnModelsByName) {
        String primaryKeyName = primaryKeySqlMetadata.getName();
        DbPrimaryKeyModel primaryKeyModel = DbPrimaryKeyModel.build(primaryKeyName);

        List<DbPrimaryKeyColumnModel> primaryKeyColumnModels = new ArrayList<>();
        for (String columnName : primaryKeySqlMetadata.getColumnNames()) {
            DbColumnModel dbColumnModel = columnModelsByName.get(columnName);

            DbPrimaryKeyColumnModel primaryKeyColumnModel = this.createPrimaryKeyModelColumn();
            primaryKeyColumnModel.setPrimaryKey(primaryKeyModel);
            primaryKeyColumnModel.setColumn(dbColumnModel);

            primaryKeyColumnModels.add(primaryKeyColumnModel);
        }

        primaryKeyModel.setPrimaryKeyColumns(primaryKeyColumnModels);
        return primaryKeyModel;
    }

    private DbPrimaryKeyColumnModel createPrimaryKeyModelColumn() {
        return new DbPrimaryKeyColumnModel();
    }

    private List<DbForeignKeyModel> createForeignKeyModels(List<ForeignKeySqlMetadata> allForeignKeySqlMetadata, Map<String, TableSqlMetadata> tableMetadataByName, Map<String, DbTableModel> createdTableModelsByNames, Map<String, DbColumnModel> createdColumnModelsByName, DbModel dbModel, List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        List<DbForeignKeyModel> foreignKeyModels = new ArrayList<>();
        for (ForeignKeySqlMetadata foreignKeySqlMetadata : allForeignKeySqlMetadata) {
            DbForeignKeyModel foreignKeyModel = this.createForeignKeyModel(foreignKeySqlMetadata, tableMetadataByName, createdTableModelsByNames, createdColumnModelsByName, dbModel, tableNameToCategoryMatchers, columnNameToCategoryMatchers);

            foreignKeyModels.add(foreignKeyModel);
        }
        return foreignKeyModels;
    }

    private DbForeignKeyModel createForeignKeyModel(ForeignKeySqlMetadata foreignKeySqlMetadata, Map<String, TableSqlMetadata> tableMetadataByName, Map<String, DbTableModel> createdTableModelsByNames, Map<String, DbColumnModel> createdColumnModelsByName, DbModel dbModel, List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        String foreignKeyName = foreignKeySqlMetadata.getForeignKeyName();
        String localColumnName = foreignKeySqlMetadata.getLocalColumnName();
        String referencedTableName = foreignKeySqlMetadata.getReferencedTableName();
        String referencedColumnName = foreignKeySqlMetadata.getReferencedColumnName();

        DbColumnModel localDbColumnModel = createdColumnModelsByName.get(localColumnName);
        if (localDbColumnModel == null) {
            throw new RuntimeException("Failed to find the column model by the name [" + localColumnName + "]");
        }

        DbTableModel referencedTableModel = createdTableModelsByNames.get(referencedTableName);
        if (referencedTableModel == null) {
            if (createdTableModelsByNames.containsKey(referencedTableName)) {
                throw new RuntimeException("Table with name [" + referencedTableName + "] is registered as created but it wasn't found within the Table Map");
            }

            TableSqlMetadata referencedTableSqlMetadata = tableMetadataByName.get(referencedTableName);
            referencedTableModel = createTableModel(referencedTableSqlMetadata, createdTableModelsByNames, tableMetadataByName, dbModel, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            createdTableModelsByNames.put(referencedTableModel.getName(), referencedTableModel);
        }
        referencedTableModel = createdTableModelsByNames.get(referencedTableName);
        DbColumnModel referencedDbColumnModel = referencedTableModel.getColumns()
                                                                    .stream()
                                                                    .filter(columnModel -> columnModel.getName()
                                                                                                      .equalsIgnoreCase(referencedColumnName))
                                                                    .findFirst()
                                                                    .orElse(null);
        if (referencedDbColumnModel == null) {
            throw new RuntimeException("Failed to find the referenced column model by the name [" + localColumnName + "]");
        }

        DbForeignKeyModel dbForeignKeyModel = DbForeignKeyModel.build(foreignKeyName);
        dbForeignKeyModel.setLocalColumn(localDbColumnModel);
        dbForeignKeyModel.setReferencedTable(referencedTableModel);
        dbForeignKeyModel.setReferencedColumn(referencedDbColumnModel);

        return dbForeignKeyModel;
    }

    private String validateTextIsNotNullAndMinLengthOrThrow(String text, int minimumLength, String textLogName) {
        if (StringUtils.isEmpty(text)) {
            throw new IllegalArgumentException(textLogName + " can not be blank");
        } else if (text.length() < minimumLength) {
            throw new IllegalArgumentException(textLogName + " must be at least " + minimumLength + " characters in length; [" + text + "] is invalid");
        }
        return text;
    }

    private static List<DbDataType> createDataTypesFromJavaSqlTypes() {
        List<DbDataType> dataTypes = new ArrayList<>();
        Class<?> javaSqlTypesClazz = Types.class;
        List<Field> staticIntFields = DatabaseModelDaoImpl.getAllStaticIntFieldsFromJavaClazz(javaSqlTypesClazz);

        for (Field field : staticIntFields) {
            String fieldName = field.getName();
            Object fieldValue;
            try {
                // Since we are getting the static value we do not need an instance to pass into the get() method and can just pass null
                fieldValue = field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to find the value for static Field with name [" + fieldName + "] for Class [" + javaSqlTypesClazz + "] due to [" + e.getLocalizedMessage() + "]", e);
            }

            Class<?> fieldValueType = fieldValue.getClass();
            boolean isFieldValueTypeInteger = DatabaseModelDaoImpl.isTypeInteger(fieldValueType);
            if (!isFieldValueTypeInteger) {
                throw new RuntimeException("Found a non-Integer value type [" + fieldValueType + "] for static Field with name [" + fieldName + "] for Class [" + javaSqlTypesClazz + "]");
            }

            LOGGER.info("Found static int Field with name [" + field.getName() + "] and value [" + fieldValue + "] for Class [" + javaSqlTypesClazz + "]");
            DbDataType dataType = new DbDataType();
            dataType.setName(fieldName);
            dataType.setJavaSqlConstant((Integer) fieldValue);

            dataTypes.add(dataType);
        }
        return dataTypes;
    }

    private static List<Field> getAllStaticIntFieldsFromJavaClazz(Class<?> clazz) {
        List<Field> staticIntFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            boolean isStatic = java.lang.reflect.Modifier.isStatic(field.getModifiers());
            boolean isPublic = java.lang.reflect.Modifier.isPublic(field.getModifiers());
            boolean isTypeInteger = DatabaseModelDaoImpl.isTypeInteger(field.getType());
            if (isStatic && isPublic && isTypeInteger) {
                staticIntFields.add(field);
            }
        }
        return staticIntFields;
    }

    private static boolean isTypeInteger(Class<?> type) {
        return type.equals(Integer.TYPE) || type.equals(Integer.class);
    }

}
