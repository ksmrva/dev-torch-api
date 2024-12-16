package io.ksmrva.visual.torch.db.dao.model.database.metadata.sql;

import io.ksmrva.visual.torch.db.dao.model.database.metadata.sql.pojo.*;
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
public class SqlDatabaseMetadataDaoImpl implements SqlDatabaseMetadataDao {

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

    public SqlDatabaseMetadataDaoImpl() {

    }

    @Override
    public SqlDatabaseMetadata getSqlDatabaseMetadata(DbModelSourceConfigDto source, String databaseName, String schemaName) {
        DataSource dataSource = createDataSource(source);

        return extractDatabaseMetadata(dataSource, databaseName, schemaName);
    }

    private SqlDatabaseMetadata extractDatabaseMetadata(DataSource dataSource, String databaseName, String schemaName) {
        SqlDatabaseMetadata sqlDatabaseMetadata;
        try (Connection dataSourceConnection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = dataSourceConnection.getMetaData();

            Map<String, SqlTableMetadata> tableSqlMetadataByName = createTableMetadata(databaseMetaData, databaseName, schemaName);

            sqlDatabaseMetadata = new SqlDatabaseMetadata(databaseName, schemaName, tableSqlMetadataByName);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to extract the Database Metadata using name [" + databaseName + "] and schema [" + schemaName + "]the database tables due to [" + e.getLocalizedMessage() + "]", e);
        }
        return sqlDatabaseMetadata;
    }

    private Map<String, SqlTableMetadata> createTableMetadata(DatabaseMetaData databaseMetaData, String databaseName, String schemaName) throws SQLException {
        Map<String, SqlTableMetadata> tableSqlMetadataByName = new HashMap<>();
        ResultSet tablesMetaData = databaseMetaData.getTables(databaseName, schemaName, null, new String[]{DB_METADATA_TABLE_TYPE_NAME});
        while (tablesMetaData.next()) {
            String tableName = tablesMetaData.getString(TABLE_METADATA_TABLE_NAME_KEY);
            String tableDescription = tablesMetaData.getString(DB_METADATA_DESCRIPTION_KEY);

            Map<String, SqlColumnMetadata> columnSqlMetadataByName = extractColumnMetadata(databaseMetaData, databaseName, schemaName, tableName);
            SqlPrimaryKeyMetadata sqlPrimaryKeyMetadata = extractPrimaryKeyMetadata(databaseMetaData, databaseName, schemaName, tableName);
            List<SqlForeignKeyMetadata> allSqlForeignKeyMetadata = extractForeignKeyMetadata(databaseMetaData, databaseName, schemaName, tableName);

            SqlTableMetadata tableSqlMetadata = new SqlTableMetadata(tableName, tableDescription, columnSqlMetadataByName, sqlPrimaryKeyMetadata, allSqlForeignKeyMetadata);
            tableSqlMetadataByName.put(tableName, tableSqlMetadata);
        }
        return tableSqlMetadataByName;
    }

    private Map<String, SqlColumnMetadata> extractColumnMetadata(DatabaseMetaData databaseMetaData, String databaseName, String schemaName, String tableName) throws SQLException {
        Map<String, SqlColumnMetadata> columnSqlMetadataByName = new HashMap<>();
        ResultSet columnsMetaData = databaseMetaData.getColumns(databaseName, schemaName, tableName, DB_METADATA_COLUMN_NAME_PATTERN);
        while (columnsMetaData.next()) {
            String columnName = columnsMetaData.getString(COLUMN_METADATA_COLUMN_NAME_KEY);
            String columnDescription = columnsMetaData.getString(DB_METADATA_DESCRIPTION_KEY);
            int sqlDataTypeValue = columnsMetaData.getInt(COLUMN_METADATA_DATA_TYPE_KEY);
            String isNullableDeclaration = columnsMetaData.getString(COLUMN_METADATA_IS_NULLABLE_KEY);
            String isAutoIncrementDeclaration = columnsMetaData.getString(COLUMN_METADATA_IS_AUTOINCREMENT_KEY);
            int ordinalPosition = columnsMetaData.getInt(COLUMN_METADATA_ORDINAL_KEY);

            SqlColumnMetadata sqlColumnMetadata = SqlColumnMetadata.build(columnName, columnDescription, sqlDataTypeValue, isNullableDeclaration, isAutoIncrementDeclaration, ordinalPosition);
            columnSqlMetadataByName.put(columnName, sqlColumnMetadata);
        }
        return columnSqlMetadataByName;
    }

    private SqlPrimaryKeyMetadata extractPrimaryKeyMetadata(DatabaseMetaData databaseMetaData, String databaseName, String schemaName, String tableName) throws SQLException {
        ResultSet primaryKeysMetaData = databaseMetaData.getPrimaryKeys(databaseName, schemaName, tableName);
        SqlPrimaryKeyMetadata sqlPrimaryKeyMetadata = new SqlPrimaryKeyMetadata();
        while (primaryKeysMetaData.next()) {
            String primaryKeyNameProvided = primaryKeysMetaData.getString(PRIMARY_KEY_METADATA_PRIMARY_KEY_NAME_KEY);
            String currentPrimaryKeyName = sqlPrimaryKeyMetadata.getName();
            if (currentPrimaryKeyName == null) {
                sqlPrimaryKeyMetadata.setName(primaryKeyNameProvided);
            } else if (!primaryKeyNameProvided.equals(currentPrimaryKeyName)) {
                throw new RuntimeException("The Primary Key Name provided from the database [" + primaryKeyNameProvided + "] was different from the Primary Key Name currently stored locally [" + currentPrimaryKeyName + "]");
            }
            String nextPrimaryKeyColumnName = primaryKeysMetaData.getString(PRIMARY_KEY_METADATA_COLUMN_NAME_KEY);
            sqlPrimaryKeyMetadata.addColumnName(nextPrimaryKeyColumnName);
        }
        return sqlPrimaryKeyMetadata;
    }

    private List<SqlForeignKeyMetadata> extractForeignKeyMetadata(DatabaseMetaData databaseMetaData, String databaseName, String schemaName, String tableName) throws SQLException {
        List<SqlForeignKeyMetadata> allSqlForeignKeyMetadata = new ArrayList<>();
        ResultSet foreignKeysMetaData = databaseMetaData.getImportedKeys(databaseName, schemaName, tableName);
        while (foreignKeysMetaData.next()) {
            String foreignKeyName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_NAME_KEY);
            String localTableName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_LOCAL_TABLE_NAME_KEY);
            String localColumnName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_LOCAL_COLUMN_NAME_KEY);
            String referencedTableName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_REFERENCED_TABLE_NAME_KEY);
            String referencedColumnName = foreignKeysMetaData.getString(FOREIGN_KEY_METADATA_REFERENCED_COLUMN_NAME_KEY);

            SqlForeignKeyMetadata sqlForeignKeyMetadata = new SqlForeignKeyMetadata(foreignKeyName, localTableName, localColumnName, referencedTableName, referencedColumnName);
            allSqlForeignKeyMetadata.add(sqlForeignKeyMetadata);
        }
        return allSqlForeignKeyMetadata;
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
