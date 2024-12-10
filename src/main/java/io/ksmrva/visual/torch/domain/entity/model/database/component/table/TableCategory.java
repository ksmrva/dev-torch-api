package io.ksmrva.visual.torch.domain.entity.model.database.component.table;

import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableCategoryDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "table_category", schema = "db_model_component")
public class TableCategory extends AbstractBaseEntity<TableCategoryDto, TableCategory> {

    @Column(name = "name")
    private String name;

    @Override
    public TableCategoryDto convertToDto() {
        TableCategoryDto dto = super.createDtoWithBaseValues(TableCategoryDto::new);
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

        if (!(o instanceof TableCategory that)) {
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

    public static TableCategory build(String name) {
        TableCategory tableCategory = new TableCategory();
        tableCategory.setName(name);

        return tableCategory;
    }

}
