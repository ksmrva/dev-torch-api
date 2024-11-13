package io.ksmrva.visual.torch.domain.dto.model.database.table;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableCategory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbTableCategoryDto extends AbstractBaseDto<DbTableCategoryDto, DbTableCategory> {

    private String name;

    @Override
    public DbTableCategory convertToEntity() {
        DbTableCategory entity = super.createEntityWithBaseValues(DbTableCategory::new);
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

        if (!(o instanceof DbTableCategoryDto that)) {
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

    public static DbTableCategoryDto build(String name) {
        DbTableCategoryDto tableCategoryDto = new DbTableCategoryDto();
        tableCategoryDto.setName(name);

        return tableCategoryDto;
    }

}
