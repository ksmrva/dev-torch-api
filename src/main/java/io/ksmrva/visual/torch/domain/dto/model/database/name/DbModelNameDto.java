package io.ksmrva.visual.torch.domain.dto.model.database.name;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbModelNameDto {

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

        if (!(o instanceof DbModelNameDto that)) {
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

    public static DbModelNameDto build(String databaseName, String schemaName) {
        DbModelNameDto dbModelNameDto = new DbModelNameDto();
        dbModelNameDto.setDatabaseName(databaseName);
        dbModelNameDto.setSchemaName(schemaName);

        return dbModelNameDto;
    }

}
