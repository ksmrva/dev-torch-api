package io.ksmrva.visual.torch.db.dao.model.database.metadata.sql.pojo;

public class SqlForeignKeyMetadata {

    private String foreignKeyName;

    private String localTableName;

    private String localColumnName;

    private String referencedTableName;

    private String referencedColumnName;

    public SqlForeignKeyMetadata() {

    }

    public SqlForeignKeyMetadata(String foreignKeyName, String localTableName, String localColumnName, String referencedTableName, String referencedColumnName) {
        this.foreignKeyName = foreignKeyName;
        this.localTableName = localTableName;
        this.localColumnName = localColumnName;
        this.referencedTableName = referencedTableName;
        this.referencedColumnName = referencedColumnName;
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public void setForeignKeyName(String foreignKeyName) {
        this.foreignKeyName = foreignKeyName;
    }

    public String getLocalTableName() {
        return localTableName;
    }

    public void setLocalTableName(String localTableName) {
        this.localTableName = localTableName;
    }

    public String getLocalColumnName() {
        return localColumnName;
    }

    public void setLocalColumnName(String localColumnName) {
        this.localColumnName = localColumnName;
    }

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public void setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
    }

}
