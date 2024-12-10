package io.ksmrva.visual.torch.domain.entity.model.database.source.data;

import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "data_type", schema = "db_model_source")
public class DbModelSourceDataType extends AbstractBaseEntity<DbModelSourceDataTypeDto, DbModelSourceDataType> {

    @Column(name = "name")
    private String name;

    @Column(name = "java_sql_constant")
    private Integer javaSqlConstant;

    @Override
    public DbModelSourceDataTypeDto convertToDto() {
        DbModelSourceDataTypeDto dto = super.createDtoWithBaseValues(DbModelSourceDataTypeDto::new);
        dto.setName(this.getName());
        dto.setJavaSqlConstant(this.getJavaSqlConstant());

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

        if (!(o instanceof DbModelSourceDataType dbModelSourceDataType)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), dbModelSourceDataType.getName())
                                  .append(getJavaSqlConstant(), dbModelSourceDataType.getJavaSqlConstant())
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

    public static DbModelSourceDataType build(String name) {
        DbModelSourceDataType dbModelSourceDataType = new DbModelSourceDataType();
        dbModelSourceDataType.setName(name);
        dbModelSourceDataType.setJavaSqlConstant(null);

        return dbModelSourceDataType;
    }

}
