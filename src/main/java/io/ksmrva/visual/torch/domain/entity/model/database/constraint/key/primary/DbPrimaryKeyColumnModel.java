package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary.DbPrimaryKeyColumnModelDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnModel;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "primary_key_column_model", schema = "db_model")
public class DbPrimaryKeyColumnModel extends AbstractBaseEntity<DbPrimaryKeyColumnModelDto, DbPrimaryKeyColumnModel> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_key_id")
    private DbPrimaryKeyModel primaryKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private DbColumnModel column;

    @Override
    public DbPrimaryKeyColumnModelDto convertToDto() {
        DbPrimaryKeyColumnModelDto dto = super.createDtoWithBaseValues(DbPrimaryKeyColumnModelDto::new);

        if (this.getColumn() != null) {
            dto.setColumn(this.getColumn()
                              .convertToDto());
        }

        return dto;
    }

    public DbPrimaryKeyModel getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(DbPrimaryKeyModel primaryKey) {
        this.primaryKey = primaryKey;
    }

    public DbColumnModel getColumn() {
        return column;
    }

    public void setColumn(DbColumnModel column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbPrimaryKeyColumnModel that)) {
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

    public static DbPrimaryKeyColumnModel build(DbPrimaryKeyModel primaryKey, DbColumnModel column) {
        DbPrimaryKeyColumnModel primaryKeyColumn = new DbPrimaryKeyColumnModel();
        primaryKeyColumn.setPrimaryKey(primaryKey);
        primaryKeyColumn.setColumn(column);

        return primaryKeyColumn;
    }

}
