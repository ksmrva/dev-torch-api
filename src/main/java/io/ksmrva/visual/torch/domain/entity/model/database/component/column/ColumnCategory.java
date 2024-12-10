package io.ksmrva.visual.torch.domain.entity.model.database.component.column;

import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnCategoryDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "column_category", schema = "db_model_component")
public class ColumnCategory extends AbstractBaseEntity<ColumnCategoryDto, ColumnCategory> {

    @Column(name = "name")
    private String name;

    @Override
    public ColumnCategoryDto convertToDto() {
        ColumnCategoryDto dto = super.createDtoWithBaseValues(ColumnCategoryDto::new);
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

        if (!(o instanceof ColumnCategory that)) {
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

    public static ColumnCategory build(String name) {
        ColumnCategory columnCategory = new ColumnCategory();
        columnCategory.setName(name);

        return columnCategory;
    }

}
