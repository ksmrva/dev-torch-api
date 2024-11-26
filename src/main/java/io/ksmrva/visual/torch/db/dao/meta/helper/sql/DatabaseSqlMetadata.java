package io.ksmrva.visual.torch.db.dao.meta.helper.sql;

import java.util.Map;

public class DatabaseSqlMetadata {

    private String databaseName;

    private String schemaName;

    private Map<String, TableSqlMetadata> tableMetadataByName;

    public DatabaseSqlMetadata() {

    }

    public DatabaseSqlMetadata(String databaseName, String schemaName, Map<String, TableSqlMetadata> tableMetadataByName) {
        this.databaseName = databaseName;
        this.schemaName = schemaName;
        this.tableMetadataByName = tableMetadataByName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public Map<String, TableSqlMetadata> getTableMetadataByName() {
        return tableMetadataByName;
    }

    public void setTableMetadataByName(Map<String, TableSqlMetadata> tableMetadataByName) {
        this.tableMetadataByName = tableMetadataByName;
    }

}
