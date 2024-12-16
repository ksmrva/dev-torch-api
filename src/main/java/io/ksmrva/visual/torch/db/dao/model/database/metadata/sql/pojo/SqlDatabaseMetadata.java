package io.ksmrva.visual.torch.db.dao.model.database.metadata.sql.pojo;

import java.util.Map;

public class SqlDatabaseMetadata {

    private String databaseName;

    private String schemaName;

    private Map<String, SqlTableMetadata> tableMetadataByName;

    public SqlDatabaseMetadata() {

    }

    public SqlDatabaseMetadata(String databaseName, String schemaName, Map<String, SqlTableMetadata> tableMetadataByName) {
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

    public Map<String, SqlTableMetadata> getTableMetadataByName() {
        return tableMetadataByName;
    }

    public void setTableMetadataByName(Map<String, SqlTableMetadata> tableMetadataByName) {
        this.tableMetadataByName = tableMetadataByName;
    }

}
