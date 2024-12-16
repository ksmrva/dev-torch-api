package io.ksmrva.visual.torch.db.dao.model.database.metadata.sql.pojo;

import java.util.List;
import java.util.Map;

public class SqlTableMetadata {

    private String tableName;

    private String tableDescription;

    private Map<String, SqlColumnMetadata> columnMetadataByName;

    private SqlPrimaryKeyMetadata primaryKeyMetadata;

    private List<SqlForeignKeyMetadata> foreignKeyMetadataList;

    public SqlTableMetadata() {

    }

    public SqlTableMetadata(String tableName, String tableDescription, Map<String, SqlColumnMetadata> columnMetadataByName, SqlPrimaryKeyMetadata primaryKeyMetadata, List<SqlForeignKeyMetadata> foreignKeyMetadataList) {
        this.tableName = tableName;
        this.tableDescription = tableDescription;
        this.columnMetadataByName = columnMetadataByName;
        this.primaryKeyMetadata = primaryKeyMetadata;
        this.foreignKeyMetadataList = foreignKeyMetadataList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDescription() {
        return tableDescription;
    }

    public void setTableDescription(String tableDescription) {
        this.tableDescription = tableDescription;
    }

    public Map<String, SqlColumnMetadata> getColumnMetadataByName() {
        return columnMetadataByName;
    }

    public void setColumnMetadataByName(Map<String, SqlColumnMetadata> columnMetadataByName) {
        this.columnMetadataByName = columnMetadataByName;
    }

    public SqlPrimaryKeyMetadata getPrimaryKeySqlMetadata() {
        return primaryKeyMetadata;
    }

    public void setPrimaryKeySqlMetadata(SqlPrimaryKeyMetadata primaryKeyMetadata) {
        this.primaryKeyMetadata = primaryKeyMetadata;
    }

    public List<SqlForeignKeyMetadata> getForeignKeySqlMetadataList() {
        return foreignKeyMetadataList;
    }

    public void setForeignKeySqlMetadataList(List<SqlForeignKeyMetadata> foreignKeyMetadataList) {
        this.foreignKeyMetadataList = foreignKeyMetadataList;
    }

}
