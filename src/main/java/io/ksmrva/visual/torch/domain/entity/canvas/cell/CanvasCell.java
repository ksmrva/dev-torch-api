package io.ksmrva.visual.torch.domain.entity.canvas.cell;

import io.ksmrva.visual.torch.domain.dto.canvas.cell.CanvasCellDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigInteger;
import java.util.function.Supplier;

@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "canvas_cell", schema = "canvas")
public class CanvasCell<D extends CanvasCellDto<D, E>, E extends CanvasCell<D, E>> extends AbstractBaseEntity<D, E> {

    @Column(name = "canvas_id")
    @JoinColumn(name = "canvas_id")
    private BigInteger canvasId;

    @Column(name = "name")
    private String name;

    @Column(name = "local_name")
    private String localName;

    @Override
    public D convertToDto() {
        throw new UnsupportedOperationException("Canvas Cell can not be converted directly into a DTO, must use a sub-class such as a Custom or Link Cell");
    }

    protected D createDtoWithBaseValues(Supplier<D> dtoSupplier) {
        D dto = null;
        if (dtoSupplier != null) {
            dto = super.createDtoWithBaseValues(dtoSupplier);
            this.setCanvasId(dto.getCanvasId());
            this.setName(dto.getName());
            this.setLocalName(dto.getLocalName());
        }
        return dto;
    }

    public BigInteger getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(BigInteger canvasId) {
        this.canvasId = canvasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CanvasCell<?, ?> that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getCanvasId(), that.getCanvasId())
                                  .append(getName(), that.getName())
                                  .append(getLocalName(), that.getLocalName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getCanvasId())
                .append(getName())
                .append(getLocalName())
                .toHashCode();
    }

}
