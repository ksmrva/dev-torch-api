package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyColumnDetailDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column.SqlColumnDetail;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "sql_primary_key_column", schema = "db_model_detail")
public class SqlPrimaryKeyColumnDetail extends AbstractBaseEntity<SqlPrimaryKeyColumnDetailDto, SqlPrimaryKeyColumnDetail> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_key_id")
    private SqlPrimaryKeyDetail primaryKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private SqlColumnDetail column;

    @Override
    public SqlPrimaryKeyColumnDetailDto convertToDto() {
        SqlPrimaryKeyColumnDetailDto dto = super.createDtoWithBaseValues(SqlPrimaryKeyColumnDetailDto::new);

        if (this.getColumn() != null) {
            dto.setColumn(this.getColumn()
                              .convertToDto());
        }

        return dto;
    }

    public SqlPrimaryKeyDetail getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(SqlPrimaryKeyDetail primaryKey) {
        this.primaryKey = primaryKey;
    }

    public SqlColumnDetail getColumn() {
        return column;
    }

    public void setColumn(SqlColumnDetail column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlPrimaryKeyColumnDetail that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getPrimaryKey(), that.getPrimaryKey())
                                  .append(getColumn(), that.getColumn())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getPrimaryKey())
                .append(getColumn())
                .toHashCode();
    }

    public static SqlPrimaryKeyColumnDetail build(SqlPrimaryKeyDetail primaryKey, SqlColumnDetail column) {
        SqlPrimaryKeyColumnDetail primaryKeyColumn = new SqlPrimaryKeyColumnDetail();
        primaryKeyColumn.setPrimaryKey(primaryKey);
        primaryKeyColumn.setColumn(column);

        return primaryKeyColumn;
    }

}
