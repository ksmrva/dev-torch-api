package io.ksmrva.visual.torch.domain.dto.model.database.component.table;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableCategory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TableCategoryDto extends AbstractBaseDto<TableCategoryDto, TableCategory> {

    private String name;

    @Override
    public TableCategory convertToEntity() {
        TableCategory entity = super.createEntityWithBaseValues(TableCategory::new);
        entity.setName(this.getName());

        return entity;
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

        if (!(o instanceof TableCategoryDto that)) {
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

    public static TableCategoryDto build(String name) {
        TableCategoryDto tableCategoryDto = new TableCategoryDto();
        tableCategoryDto.setName(name);

        return tableCategoryDto;
    }

}
