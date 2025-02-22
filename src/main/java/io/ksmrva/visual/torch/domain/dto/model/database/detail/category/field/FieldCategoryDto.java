package io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.category.field.FieldCategory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FieldCategoryDto extends AbstractBaseDto<FieldCategoryDto, FieldCategory> {

    private String name;

    @Override
    public FieldCategory convertToEntity() {
        FieldCategory entity = super.createEntityWithBaseValues(FieldCategory::new);
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

        if (!(o instanceof FieldCategoryDto that)) {
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

    public static FieldCategoryDto build(String name) {
        FieldCategoryDto fieldCategoryDto = new FieldCategoryDto();
        fieldCategoryDto.setName(name);

        return fieldCategoryDto;
    }

}
