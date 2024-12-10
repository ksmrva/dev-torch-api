package io.ksmrva.visual.torch.domain.dto.model.database.component.column;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnCategory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ColumnCategoryDto extends AbstractBaseDto<ColumnCategoryDto, ColumnCategory> {

    private String name;

    @Override
    public ColumnCategory convertToEntity() {
        ColumnCategory entity = super.createEntityWithBaseValues(ColumnCategory::new);
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

        if (!(o instanceof ColumnCategoryDto that)) {
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

    public static ColumnCategoryDto build(String name) {
        ColumnCategoryDto columnCategoryDto = new ColumnCategoryDto();
        columnCategoryDto.setName(name);

        return columnCategoryDto;
    }

}
