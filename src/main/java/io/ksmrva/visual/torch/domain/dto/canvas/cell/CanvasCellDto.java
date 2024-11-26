package io.ksmrva.visual.torch.domain.dto.canvas.cell;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.canvas.cell.CanvasCell;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;
import java.util.function.Supplier;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(value = CanvasCustomCellDto.class, name = "custom"),
               @JsonSubTypes.Type(value = CanvasLinkCellDto.class, name = "link")})
public abstract class CanvasCellDto<D extends CanvasCellDto<D, E>, E extends CanvasCell<D, E>> extends AbstractBaseDto<D, E> {

    private BigInteger canvasId;

    private String name;

    private String localName;

    protected E createEntityWithBaseValues(Supplier<E> entitySupplier) {
        E entity = null;
        if (entitySupplier != null) {
            entity = super.createEntityWithBaseValues(entitySupplier);
            entity.setCanvasId(this.getCanvasId());
            entity.setName(this.getName());
            entity.setLocalName(this.getLocalName());
        }
        return entity;
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

        if (!(o instanceof CanvasCellDto<?, ?> that)) {
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
