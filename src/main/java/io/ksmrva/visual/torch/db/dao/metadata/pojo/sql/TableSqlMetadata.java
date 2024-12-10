package io.ksmrva.visual.torch.db.dao.metadata.pojo.sql;

import java.util.List;
import java.util.Map;

public class TableSqlMetadata {

    private String tableName;

    private String tableDescription;

    private Map<String, ColumnSqlMetadata> columnMetadataByName;

    private PrimaryKeySqlMetadata primaryKeySqlMetadata;

    private List<ForeignKeySqlMetadata> foreignKeySqlMetadataList;

    public TableSqlMetadata() {

    }

    public TableSqlMetadata(String tableName, String tableDescription, Map<String, ColumnSqlMetadata> columnMetadataByName, PrimaryKeySqlMetadata primaryKeySqlMetadata, List<ForeignKeySqlMetadata> foreignKeySqlMetadataList) {
        this.tableName = tableName;
        this.tableDescription = tableDescription;
        this.columnMetadataByName = columnMetadataByName;
        this.primaryKeySqlMetadata = primaryKeySqlMetadata;
        this.foreignKeySqlMetadataList = foreignKeySqlMetadataList;
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

    public Map<String, ColumnSqlMetadata> getColumnMetadataByName() {
        return columnMetadataByName;
    }

    public void setColumnMetadataByName(Map<String, ColumnSqlMetadata> columnMetadataByName) {
        this.columnMetadataByName = columnMetadataByName;
    }

    public PrimaryKeySqlMetadata getPrimaryKeySqlMetadata() {
        return primaryKeySqlMetadata;
    }

    public void setPrimaryKeySqlMetadata(PrimaryKeySqlMetadata primaryKeySqlMetadata) {
        this.primaryKeySqlMetadata = primaryKeySqlMetadata;
    }

    public List<ForeignKeySqlMetadata> getForeignKeySqlMetadataList() {
        return foreignKeySqlMetadataList;
    }

    public void setForeignKeySqlMetadataList(List<ForeignKeySqlMetadata> foreignKeySqlMetadataList) {
        this.foreignKeySqlMetadataList = foreignKeySqlMetadataList;
    }

}
