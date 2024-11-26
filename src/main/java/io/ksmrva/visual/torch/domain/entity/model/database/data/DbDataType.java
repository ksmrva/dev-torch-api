package io.ksmrva.visual.torch.domain.entity.model.database.data;

import io.ksmrva.visual.torch.domain.dto.model.database.data.DbDataTypeDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "db_data_type", schema = "db_model")
public class DbDataType extends AbstractBaseEntity<DbDataTypeDto, DbDataType> {

    @Column(name = "name")
    private String name;

    @Column(name = "java_sql_constant")
    private Integer javaSqlConstant;

    @Override
    public DbDataTypeDto convertToDto() {
        DbDataTypeDto dto = super.createDtoWithBaseValues(DbDataTypeDto::new);
        dto.setName(this.getName());

        return dto;
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

        if (!(o instanceof DbDataType dataType)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), dataType.getName())
                                  .append(getJavaSqlConstant(), dataType.getJavaSqlConstant())
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

    public static DbDataType build(String name) {
        DbDataType dataType = new DbDataType();
        dataType.setName(name);

        return dataType;
    }

}
