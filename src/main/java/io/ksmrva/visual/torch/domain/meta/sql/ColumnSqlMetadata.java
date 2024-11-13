package io.ksmrva.visual.torch.domain.meta.sql;

public class ColumnSqlMetadata {

    private static final String DB_METADATA_BOOLEAN_TRUE_VALUE = "YES";

    private static final String DB_METADATA_BOOLEAN_FALSE_VALUE = "NO";

    private String columnName;

    private String columnDescription;

    private int sqlDataTypeValue;

    private boolean isNullable;

    private boolean isAutoIncrement;

    private int ordinalPosition;

    public ColumnSqlMetadata(String columnName, String columnDescription, int sqlDataTypeValue, boolean isNullable, boolean isAutoIncrement, int ordinalPosition) {
        this.columnName = columnName;
        this.columnDescription = columnDescription;
        this.sqlDataTypeValue = sqlDataTypeValue;
        this.isNullable = isNullable;
        this.isAutoIncrement = isAutoIncrement;
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDescription() {
        return columnDescription;
    }

    public void setColumnDescription(String columnDescription) {
        this.columnDescription = columnDescription;
    }

    public int getSqlDataTypeValue() {
        return sqlDataTypeValue;
    }

    public void setSqlDataTypeValue(int sqlDataTypeValue) {
        this.sqlDataTypeValue = sqlDataTypeValue;
    }

    public boolean getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public boolean getIsAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public static ColumnSqlMetadata build(String columnName, String columnDescription, int sqlDataTypeValue, String isNullableDeclaration, String isAutoIncrementDeclaration, int ordinalPosition) {
        boolean isNullable = extractBooleanValueDeclarationOrThrow(isNullableDeclaration);
        boolean isAutoIncrement = extractBooleanValueDeclarationOrThrow(isAutoIncrementDeclaration);

        return new ColumnSqlMetadata(columnName, columnDescription, sqlDataTypeValue, isNullable, isAutoIncrement, ordinalPosition);
    }

    private static boolean extractBooleanValueDeclarationOrThrow(String booleanValueDeclaration) {
        boolean result;
        if (booleanValueDeclaration.equals(DB_METADATA_BOOLEAN_TRUE_VALUE)) {
            result = true;
        } else if (booleanValueDeclaration.equals(DB_METADATA_BOOLEAN_FALSE_VALUE)) {
            result = false;
        } else {
            throw new RuntimeException("Unrecognized value declaration [" + booleanValueDeclaration + "] given for Boolean");
        }
        return result;
    }

}
