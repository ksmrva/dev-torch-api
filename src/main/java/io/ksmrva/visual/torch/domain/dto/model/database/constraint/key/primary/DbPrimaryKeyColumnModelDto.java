package io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary.DbPrimaryKeyColumnModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbPrimaryKeyColumnModelDto extends AbstractBaseDto<DbPrimaryKeyColumnModelDto, DbPrimaryKeyColumnModel> {

    private DbColumnModelDto column;

    @Override
    public DbPrimaryKeyColumnModel convertToEntity() {
        DbPrimaryKeyColumnModel entity = super.createEntityWithBaseValues(DbPrimaryKeyColumnModel::new);

        if (this.getColumn() != null) {
            entity.setColumn(this.getColumn()
                                 .convertToEntity());
        }

        return entity;
    }

    public DbColumnModelDto getColumn() {
        return column;
    }

    public void setColumn(DbColumnModelDto column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbPrimaryKeyColumnModelDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getColumn(), that.getColumn())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getColumn())
                .toHashCode();
    }

    public static DbPrimaryKeyColumnModelDto build(DbColumnModelDto column) {
        DbPrimaryKeyColumnModelDto primaryKeyColumnDto = new DbPrimaryKeyColumnModelDto();
        primaryKeyColumnDto.setColumn(column);

        return primaryKeyColumnDto;
    }

}
