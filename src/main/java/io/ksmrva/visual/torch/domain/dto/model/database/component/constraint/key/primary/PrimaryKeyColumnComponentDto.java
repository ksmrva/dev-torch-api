package io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyColumnComponent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PrimaryKeyColumnComponentDto extends AbstractBaseDto<PrimaryKeyColumnComponentDto, PrimaryKeyColumnComponent> {

    private ColumnComponentDto column;

    @Override
    public PrimaryKeyColumnComponent convertToEntity() {
        PrimaryKeyColumnComponent entity = super.createEntityWithBaseValues(PrimaryKeyColumnComponent::new);

        if (this.getColumn() != null) {
            entity.setColumn(this.getColumn()
                                 .convertToEntity());
        }

        return entity;
    }

    public ColumnComponentDto getColumn() {
        return column;
    }

    public void setColumn(ColumnComponentDto column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PrimaryKeyColumnComponentDto that)) {
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

    public static PrimaryKeyColumnComponentDto build(ColumnComponentDto column) {
        PrimaryKeyColumnComponentDto primaryKeyColumnDto = new PrimaryKeyColumnComponentDto();
        primaryKeyColumnDto.setColumn(column);

        return primaryKeyColumnDto;
    }

}
