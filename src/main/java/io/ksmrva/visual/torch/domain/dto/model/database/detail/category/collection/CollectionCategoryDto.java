package io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.category.collection.CollectionCategory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CollectionCategoryDto extends AbstractBaseDto<CollectionCategoryDto, CollectionCategory> {

    private String name;

    @Override
    public CollectionCategory convertToEntity() {
        CollectionCategory entity = super.createEntityWithBaseValues(CollectionCategory::new);
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

        if (!(o instanceof CollectionCategoryDto that)) {
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

    public static CollectionCategoryDto build(String name) {
        CollectionCategoryDto collectionCategoryDto = new CollectionCategoryDto();
        collectionCategoryDto.setName(name);

        return collectionCategoryDto;
    }

}
