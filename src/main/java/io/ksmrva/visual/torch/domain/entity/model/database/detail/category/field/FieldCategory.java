package io.ksmrva.visual.torch.domain.entity.model.database.detail.category.field;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "field_category", schema = "db_model_detail")
public class FieldCategory extends AbstractBaseEntity<FieldCategoryDto, FieldCategory> {

    @Column(name = "name")
    private String name;

    @Override
    public FieldCategoryDto convertToDto() {
        FieldCategoryDto dto = super.createDtoWithBaseValues(FieldCategoryDto::new);
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

        if (!(o instanceof FieldCategory that)) {
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

    public static FieldCategory build(String name) {
        FieldCategory fieldCategory = new FieldCategory();
        fieldCategory.setName(name);

        return fieldCategory;
    }

}
