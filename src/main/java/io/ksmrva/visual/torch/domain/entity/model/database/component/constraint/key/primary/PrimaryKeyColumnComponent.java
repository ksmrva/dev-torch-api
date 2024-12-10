package io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.primary.PrimaryKeyColumnComponentDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnComponent;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "primary_key_column", schema = "db_model_component")
public class PrimaryKeyColumnComponent extends AbstractBaseEntity<PrimaryKeyColumnComponentDto, PrimaryKeyColumnComponent> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_key_id")
    private PrimaryKeyComponent primaryKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private ColumnComponent column;

    @Override
    public PrimaryKeyColumnComponentDto convertToDto() {
        PrimaryKeyColumnComponentDto dto = super.createDtoWithBaseValues(PrimaryKeyColumnComponentDto::new);

        if (this.getColumn() != null) {
            dto.setColumn(this.getColumn()
                              .convertToDto());
        }

        return dto;
    }

    public PrimaryKeyComponent getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKeyComponent primaryKey) {
        this.primaryKey = primaryKey;
    }

    public ColumnComponent getColumn() {
        return column;
    }

    public void setColumn(ColumnComponent column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PrimaryKeyColumnComponent that)) {
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

    public static PrimaryKeyColumnComponent build(PrimaryKeyComponent primaryKey, ColumnComponent column) {
        PrimaryKeyColumnComponent primaryKeyColumn = new PrimaryKeyColumnComponent();
        primaryKeyColumn.setPrimaryKey(primaryKey);
        primaryKeyColumn.setColumn(column);

        return primaryKeyColumn;
    }

}
