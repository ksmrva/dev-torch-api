package io.ksmrva.visual.torch.domain.dto.model.database.source.data;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.data.DbModelSourceDataType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbModelSourceDataTypeDto extends AbstractBaseDto<DbModelSourceDataTypeDto, DbModelSourceDataType> {

    private String name;

    private Integer javaSqlConstant;

    @Override
    public DbModelSourceDataType convertToEntity() {
        DbModelSourceDataType entity = super.createEntityWithBaseValues(DbModelSourceDataType::new);
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

        if (!(o instanceof DbModelSourceDataTypeDto that)) {
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

    public static DbModelSourceDataTypeDto build(String name, Integer javaSqlConstant) {
        DbModelSourceDataTypeDto dbModelSourceDataTypeDto = new DbModelSourceDataTypeDto();
        dbModelSourceDataTypeDto.setName(name);
        dbModelSourceDataTypeDto.setJavaSqlConstant(javaSqlConstant);

        return dbModelSourceDataTypeDto;
    }

}
