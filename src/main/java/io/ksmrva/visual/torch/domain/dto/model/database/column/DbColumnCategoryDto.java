package io.ksmrva.visual.torch.domain.dto.model.database.column;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnCategory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbColumnCategoryDto extends AbstractBaseDto<DbColumnCategoryDto, DbColumnCategory> {

    private String name;

    @Override
    public DbColumnCategory convertToEntity() {
        DbColumnCategory entity = super.createEntityWithBaseValues(DbColumnCategory::new);
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

        if (!(o instanceof DbColumnCategoryDto that)) {
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

    public static DbColumnCategoryDto build(String name) {
        DbColumnCategoryDto columnCategoryDto = new DbColumnCategoryDto();
        columnCategoryDto.setName(name);

        return columnCategoryDto;
    }

}
