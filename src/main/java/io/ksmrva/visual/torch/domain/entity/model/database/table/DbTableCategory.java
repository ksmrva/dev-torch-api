package io.ksmrva.visual.torch.domain.entity.model.database.table;

import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableCategoryDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "db_table_category", schema = "model")
public class DbTableCategory extends AbstractBaseEntity<DbTableCategoryDto, DbTableCategory> {

    @Column(name = "name")
    private String name;

    @Override
    public DbTableCategoryDto convertToDto() {
        DbTableCategoryDto dto = super.createDtoWithBaseValues(DbTableCategoryDto::new);
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

        if (!(o instanceof DbTableCategory that)) {
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

    public static DbTableCategory build(String name) {
        DbTableCategory tableCategory = new DbTableCategory();
        tableCategory.setName(name);

        return tableCategory;
    }

}
