package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyColumnDetail;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SqlPrimaryKeyColumnDetailDto extends AbstractBaseDto<SqlPrimaryKeyColumnDetailDto, SqlPrimaryKeyColumnDetail> {

    private SqlColumnDetailDto column;

    @Override
    public SqlPrimaryKeyColumnDetail convertToEntity() {
        SqlPrimaryKeyColumnDetail entity = super.createEntityWithBaseValues(SqlPrimaryKeyColumnDetail::new);

        if (this.getColumn() != null) {
            entity.setColumn(this.getColumn()
                                 .convertToEntity());
        }

        return entity;
    }

    public SqlColumnDetailDto getColumn() {
        return column;
    }

    public void setColumn(SqlColumnDetailDto column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlPrimaryKeyColumnDetailDto that)) {
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

    public static SqlPrimaryKeyColumnDetailDto build(SqlColumnDetailDto column) {
        SqlPrimaryKeyColumnDetailDto primaryKeyColumnDto = new SqlPrimaryKeyColumnDetailDto();
        primaryKeyColumnDto.setColumn(column);

        return primaryKeyColumnDto;
    }

}
