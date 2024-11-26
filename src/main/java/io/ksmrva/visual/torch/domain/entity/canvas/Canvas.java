package io.ksmrva.visual.torch.domain.entity.canvas;

import io.ksmrva.visual.torch.domain.dto.canvas.CanvasDto;
import io.ksmrva.visual.torch.domain.dto.canvas.cell.CanvasCellDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.canvas.cell.CanvasCell;
import io.ksmrva.visual.torch.domain.entity.canvas.cell.CanvasCustomCell;
import io.ksmrva.visual.torch.domain.entity.canvas.cell.CanvasLinkCell;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "canvas", schema = "canvas")
public class Canvas extends AbstractBaseEntity<CanvasDto, Canvas> {

    @Column(name = "name")
    private String name;

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

    @Column(name = "background_color")
    private String backgroundColor;

    @OneToMany(mappedBy = "canvasId",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<CanvasCell<?, ?>> cells;

    @Override
    public CanvasDto convertToDto() {
        CanvasDto dto = super.createDtoWithBaseValues(CanvasDto::new);
        dto.setName(this.getName());
        dto.setHeight(this.getHeight());
        dto.setWidth(this.getWidth());
        dto.setBackgroundColor(this.getBackgroundColor());

        List<CanvasCellDto<?, ?>> canvasCellDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getCells())) {
            for (CanvasCell<?, ?> canvasCell : this.getCells()) {
                CanvasCellDto<?, ?> canvasCellDto;
                if (canvasCell instanceof CanvasCustomCell) {
                    canvasCellDto = ((CanvasCustomCell) canvasCell).convertToDto();
                } else if (canvasCell instanceof CanvasLinkCell) {
                    canvasCellDto = ((CanvasLinkCell) canvasCell).convertToDto();
                } else {
                    throw new RuntimeException("Unsupported Canvas Cell type [" + canvasCell.getClass()
                                                                                            .getName() + "]");
                }
                canvasCellDtos.add(canvasCellDto);
            }
        }
        dto.setCells(canvasCellDtos);

        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<CanvasCell<?, ?>> getCells() {
        return cells;
    }

    public void setCells(List<CanvasCell<?, ?>> cells) {
        this.cells = cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Canvas canvas)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), canvas.getName())
                                  .append(getHeight(), canvas.getHeight())
                                  .append(getWidth(), canvas.getWidth())
                                  .append(getBackgroundColor(), canvas.getBackgroundColor())
                                  .append(getCells(), canvas.getCells())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getHeight())
                .append(getWidth())
                .append(getBackgroundColor())
                .append(getCells())
                .toHashCode();
    }

    public static Canvas build(String name) {
        Canvas canvas = new Canvas();
        canvas.setName(name);

        canvas.setHeight(0);
        canvas.setWidth(0);
        canvas.setBackgroundColor("#FFFFFF");
        canvas.setCells(new ArrayList<>());

        return canvas;
    }

}
