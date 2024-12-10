package io.ksmrva.visual.torch.domain.dto.model.database.component.path;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DatabasePathDto {

    private String databaseName;

    private String schemaName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DatabasePathDto that)) {
            return false;
        }

        return new EqualsBuilder().append(getDatabaseName(), that.getDatabaseName())
                                  .append(getSchemaName(), that.getSchemaName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getDatabaseName())
                .append(getSchemaName())
                .toHashCode();
    }

    public static DatabasePathDto build(String databaseName, String schemaName) {
        DatabasePathDto databasePathDto = new DatabasePathDto();
        databasePathDto.setDatabaseName(databaseName);
        databasePathDto.setSchemaName(schemaName);

        return databasePathDto;
    }

    @Override
    public String toString() {
        return "Database Name [" + getDatabaseName() + "]; Schema Name [" + getSchemaName() + "]";
    }

}
