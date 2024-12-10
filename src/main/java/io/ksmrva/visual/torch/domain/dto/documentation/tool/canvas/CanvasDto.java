package io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasCellDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasCustomCellDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasLinkCellDto;
import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.Canvas;
import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell.CanvasCell;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CanvasDto extends AbstractBaseDto<CanvasDto, Canvas> {

    private String name;

    private Integer height;

    private Integer width;

    private String backgroundColor;

    private List<CanvasCellDto<?, ?>> cells;

    @Override
    public Canvas convertToEntity() {
        Canvas entity = super.createEntityWithBaseValues(Canvas::new);
        entity.setName(this.getName());
        entity.setHeight(this.getHeight());
        entity.setWidth(this.getWidth());
        entity.setBackgroundColor(this.getBackgroundColor());

        List<CanvasCell<?, ?>> canvasCells = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getCells())) {
            for (CanvasCellDto<?, ?> canvasCellDto : this.getCells()) {
                CanvasCell<?, ?> canvasCell;
                if (canvasCellDto instanceof CanvasCustomCellDto) {
                    canvasCell = ((CanvasCustomCellDto) canvasCellDto).convertToEntity();
                } else if (canvasCellDto instanceof CanvasLinkCellDto) {
                    canvasCell = ((CanvasLinkCellDto) canvasCellDto).convertToEntity();
                } else {
                    throw new RuntimeException("Unsupported Canvas Cell DTO type [" + canvasCellDto.getClass()
                                                                                                   .getName() + "]");
                }
                canvasCells.add(canvasCell);
            }
        }
        entity.setCells(canvasCells);
        return entity;
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

    public List<CanvasCellDto<?, ?>> getCells() {
        return cells;
    }

    public void setCells(List<CanvasCellDto<?, ?>> cells) {
        this.cells = cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CanvasDto canvasDto)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), canvasDto.getName())
                                  .append(getHeight(), canvasDto.getHeight())
                                  .append(getWidth(), canvasDto.getWidth())
                                  .append(getBackgroundColor(), canvasDto.getBackgroundColor())
                                  .append(getCells(), canvasDto.getCells())
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

    public static CanvasDto build(String name) {
        CanvasDto canvasDto = new CanvasDto();
        canvasDto.setName(name);

        canvasDto.setHeight(0);
        canvasDto.setWidth(0);
        canvasDto.setBackgroundColor("#FFFFFF");
        canvasDto.setCells(new ArrayList<>());

        return canvasDto;
    }

}
