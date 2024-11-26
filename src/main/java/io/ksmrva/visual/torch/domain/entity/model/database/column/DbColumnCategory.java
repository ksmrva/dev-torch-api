package io.ksmrva.visual.torch.domain.entity.model.database.column;

import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnCategoryDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "column_category", schema = "db_model")
public class DbColumnCategory extends AbstractBaseEntity<DbColumnCategoryDto, DbColumnCategory> {

    @Column(name = "name")
    private String name;

    @Override
    public DbColumnCategoryDto convertToDto() {
        DbColumnCategoryDto dto = super.createDtoWithBaseValues(DbColumnCategoryDto::new);
        dto.setName(this.getName());

        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbColumnCategory that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .toHashCode();
    }

    public static DbColumnCategory build(String name) {
        DbColumnCategory columnCategory = new DbColumnCategory();
        columnCategory.setName(name);

        return columnCategory;
    }

}
