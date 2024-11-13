package io.ksmrva.visual.torch.domain.meta.sql;

import java.util.ArrayList;
import java.util.List;

public class PrimaryKeySqlMetadata {

    private String name;

    private List<String> columnNames;

    public PrimaryKeySqlMetadata() {

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
