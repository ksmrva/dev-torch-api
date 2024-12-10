package io.ksmrva.visual.torch.db.dao.metadata;

import io.ksmrva.visual.torch.db.dao.metadata.pojo.sql.*;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseMetadataDaoImpl implements DatabaseMetadataDao {

    private static final String DB_METADATA_COLUMN_NAME_PATTERN = "%";

    private static final String DB_METADATA_TABLE_TYPE_NAME = "TABLE";

    private static final String DB_METADATA_DESCRIPTION_KEY = "REMARKS";

    private static final String TABLE_METADATA_TABLE_NAME_KEY = "TABLE_NAME";

    private static final String COLUMN_METADATA_COLUMN_NAME_KEY = "COLUMN_NAME";

    private static final String COLUMN_METADATA_DATA_TYPE_KEY = "DATA_TYPE";

    private static final String COLUMN_METADATA_IS_NULLABLE_KEY = "IS_NULLABLE";

    private static final String COLUMN_METADATA_IS_AUTOINCREMENT_KEY = "IS_AUTOINCREMENT";

    private static final String COLUMN_METADATA_ORDINAL_KEY = "ORDINAL_POSITION";

    private static final String PRIMARY_KEY_METADATA_PRIMARY_KEY_NAME_KEY = "PK_NAME";

    private static final String PRIMARY_KEY_METADATA_COLUMN_NAME_KEY = "COLUMN_NAME";

    private static final String FOREIGN_KEY_METADATA_NAME_KEY = "FK_NAME";

    // TODO: Incorporate schema to foreign key
    private static final String FOREIGN_KEY_METADATA_LOCAL_SCHEMA_NAME_KEY = "FKTABLE_SCHEM";

    private static final String FOREIGN_KEY_METADATA_LOCAL_TABLE_NAME_KEY = "FKTABLE_NAME";

    private static final String FOREIGN_KEY_METADATA_LOCAL_COLUMN_NAME_KEY = "FKCOLUMN_NAME";

    private static final String FOREIGN_KEY_METADATA_REFERENCED_SCHEMA_NAME_KEY = "PKTABLE_SCHEM";

    private static final String FOREIGN_KEY_METADATA_REFERENCED_TABLE_NAME_KEY = "PKTABLE_NAME";

    private static final String FOREIGN_KEY_METADATA_REFERENCED_COLUMN_NAME_KEY = "PKCOLUMN_NAME";

    public DatabaseMetadataDaoImpl() {

    }

    @Override
    public DatabaseSqlMetadata getDatabaseMetadata(DbModelSourceConfigDto source, String databaseName, String schemaName) {
        DataSource dataSource = createDataSource(source);

        return extractDatabaseMetadata(dataSource, databaseName, schemaName);
    }

    private DatabaseSqlMetadata extractDatabaseMetadata(DataSource dataSource, String databaseName, String schemaName) {
        DatabaseSqlMetadata databaseSqlMetadata;
        try (Connection dataSourceConnection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = dataSourceConnection.getMetaData();

            Map<String, TableSqlMetadata> tableSqlMetadataByName = createTableMetadata(databaseMetaData, databaseName, schemaName);

            databaseSqlMetadata = new DatabaseSqlMetadata(databaseName, schemaName, tableSqlMetadataByName);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to extract the Database Metadata using name [" + databaseName + "] and schema [" + schemaName + "]the database tables due to [" + e.getLocalizedMessage() + "]", e);
        }
        return databaseSqlMetadata;
    }

    private Map<String, TableSqlMetadata> createTableMetadata(DatabaseMetaData databaseMetaData, String databaseName, String schemaName) throws SQLException {
        Map<String, TableSqlMetadata> tableSqlMetadataByName = new HashMap<>();
        ResultSet tablesMetaData = databaseMetaData.getTables(databaseName, schemaName, null, new String[]{DB_METADATA_TABLE_TYPE_NAME});
        while (tablesMetaData.next()) {
            String tableName = tablesMetaData.getString(TABLE_METADATA_TABLE_NAME_KEY);
            String tableDescription = tablesMetaData.getString(DB_METADATA_DESCRIPTION_KEY);

            Map<String, ColumnSqlMetadata> columnSqlMetadataByName = extractColumnMetadata(databaseMetaData, databaseName, schemaName, tableName);
            PrimaryKeySqlMetadata primaryKeySqlMetadata = extractPrimaryKeyMetadata(databaseMetaData, databaseName, schemaName, tableName);
            List<ForeignKeySqlMetadata> allForeignKeySqlMetadata = extractForeignKeyMetadata(databaseMetaData, databaseName, schemaName, tableName);

            TableSqlMetadata tableSqlMetadata = new TableSqlMetadata(tableName, tableDescription, columnSqlMetadataByName, primaryKeySqlMetadata, allForeignKeySqlMetadata);
            tableSqlMetadataByName.put(tableName, tableSqlMetadata);
        }
        return tableSqlMetadataByName;
    }

    private Map<String, ColumnSqlMetadata> extractColumnMetadata(DatabaseMetaData databaseMetaData, String databaseName, String schemaName, String tableName) throws SQLException {
        Map<String, ColumnSqlMetadata> columnSqlMetadataByName = new HashMap<>();
        ResultSet columnsMetaData = databaseMetaData.getColumns(databaseName, schemaName, tableName, DB_METADATA_COLUMN_NAME_PATTERN);
        while (columnsMetaData.next()) {
            String columnName = columnsMetaData.getString(COLUMN_METADATA_COLUMN_NAME_KEY);
            String columnDescription = columnsMetaData.getString(DB_METADATA_DESCRIPTION_KEY);
            int sqlDataTypeValue = columnsMetaData.getInt(COLUMN_METADATA_DATA_TYPE_KEY);
            String isNullableDeclaration = columnsMetaData.getString(COLUMN_METADATA_IS_NULLABLE_KEY);
            String isAutoIncrementDeclaration = columnsMetaData.getString(COLUMN_METADATA_IS_AUTOINCREMENT_KEY);
            int ordinalPosition = columnsMetaData.getInt(COLUMN_METADATA_ORDINAL_KEY);

            ColumnSqlMetadata columnSqlMetadata = ColumnSqlMetadata.build(columnName, columnDescription, sqlDataTypeValue, isNullableDeclaration, isAutoIncrementDeclaration, ordinalPosition);
            columnSqlMetadataByName.put(columnName, columnSqlMetadata);
        }
        return columnSqlMetadataByName;
    }

    private PrimaryKeySqlMetadata extractPrimaryKeyMetadata(DatabaseMetaData databaseMetaData, String databaseName, String schemaName, String tableName) throws SQLException {
        ResultSet primaryKeysMetaData = databaseMetaData.getPrimaryKeys(databaseName, schemaName, tableName);
        PrimaryKeySqlMetadata primaryKeySqlMetadata = new PrimaryKeySqlMetadata();
        while (primaryKeysMetaData.next()) {
            String primaryKeyNameProvided = primaryKeysMetaData.getString(PRIMARY_KEY_METADATA_PRIMARY_KEY_NAME_KEY);
            String currentPrimaryKeyName = primaryKeySqlMetadata.getName();
            if (currentPrimaryKeyName == null) {
                primaryKeySqlMetadata.setName(primaryKeyNameProvided);
            } else if (!primaryKeyNameProvided.equals(currentPrimaryKeyName)) {
                throw new RuntimeException("The Primary Key Name provided from the database [" + primaryKeyNameProvided + "] was different from the Primary Key Name currently stored locally [" + currentPrimaryKeyName + "]");
            }
            String nextPrimaryKeyColumnName = primaryKeysMetaData.getString(PRIMARY_KEY_METADATA_COLUMN_NAME_KEY);
            primaryKeySqlMetadata.addColumnName(nextPrimaryKeyColumnName);
        }
        return primaryKeySqlMetadata;
    }

    private List<ForeignKeySqlMetadata> extractForeignKeyMetadata(DatabaseMetaData databaseMetaData, String databaseName, String schemaName, String tableName) throws SQLException {
        List<ForeignKeySqlMetadata> allForeignKeySqlMetadata = new ArrayList<>();
        ResultSet foreignKeysMetaData = databaseMetaData.getImportedKeys(databaseName, schemaName, tableName);
        while (foreignKeysMetaData.next()) {
            String foreignKeyName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_NAME_KEY);
            String localTableName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_LOCAL_TABLE_NAME_KEY);
            String localColumnName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_LOCAL_COLUMN_NAME_KEY);
            String referencedTableName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_REFERENCED_TABLE_NAME_KEY);
            String referencedColumnName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_REFERENCED_COLUMN_NAME_KEY);

            ForeignKeySqlMetadata foreignKeySqlMetadata = new ForeignKeySqlMetadata(foreignKeyName, localTableName, localColumnName, referencedTableName, referencedColumnName);
            allForeignKeySqlMetadata.add(foreignKeySqlMetadata);
        }
        return allForeignKeySqlMetadata;
    }

    private DataSource createDataSource(DbModelSourceConfigDto source) {
        String url = source.getUrl()
                                 .getFullyFormedUrl();
        String driverName = source.getDriverName();
        String username = source.getUsername();
        String password = source.getPassword();

        return createDataSource(url, driverName, username, password);
    }

    private DataSource createDataSource(String url, String driverName, String username, String password) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.driverClassName(driverName);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();
    }

}
