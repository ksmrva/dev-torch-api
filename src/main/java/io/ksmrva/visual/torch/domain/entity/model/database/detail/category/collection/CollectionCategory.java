package io.ksmrva.visual.torch.domain.entity.model.database.detail.category.collection;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "collection_category", schema = "db_model_detail")
public class CollectionCategory extends AbstractBaseEntity<CollectionCategoryDto, CollectionCategory> {

    @Column(name = "name")
    private String name;

    @Override
    public CollectionCategoryDto convertToDto() {
        CollectionCategoryDto dto = super.createDtoWithBaseValues(CollectionCategoryDto::new);
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

        if (!(o instanceof CollectionCategory that)) {
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

    public static CollectionCategory build(String name) {
        CollectionCategory collectionCategory = new CollectionCategory();
        collectionCategory.setName(name);

        return collectionCategory;
    }

}
