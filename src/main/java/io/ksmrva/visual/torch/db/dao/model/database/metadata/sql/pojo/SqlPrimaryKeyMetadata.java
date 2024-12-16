package io.ksmrva.visual.torch.db.dao.model.database.metadata.sql.pojo;

import java.util.ArrayList;
import java.util.List;

public class SqlPrimaryKeyMetadata {

    private String name;

    private List<String> columnNames;

    public SqlPrimaryKeyMetadata() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void addColumnName(String columnName) {
        if (columnNames == null) {
            columnNames = new ArrayList<String>();
        }
        columnNames.add(columnName);
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

}
