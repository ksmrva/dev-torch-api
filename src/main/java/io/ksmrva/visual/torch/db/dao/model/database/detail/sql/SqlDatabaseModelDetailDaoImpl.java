package io.ksmrva.visual.torch.db.dao.model.database.detail.sql;

import io.ksmrva.visual.torch.api.arg.misc.RegexMatcher;
import io.ksmrva.visual.torch.db.dao.model.database.detail.DatabaseModelDetailDao;
import io.ksmrva.visual.torch.db.dao.model.database.metadata.sql.SqlDatabaseMetadataDaoImpl;
import io.ksmrva.visual.torch.db.dao.model.database.metadata.sql.pojo.*;
import io.ksmrva.visual.torch.db.dao.model.database.source.DatabaseModelSourceDaoImpl;
import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.SqlDatabaseDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.path.SqlDatabaseDetailPathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.category.collection.CollectionCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.category.field.FieldCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.SqlDatabaseDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column.SqlColumnDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.foreign.SqlForeignKeyDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyColumnDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
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
public class SqlDatabaseModelDetailDaoImpl implements SqlDatabaseModelDetailDao {

    private static final Logger LOGGER = LogManager.getLogger(SqlDatabaseModelDetailDaoImpl.class);

    private final DatabaseModelDetailDao databaseModelDetailDao;

    private final DatabaseModelSourceDaoImpl databaseModelSourceDao;

    private final SqlDatabaseMetadataDaoImpl sqlDatabaseMetadataDao;

    private final SessionFactory sessionFactory;

    @Autowired
    public SqlDatabaseModelDetailDaoImpl(DatabaseModelDetailDao databaseModelDetailDao, DatabaseModelSourceDaoImpl databaseModelSourceDao, SqlDatabaseMetadataDaoImpl sqlDatabaseMetadataDao, SessionFactory sessionFactory) {
        this.databaseModelDetailDao = databaseModelDetailDao;
        this.databaseModelSourceDao = databaseModelSourceDao;
        this.sqlDatabaseMetadataDao = sqlDatabaseMetadataDao;
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create
     **/
    @Override
    public SqlDatabaseDetailDto createDatabase(BigInteger sourceConfigId, SqlDatabaseDetailPathDto sqlDatabaseDetailPathDto, List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        String databaseName = validateTextIsNotNullAndMinLengthOrThrow(sqlDatabaseDetailPathDto.getDatabaseName(), 2, "Database Name");
        String schemaName = validateTextIsNotNullAndMinLengthOrThrow(sqlDatabaseDetailPathDto.getSchemaName(), 1, "Schema Name");

        DbModelSourceConfigDto sourceDto = databaseModelSourceDao.getConfig(sourceConfigId);
        Assert.notNull(sourceDto, "Failed to find the SQL Database Model Source Config with ID [" + sourceConfigId + "]");
        DbModelSourceConfig source = sourceDto.convertToEntity();

        SqlDatabaseMetadata sqlDatabaseMetadata = sqlDatabaseMetadataDao.getSqlDatabaseMetadata(sourceDto, databaseName, schemaName);
        Assert.notNull(sourceDto, "Failed to get the SQL Database Metadata using Database Model Source Config ID [" + sourceConfigId + "], Database Name [" + databaseName + "], and Schema Name [" + schemaName + "]");

        // First create the underlying Database Detail record
        SqlDatabaseDetail database = SqlDatabaseDetail.build(source, databaseName, schemaName);
        this.sessionFactory.getCurrentSession()
                           .persist(database);

        // After creating the underlying Database Detail record, create the Table Details for all Tables in the Database
        Map<String, SqlTableMetadata> tableMetadataByName = sqlDatabaseMetadata.getTableMetadataByName();
        Map<String, SqlTableDetail> createdTablesByNames = createTables(tableMetadataByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);

        // Set the created Table Details before returning the Database Detail
        database.setTables(createdTablesByNames.values()
                                               .stream()
                                               .toList());

        return database.convertToDto();
    }

    /**
     * Read
     **/
    @Override
    public SqlDatabaseDetailDto getDatabase(SqlDatabaseDetailPathDto sqlDatabaseDetailPathDto) {
        Assert.notNull(sqlDatabaseDetailPathDto, "Was provided a null SQL Database Path");

        String databaseName = sqlDatabaseDetailPathDto.getDatabaseName();
        String schemaName = sqlDatabaseDetailPathDto.getSchemaName();
        Assert.notNull(databaseName, "Was provided a SQL Database Path with a null Database Name");
        Assert.notNull(schemaName, "Was provided a SQL Database Path with a null Schema Name");

        SqlDatabaseDetail databaseQueryResult = null;
        try {
            databaseQueryResult = this.sessionFactory.getCurrentSession()
                                                     .createSelectionQuery("from SqlDatabaseDetail where name=:databaseName and schemaName=:schemaName", SqlDatabaseDetail.class)
                                                     .setParameter("databaseName", databaseName)
                                                     .setParameter("schemaName", schemaName)
                                                     .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for SQL Database Detail with Database Path [" + sqlDatabaseDetailPathDto + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for SQL Database Detail with Database Path [" + sqlDatabaseDetailPathDto + "]", e);
        }

        return DtoFactory.fromEntity(databaseQueryResult);
    }

    @Override
    public List<SqlDatabaseDetailDto> getAllDatabases() {
        List<SqlDatabaseDetail> databasesQueryResult;
        try {
            databasesQueryResult = this.sessionFactory.getCurrentSession()
                                                      .createSelectionQuery("from SqlDatabaseDetail", SqlDatabaseDetail.class)
                                                      .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no SQL Database Details", e);
            databasesQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(databasesQueryResult);
    }

    /**
     * Update
     **/
    @Override
    public SqlDatabaseDetailDto updateDatabase(SqlDatabaseDetailDto databaseDtoToUpdate) {
        return null;
    }

    @Override
    public SqlTableDetailDto updateTable(SqlTableDetailDto tableDtoToUpdate) {
        Assert.notNull(tableDtoToUpdate, "Was provided a null SQL Table Detail to update");
        SqlTableDetail table = DtoFactory.toEntity(tableDtoToUpdate);
        this.sessionFactory.getCurrentSession()
                           .merge(table);

        return DtoFactory.fromEntity(table);
    }

    @Override
    public SqlColumnDetailDto updateColumn(SqlColumnDetailDto columnDtoToUpdate) {
        Assert.notNull(columnDtoToUpdate, "Was provided a null SQL Column Detail to update");
        BigInteger columnIdToUpdate = columnDtoToUpdate.getId();
        if (columnIdToUpdate == null || columnIdToUpdate.compareTo(BigInteger.ZERO) < 0) {
            throw new RuntimeException("SQL Column Detail ID [" + columnIdToUpdate + "] is not valid for update");
        }

        SqlColumnDetail existingColumn = this.getColumnEntity(columnIdToUpdate);
        Assert.notNull(columnDtoToUpdate, "Failed to find the SQL Column Detail for update using ID [" + columnIdToUpdate + "]");

        existingColumn.setName(columnDtoToUpdate.getName());
        existingColumn.setDescription(columnDtoToUpdate.getDescription());

        DbModelSourceDataType dataType = DtoFactory.toEntity(columnDtoToUpdate.getDataType());
        existingColumn.setDataType(dataType);

        FieldCategory fieldCategory = DtoFactory.toEntity(columnDtoToUpdate.getCategory());
        existingColumn.setCategory(fieldCategory);

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
    private SqlColumnDetail getColumnEntity(BigInteger columnId) {
        SqlColumnDetail columnQueryResult = null;
        try {
            columnQueryResult = this.sessionFactory.getCurrentSession()
                                                   .createSelectionQuery("from SqlColumnDetail where id=:columnId", SqlColumnDetail.class)
                                                   .setParameter("columnId", columnId)
                                                   .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for SQL Column Detail with ID [" + columnId + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for SQL Column Detail with ID [" + columnId + "]", e);
        }
        return columnQueryResult;
    }

    private Map<String, SqlTableDetail> createTables(Map<String, SqlTableMetadata> tableMetadataByName, SqlDatabaseDetail database, List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        Map<String, SqlTableDetail> createdTablesByNames = new HashMap<>();
        Collection<SqlTableMetadata> allTableMetadata = tableMetadataByName.values();
        for (SqlTableMetadata tableMetadata : allTableMetadata) {
            SqlTableDetail table = createTable(tableMetadata, createdTablesByNames, tableMetadataByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            createdTablesByNames.put(table.getName(), table);
        }
        return createdTablesByNames;
    }

    private SqlTableDetail createTable(SqlTableMetadata tableMetadata, Map<String, SqlTableDetail> createdTablesByNames, Map<String, SqlTableMetadata> tableMetadataByName, SqlDatabaseDetail database, List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        SqlTableDetail table;
        String tableName = tableMetadata.getTableName();
        if (createdTablesByNames.containsKey(tableName)) {
            table = createdTablesByNames.get(tableName);
        } else {
            String tableDescription = tableMetadata.getTableDescription();

            table = new SqlTableDetail();
            table.setName(tableName);
            table.setDescription(tableDescription);
            table.setDatabase(database);

            CollectionCategoryDto collectionCategoryDto = null;
            for (RegexMatcher<CollectionCategoryDto> tableNameToCategoryMatcher : tableNameToCategoryMatchers) {
                if (tableNameToCategoryMatcher.doesValueMatch(tableName)) {
                    collectionCategoryDto = tableNameToCategoryMatcher.getObjectForMatch();
                    break;
                }
            }

            CollectionCategory collectionCategory;
            if (collectionCategoryDto != null) {
                collectionCategory = DtoFactory.toEntity(collectionCategoryDto);
            } else {
                collectionCategory = this.databaseModelDetailDao.getUndefinedCollectionCategory()
                                                                .convertToEntity();
            }
            table.setCategory(collectionCategory);
            this.sessionFactory.getCurrentSession()
                               .persist(table);

            Map<String, SqlColumnMetadata> columnMetadataByName = tableMetadata.getColumnMetadataByName();
            Map<String, SqlColumnDetail> createdColumnsByName = this.createColumns(columnMetadataByName, columnNameToCategoryMatchers);
            for (SqlColumnDetail column : createdColumnsByName.values()) {
                column.setTable(table);
            }
            table.setColumns(createdColumnsByName.values()
                                                 .stream()
                                                 .toList());
            for (SqlColumnDetail column : createdColumnsByName.values()) {
                this.sessionFactory.getCurrentSession()
                                   .persist(column);
            }

            SqlPrimaryKeyMetadata primaryKeyMetadata = tableMetadata.getPrimaryKeySqlMetadata();
            SqlPrimaryKeyDetail primaryKey = this.createPrimaryKey(primaryKeyMetadata, createdColumnsByName);
            primaryKey.setTable(table);
            table.setPrimaryKey(primaryKey);
            this.sessionFactory.getCurrentSession()
                               .persist(primaryKey);

            List<SqlForeignKeyMetadata> allForeignKeyMetadata = tableMetadata.getForeignKeySqlMetadataList();
            List<SqlForeignKeyDetail> foreignKeys = createForeignKeys(allForeignKeyMetadata, tableMetadataByName, createdTablesByNames, createdColumnsByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            table.setForeignKeys(foreignKeys);
            for (SqlForeignKeyDetail foreignKey : foreignKeys) {
                foreignKey.setTable(table);
                this.sessionFactory.getCurrentSession()
                                   .persist(foreignKey);
            }
        }
        return table;
    }

    private Map<String, SqlColumnDetail> createColumns(Map<String, SqlColumnMetadata> columnMetadataByName, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        Map<String, SqlColumnDetail> columnsByName = new HashMap<>();
        for (Map.Entry<String, SqlColumnMetadata> columnMetadataByNameEntry : columnMetadataByName.entrySet()) {
            SqlColumnDetail column = createColumn(columnMetadataByNameEntry.getValue(), columnNameToCategoryMatchers);
            columnsByName.put(column.getName(), column);
        }
        return columnsByName;
    }

    private SqlColumnDetail createColumn(SqlColumnMetadata columnMetadata, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        String columnName = columnMetadata.getColumnName();
        String columnDescription = columnMetadata.getColumnDescription();

        FieldCategoryDto fieldCategoryDto = null;
        for (RegexMatcher<FieldCategoryDto> columnNameToCategoryMatcher : columnNameToCategoryMatchers) {
            if (columnNameToCategoryMatcher.doesValueMatch(columnName)) {
                fieldCategoryDto = columnNameToCategoryMatcher.getObjectForMatch();
                break;
            }
        }

        FieldCategory fieldCategory;
        if (fieldCategoryDto != null) {
            fieldCategory = DtoFactory.toEntity(fieldCategoryDto);
        } else {
            fieldCategory = this.databaseModelDetailDao.getUndefinedFieldCategory()
                                                       .convertToEntity();
        }

        int sqlDataTypeValue = columnMetadata.getSqlDataTypeValue();
        List<DbModelSourceDataTypeDto> matchingDataTypeDtos = databaseModelSourceDao.getAllDataTypes()
                                                                                    .stream()
                                                                                    .filter(dataType -> dataType.getJavaSqlConstant()
                                                                                                                .equals(sqlDataTypeValue))
                                                                                    .toList();
        if (matchingDataTypeDtos.size() != 1) {
            throw new RuntimeException("Expected to match exactly one SQL Database Model Source Data Types to the SQL Data Type value [" + sqlDataTypeValue + "] but found [" + matchingDataTypeDtos.size() + "] instead");
        }
        DbModelSourceDataType dataType = matchingDataTypeDtos.getFirst()
                                                             .convertToEntity();
        boolean isNullable = columnMetadata.getIsNullable();
        boolean isAutoIncrement = columnMetadata.getIsAutoIncrement();
        int columnIndex = columnMetadata.getOrdinalPosition();

        return SqlColumnDetail.build(columnName, columnDescription, dataType, fieldCategory, isNullable, isAutoIncrement, columnIndex);
    }

    private SqlPrimaryKeyDetail createPrimaryKey(SqlPrimaryKeyMetadata primaryKeyMetadata, Map<String, SqlColumnDetail> columnsByName) {
        String primaryKeyName = primaryKeyMetadata.getName();
        SqlPrimaryKeyDetail primaryKey = SqlPrimaryKeyDetail.build(primaryKeyName);

        List<SqlPrimaryKeyColumnDetail> primaryKeyColumns = new ArrayList<>();
        for (String columnName : primaryKeyMetadata.getColumnNames()) {
            SqlColumnDetail column = columnsByName.get(columnName);

            SqlPrimaryKeyColumnDetail primaryKeyColumn = this.createPrimaryKeyColumn();
            primaryKeyColumn.setPrimaryKey(primaryKey);
            primaryKeyColumn.setColumn(column);

            primaryKeyColumns.add(primaryKeyColumn);
        }

        primaryKey.setColumns(primaryKeyColumns);
        return primaryKey;
    }

    private SqlPrimaryKeyColumnDetail createPrimaryKeyColumn() {
        return new SqlPrimaryKeyColumnDetail();
    }

    private List<SqlForeignKeyDetail> createForeignKeys(List<SqlForeignKeyMetadata> allForeignKeyMetadata, Map<String, SqlTableMetadata> tableMetadataByName, Map<String, SqlTableDetail> createdTablesByNames, Map<String, SqlColumnDetail> createdColumnsByName, SqlDatabaseDetail database, List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        List<SqlForeignKeyDetail> foreignKeys = new ArrayList<>();
        for (SqlForeignKeyMetadata sqlForeignKeyMetadata : allForeignKeyMetadata) {
            SqlForeignKeyDetail foreignKey = this.createForeignKey(sqlForeignKeyMetadata, tableMetadataByName, createdTablesByNames, createdColumnsByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            foreignKeys.add(foreignKey);
        }
        return foreignKeys;
    }

    private SqlForeignKeyDetail createForeignKey(SqlForeignKeyMetadata foreignKeyMetadata, Map<String, SqlTableMetadata> tableMetadataByName, Map<String, SqlTableDetail> createdTablesByNames, Map<String, SqlColumnDetail> createdColumnsByName, SqlDatabaseDetail database, List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        String foreignKeyName = foreignKeyMetadata.getForeignKeyName();
        String localColumnName = foreignKeyMetadata.getLocalColumnName();
        String referencedTableName = foreignKeyMetadata.getReferencedTableName();
        String referencedColumnName = foreignKeyMetadata.getReferencedColumnName();

        SqlColumnDetail localColumn = createdColumnsByName.get(localColumnName);
        if (localColumn == null) {
            throw new RuntimeException("Failed to find the SQL Column Detail using Name [" + localColumnName + "]");
        }

        SqlTableDetail referencedTable = createdTablesByNames.get(referencedTableName);
        if (referencedTable == null) {
            if (createdTablesByNames.containsKey(referencedTableName)) {
                throw new RuntimeException("SQL Table with Name [" + referencedTableName + "] is registered as created but it wasn't found within the created Tables Map");
            }

            SqlTableMetadata referencedTableSqlMetadata = tableMetadataByName.get(referencedTableName);
            referencedTable = createTable(referencedTableSqlMetadata, createdTablesByNames, tableMetadataByName, database, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
            createdTablesByNames.put(referencedTable.getName(), referencedTable);
        }
        referencedTable = createdTablesByNames.get(referencedTableName);
        SqlColumnDetail referencedColumn = referencedTable.getColumns()
                                                          .stream()
                                                          .filter(column -> column.getName()
                                                                                  .equalsIgnoreCase(referencedColumnName))
                                                          .findFirst()
                                                          .orElse(null);
        if (referencedColumn == null) {
            throw new RuntimeException("Failed to find the Referenced SQL Column Detail using Name [" + localColumnName + "]");
        }

        SqlForeignKeyDetail foreignKey = SqlForeignKeyDetail.build(foreignKeyName);
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
