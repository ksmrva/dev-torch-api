package io.ksmrva.visual.torch.db.dao.model.database.component;

import io.ksmrva.visual.torch.api.arg.misc.RegexMatcher;
import io.ksmrva.visual.torch.db.dao.metadata.DatabaseMetadataDaoImpl;
import io.ksmrva.visual.torch.db.dao.metadata.pojo.sql.*;
import io.ksmrva.visual.torch.db.dao.model.database.source.DatabaseModelSourceDaoImpl;
import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.model.database.component.DbComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.path.DatabasePathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
import io.ksmrva.visual.torch.domain.entity.model.database.component.DatabaseComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.foreign.ForeignKeyComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
import io.ksmrva.visual.torch.domain.entity.model.database.source.data.DbModelSourceDataType;
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

import java.math.BigInteger;
import java.util.*;

@Repository
@Transactional
public class DatabaseModelComponentDaoImpl implements DatabaseModelComponentDao {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseModelComponentDaoImpl.class);

    public static final String UNDEFINED_CATEGORY_VALUE = "Undefined";

    private final DatabaseModelSourceDaoImpl databaseModelSourceDao;

    private final DatabaseMetadataDaoImpl databaseMetadataDao;

    private final SessionFactory sessionFactory;

    @Autowired
    public DatabaseModelComponentDaoImpl(DatabaseModelSourceDaoImpl databaseModelSourceDao, DatabaseMetadataDaoImpl databaseMetadataDao, SessionFactory sessionFactory) {
        this.databaseModelSourceDao = databaseModelSourceDao;
        this.databaseMetadataDao = databaseMetadataDao;
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create
     **/
    @Override
    public DbComponentDto createDatabase(BigInteger sourceConfigId, DatabasePathDto databasePathDto, List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        String databaseName = validateTextIsNotNullAndMinLengthOrThrow(databasePathDto.getDatabaseName(), 2, "Database Name");
        String schemaName = validateTextIsNotNullAndMinLengthOrThrow(databasePathDto.getSchemaName(), 1, "Schema Name");

        DbModelSourceConfigDto sourceDto = databaseModelSourceDao.getConfig(sourceConfigId);
        Assert.notNull(sourceDto, "Failed to find the Database Model Source Config with ID [" + sourceConfigId + "]");
        DbModelSourceConfig source = sourceDto.convertToEntity();

        DatabaseSqlMetadata databaseSqlMetadata = databaseMetadataDao.getDatabaseMetadata(sourceDto, databaseName, schemaName);
        Assert.notNull(sourceDto, "Failed to get the Database Metadata using Database Model Source Config ID [" + sourceConfigId + "], Database Name [" + databaseName + "], and Schema Name [" + schemaName + "]");

        // First create the underlying Database Component record
        DatabaseComponent database = DatabaseComponent.build(source, databaseName, schemaName);
        this.sessionFactory.getCurrentSession()
                           .persist(database);

        // After creating the underlying Database Component record, create the Table Components for all Tables in the Database
        Map<String, TableSqlMetadata> tableMetadataByName = databaseSqlMetadata.getTableMetadataByName();
        Map<String, TableComponent> createdTablesByNames = createTables(tableMetadataByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);

        // Set the created Table Components before returning the Database Component
        database.setTables(createdTablesByNames.values()
                                               .stream()
                                               .toList());

        return database.convertToDto();
    }

    /**
     * Read
     **/
    @Override
    public DbComponentDto getDatabase(DatabasePathDto databasePathDto) {
        Assert.notNull(databasePathDto, "Was provided a null Database Path");

        String databaseName = databasePathDto.getDatabaseName();
        String schemaName = databasePathDto.getSchemaName();
        Assert.notNull(databaseName, "Was provided a Database Path with a null Database Name");
        Assert.notNull(schemaName, "Was provided a Database Path with a null Schema Name");

        DatabaseComponent databaseQueryResult = null;
        try {
            databaseQueryResult = this.sessionFactory.getCurrentSession()
                                                     .createSelectionQuery("from DatabaseComponent where name=:databaseName and schemaName=:schemaName", DatabaseComponent.class)
                                                     .setParameter("databaseName", databaseName)
                                                     .setParameter("schemaName", schemaName)
                                                     .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Database Component with Database Path [" + databasePathDto + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Database Component with Database Path [" + databasePathDto + "]", e);
        }

        return DtoFactory.fromEntity(databaseQueryResult);
    }

    @Override
    public List<DbComponentDto> getAllDatabases() {
        List<DatabaseComponent> databasesQueryResult;
        try {
            databasesQueryResult = this.sessionFactory.getCurrentSession()
                                                      .createSelectionQuery("from DatabaseComponent", DatabaseComponent.class)
                                                      .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Components", e);
            databasesQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(databasesQueryResult);
    }

    @Override
    public List<TableCategoryDto> getAllTableCategories() {
        List<TableCategory> tableCategoriesQueryResult = this.getAllTableCategoryEntities();

        return DtoFactory.fromEntities(tableCategoriesQueryResult);
    }

    @Override
    public List<ColumnCategoryDto> getAllColumnCategories() {
        List<ColumnCategory> columnCategoriesQueryResult = this.getAllColumnCategoryEntities();

        return DtoFactory.fromEntities(columnCategoriesQueryResult);
    }

    /**
     * Update
     **/
    @Override
    public DbComponentDto updateDatabase(DbComponentDto databaseDtoToUpdate) {
        return null;
    }

    @Override
    public TableComponentDto updateTable(TableComponentDto tableDtoToUpdate) {
        Assert.notNull(tableDtoToUpdate, "Was provided a null Table Component to update");
        TableComponent table = DtoFactory.toEntity(tableDtoToUpdate);
        this.sessionFactory.getCurrentSession()
                           .merge(table);

        return DtoFactory.fromEntity(table);
    }

    @Override
    public ColumnComponentDto updateColumn(ColumnComponentDto columnDtoToUpdate) {
        Assert.notNull(columnDtoToUpdate, "Was provided a null Column Component to update");
        BigInteger columnIdToUpdate = columnDtoToUpdate.getId();
        if (columnIdToUpdate == null || columnIdToUpdate.compareTo(BigInteger.ZERO) < 0) {
            throw new RuntimeException("Column Component ID [" + columnIdToUpdate + "] is not valid for update");
        }

        ColumnComponent existingColumn = this.getColumnEntity(columnIdToUpdate);
        Assert.notNull(columnDtoToUpdate, "Failed to find the Column Component for update using ID [" + columnIdToUpdate + "]");

        existingColumn.setName(columnDtoToUpdate.getName());
        existingColumn.setDescription(columnDtoToUpdate.getDescription());

        DbModelSourceDataType dataType = DtoFactory.toEntity(columnDtoToUpdate.getDataType());
        existingColumn.setDataType(dataType);

        ColumnCategory columnCategory = DtoFactory.toEntity(columnDtoToUpdate.getCategory());
        existingColumn.setCategory(columnCategory);

        existingColumn.setNullable(columnDtoToUpdate.isNullable());
        existingColumn.setAutoIncrement(columnDtoToUpdate.isAutoIncrement());
        existingColumn.setColumnIndex(columnDtoToUpdate.getColumnIndex());

        this.sessionFactory.getCurrentSession()
                           .persist(existingColumn);

        return DtoFactory.fromEntity(existingColumn);
    }

    /** Delete **/

    /**
     * Private
     **/
    private ColumnCategory getUndefinedColumnCategory() {
        List<ColumnCategory> allColumnCategories = this.getAllColumnCategoryEntities();
        Optional<ColumnCategory> columnCategoryUnknownOptional = allColumnCategories.stream()
                                                                                    .filter(dbColumnCategory -> dbColumnCategory.getName()
                                                                                                                                .equalsIgnoreCase(UNDEFINED_CATEGORY_VALUE))
                                                                                    .findFirst();
        if (columnCategoryUnknownOptional.isEmpty()) {
            throw new RuntimeException("Failed to find the Column Category for value [" + UNDEFINED_CATEGORY_VALUE + "]");
        }
        return columnCategoryUnknownOptional.get();
    }

    private TableCategory getUndefinedTableCategory() {
        List<TableCategory> allTableCategories = this.getAllTableCategoryEntities();
        Optional<TableCategory> tableCategoryUnknownOptional = allTableCategories.stream()
                                                                                 .filter(dbTableCategory -> dbTableCategory.getName()
                                                                                                                           .equalsIgnoreCase(UNDEFINED_CATEGORY_VALUE))
                                                                                 .findFirst();
        if (tableCategoryUnknownOptional.isEmpty()) {
            throw new RuntimeException("Failed to find the Table Category for value [" + UNDEFINED_CATEGORY_VALUE + "]");
        }
        return tableCategoryUnknownOptional.get();
    }

    private List<TableCategory> getAllTableCategoryEntities() {
        List<TableCategory> tableCategoriesQueryResult;
        try {
            tableCategoriesQueryResult = this.sessionFactory.getCurrentSession()
                                                            .createSelectionQuery("from TableCategory", TableCategory.class)
                                                            .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Table Categories", e);
            tableCategoriesQueryResult = new ArrayList<>();
        }

        return tableCategoriesQueryResult;
    }

    private List<ColumnCategory> getAllColumnCategoryEntities() {
        List<ColumnCategory> columnCategoriesQueryResult;
        try {
            columnCategoriesQueryResult = this.sessionFactory.getCurrentSession()
                                                             .createSelectionQuery("from ColumnCategory", ColumnCategory.class)
                                                             .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Column Categories", e);
            columnCategoriesQueryResult = new ArrayList<>();
        }

        return columnCategoriesQueryResult;
    }

    private ColumnComponent getColumnEntity(BigInteger columnId) {
        ColumnComponent columnQueryResult = null;
        try {
            columnQueryResult = this.sessionFactory.getCurrentSession()
                                                   .createSelectionQuery("from ColumnComponent where id=:columnId", ColumnComponent.class)
                                                   .setParameter("columnId", columnId)
                                                   .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Column Component with ID [" + columnId + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Column Component with ID [" + columnId + "]", e);
        }
        return columnQueryResult;
    }

    private Map<String, TableComponent> createTables(Map<String, TableSqlMetadata> tableMetadataByName, DatabaseComponent database, List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        Map<String, TableComponent> createdTablesByNames = new HashMap<>();
        Collection<TableSqlMetadata> allTableMetadata = tableMetadataByName.values();
        for (TableSqlMetadata tableMetadata : allTableMetadata) {
            TableComponent table = createTable(tableMetadata, createdTablesByNames, tableMetadataByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            createdTablesByNames.put(table.getName(), table);
        }
        return createdTablesByNames;
    }

    private TableComponent createTable(TableSqlMetadata tableMetadata, Map<String, TableComponent> createdTablesByNames, Map<String, TableSqlMetadata> tableMetadataByName, DatabaseComponent database, List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        TableComponent table;
        String tableName = tableMetadata.getTableName();
        if (createdTablesByNames.containsKey(tableName)) {
            table = createdTablesByNames.get(tableName);
        } else {
            String tableDescription = tableMetadata.getTableDescription();

            table = new TableComponent();
            table.setName(tableName);
            table.setDescription(tableDescription);
            table.setDatabase(database);

            TableCategoryDto tableCategoryDto = null;
            for (RegexMatcher<TableCategoryDto> tableNameToCategoryMatcher : tableNameToCategoryMatchers) {
                if (tableNameToCategoryMatcher.doesValueMatch(tableName)) {
                    tableCategoryDto = tableNameToCategoryMatcher.getObjectForMatch();
                    break;
                }
            }

            TableCategory tableCategory;
            if (tableCategoryDto != null) {
                tableCategory = DtoFactory.toEntity(tableCategoryDto);
            } else {
                tableCategory = this.getUndefinedTableCategory();
            }
            table.setCategory(tableCategory);
            this.sessionFactory.getCurrentSession()
                               .persist(table);

            Map<String, ColumnSqlMetadata> columnSqlMetadataByName = tableMetadata.getColumnMetadataByName();
            Map<String, ColumnComponent> createdColumnsByName = this.createColumns(columnSqlMetadataByName, columnNameToCategoryMatchers);
            for (ColumnComponent column : createdColumnsByName.values()) {
                column.setTable(table);
            }
            table.setColumns(createdColumnsByName.values()
                                                 .stream()
                                                 .toList());
            for (ColumnComponent column : createdColumnsByName.values()) {
                this.sessionFactory.getCurrentSession()
                                   .persist(column);
            }

            PrimaryKeySqlMetadata primaryKeySqlMetadata = tableMetadata.getPrimaryKeySqlMetadata();
            PrimaryKeyComponent primaryKey = this.createPrimaryKey(primaryKeySqlMetadata, createdColumnsByName);
            primaryKey.setTable(table);
            table.setPrimaryKey(primaryKey);
            this.sessionFactory.getCurrentSession()
                               .persist(primaryKey);

            List<ForeignKeySqlMetadata> allForeignKeySqlMetadata = tableMetadata.getForeignKeySqlMetadataList();
            List<ForeignKeyComponent> foreignKeys = createForeignKeys(allForeignKeySqlMetadata, tableMetadataByName, createdTablesByNames, createdColumnsByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            table.setForeignKeys(foreignKeys);
            for (ForeignKeyComponent foreignKey : foreignKeys) {
                foreignKey.setTable(table);
                this.sessionFactory.getCurrentSession()
                                   .persist(foreignKey);
            }
        }
        return table;
    }

    private Map<String, ColumnComponent> createColumns(Map<String, ColumnSqlMetadata> columnSqlMetadataByName, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        Map<String, ColumnComponent> columnsByName = new HashMap<>();
        for (Map.Entry<String, ColumnSqlMetadata> columnSqlMetadataByNameEntry : columnSqlMetadataByName.entrySet()) {
            ColumnComponent column = createColumn(columnSqlMetadataByNameEntry.getValue(), columnNameToCategoryMatchers);
            columnsByName.put(column.getName(), column);
        }
        return columnsByName;
    }

    private ColumnComponent createColumn(ColumnSqlMetadata columnSqlMetadata, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        String columnName = columnSqlMetadata.getColumnName();
        String columnDescription = columnSqlMetadata.getColumnDescription();

        ColumnCategoryDto columnCategoryDto = null;
        for (RegexMatcher<ColumnCategoryDto> columnNameToCategoryMatcher : columnNameToCategoryMatchers) {
            if (columnNameToCategoryMatcher.doesValueMatch(columnName)) {
                columnCategoryDto = columnNameToCategoryMatcher.getObjectForMatch();
                break;
            }
        }

        ColumnCategory columnCategory;
        if (columnCategoryDto != null) {
            columnCategory = DtoFactory.toEntity(columnCategoryDto);
        } else {
            columnCategory = this.getUndefinedColumnCategory();
        }

        int sqlDataTypeValue = columnSqlMetadata.getSqlDataTypeValue();
        List<DbModelSourceDataTypeDto> matchingDataTypeDtos = databaseModelSourceDao.getAllDataTypes()
                                                                                    .stream()
                                                                                    .filter(dataType -> dataType.getJavaSqlConstant()
                                                                                                                .equals(sqlDataTypeValue))
                                                                                    .toList();
        if (matchingDataTypeDtos.size() != 1) {
            throw new RuntimeException("Expected to match exactly one Database Model Source Data Types to the SQL Data Type value [" + sqlDataTypeValue + "] but found [" + matchingDataTypeDtos.size() + "] instead");
        }
        DbModelSourceDataType dataType = matchingDataTypeDtos.getFirst()
                                                             .convertToEntity();
        boolean isNullable = columnSqlMetadata.getIsNullable();
        boolean isAutoIncrement = columnSqlMetadata.getIsAutoIncrement();
        int columnIndex = columnSqlMetadata.getOrdinalPosition();

        return ColumnComponent.build(columnName, columnDescription, dataType, columnCategory, isNullable, isAutoIncrement, columnIndex);
    }

    private PrimaryKeyComponent createPrimaryKey(PrimaryKeySqlMetadata primaryKeySqlMetadata, Map<String, ColumnComponent> columnsByName) {
        String primaryKeyName = primaryKeySqlMetadata.getName();
        PrimaryKeyComponent primaryKey = PrimaryKeyComponent.build(primaryKeyName);

        List<PrimaryKeyColumnComponent> primaryKeyColumns = new ArrayList<>();
        for (String columnName : primaryKeySqlMetadata.getColumnNames()) {
            ColumnComponent column = columnsByName.get(columnName);

            PrimaryKeyColumnComponent primaryKeyColumn = this.createPrimaryKeyColumn();
            primaryKeyColumn.setPrimaryKey(primaryKey);
            primaryKeyColumn.setColumn(column);

            primaryKeyColumns.add(primaryKeyColumn);
        }

        primaryKey.setColumns(primaryKeyColumns);
        return primaryKey;
    }

    private PrimaryKeyColumnComponent createPrimaryKeyColumn() {
        return new PrimaryKeyColumnComponent();
    }

    private List<ForeignKeyComponent> createForeignKeys(List<ForeignKeySqlMetadata> allForeignKeySqlMetadata, Map<String, TableSqlMetadata> tableMetadataByName, Map<String, TableComponent> createdTablesByNames, Map<String, ColumnComponent> createdColumnsByName, DatabaseComponent database, List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        List<ForeignKeyComponent> foreignKeys = new ArrayList<>();
        for (ForeignKeySqlMetadata foreignKeySqlMetadata : allForeignKeySqlMetadata) {
            ForeignKeyComponent foreignKey = this.createForeignKeyComponent(foreignKeySqlMetadata, tableMetadataByName, createdTablesByNames, createdColumnsByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            foreignKeys.add(foreignKey);
        }
        return foreignKeys;
    }

    private ForeignKeyComponent createForeignKeyComponent(ForeignKeySqlMetadata foreignKeySqlMetadata, Map<String, TableSqlMetadata> tableMetadataByName, Map<String, TableComponent> createdTablesByNames, Map<String, ColumnComponent> createdColumnsByName, DatabaseComponent database, List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        String foreignKeyName = foreignKeySqlMetadata.getForeignKeyName();
        String localColumnName = foreignKeySqlMetadata.getLocalColumnName();
        String referencedTableName = foreignKeySqlMetadata.getReferencedTableName();
        String referencedColumnName = foreignKeySqlMetadata.getReferencedColumnName();

        ColumnComponent localColumn = createdColumnsByName.get(localColumnName);
        if (localColumn == null) {
            throw new RuntimeException("Failed to find the Column Component using Name [" + localColumnName + "]");
        }

        TableComponent referencedTable = createdTablesByNames.get(referencedTableName);
        if (referencedTable == null) {
            if (createdTablesByNames.containsKey(referencedTableName)) {
                throw new RuntimeException("Table with Name [" + referencedTableName + "] is registered as created but it wasn't found within the created Tables Map");
            }

            TableSqlMetadata referencedTableSqlMetadata = tableMetadataByName.get(referencedTableName);
            referencedTable = createTable(referencedTableSqlMetadata, createdTablesByNames, tableMetadataByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            createdTablesByNames.put(referencedTable.getName(), referencedTable);
        }
        referencedTable = createdTablesByNames.get(referencedTableName);
        ColumnComponent referencedColumn = referencedTable.getColumns()
                                                          .stream()
                                                          .filter(column -> column.getName()
                                                                                  .equalsIgnoreCase(referencedColumnName))
                                                          .findFirst()
                                                          .orElse(null);
        if (referencedColumn == null) {
            throw new RuntimeException("Failed to find the Referenced Column Component using Name [" + localColumnName + "]");
        }

        ForeignKeyComponent foreignKey = ForeignKeyComponent.build(foreignKeyName);
        foreignKey.setLocalColumn(localColumn);
        foreignKey.setReferencedTable(referencedTable);
        foreignKey.setReferencedColumn(referencedColumn);

        return foreignKey;
    }

    private String validateTextIsNotNullAndMinLengthOrThrow(String text, int minimumLength, String textLogName) {
        if (StringUtils.isEmpty(text)) {
            throw new IllegalArgumentException(textLogName + " can not be blank");
        } else if (text.length() < minimumLength) {
            throw new IllegalArgumentException(textLogName + " must be at least " + minimumLength + " characters in length; [" + text + "] is invalid");
        }
        return text;
    }

}
