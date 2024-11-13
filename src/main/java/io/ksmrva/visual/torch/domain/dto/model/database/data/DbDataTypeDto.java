package io.ksmrva.visual.torch.domain.dto.model.database.data;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.data.DbDataType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbDataTypeDto extends AbstractBaseDto<DbDataTypeDto, DbDataType> {

    private String name;

    private Integer javaSqlConstant;

    @Override
    public DbDataType convertToEntity() {
        DbDataType entity = super.createEntityWithBaseValues(DbDataType::new);
        entity.setName(this.getName());
        entity.setJavaSqlConstant(this.getJavaSqlConstant());

        return entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJavaSqlConstant() {
        return javaSqlConstant;
    }

    public void setJavaSqlConstant(Integer javaSqlConstant) {
        this.javaSqlConstant = javaSqlConstant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbDataTypeDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getJavaSqlConstant(), that.getJavaSqlConstant())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getJavaSqlConstant())
                .toHashCode();
    }

    public static DbDataTypeDto build(String name, Integer javaSqlConstant) {
        DbDataTypeDto dataTypeDto = new DbDataTypeDto();
        dataTypeDto.setName(name);
        dataTypeDto.setJavaSqlConstant(javaSqlConstant);

        return dataTypeDto;
    }

}
