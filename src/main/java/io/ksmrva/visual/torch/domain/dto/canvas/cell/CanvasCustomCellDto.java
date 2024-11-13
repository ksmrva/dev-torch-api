package io.ksmrva.visual.torch.domain.dto.canvas.cell;

import io.ksmrva.visual.torch.domain.entity.canvas.cell.CanvasCustomCell;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CanvasCustomCellDto extends CanvasCellDto<CanvasCustomCellDto, CanvasCustomCell> {

    private String html;

    private Integer height;

    private Integer width;

    private Integer canvasPositionX;

    private Integer canvasPositionY;

    @Override
    public CanvasCustomCell convertToEntity() {
        CanvasCustomCell entity = super.createEntityWithBaseValues(CanvasCustomCell::new);
        entity.setHtml(this.getHtml());
        entity.setWidth(this.getWidth());
        entity.setHeight(this.getHeight());
        entity.setCanvasPositionX(this.getCanvasPositionX());
        entity.setCanvasPositionY(this.getCanvasPositionY());

        return entity;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
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

    public Integer getCanvasPositionX() {
        return canvasPositionX;
    }

    public void setCanvasPositionX(Integer canvasPositionX) {
        this.canvasPositionX = canvasPositionX;
    }

    public Integer getCanvasPositionY() {
        return canvasPositionY;
    }

    public void setCanvasPositionY(Integer canvasPositionY) {
        this.canvasPositionY = canvasPositionY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CanvasCustomCellDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getHtml(), that.getHtml())
                                  .append(getHeight(), that.getHeight())
                                  .append(getWidth(), that.getWidth())
                                  .append(getCanvasPositionX(), that.getCanvasPositionX())
                                  .append(getCanvasPositionY(), that.getCanvasPositionY())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getHtml())
                .append(getHeight())
                .append(getWidth())
                .append(getCanvasPositionX())
                .append(getCanvasPositionY())
                .toHashCode();
    }

    public static CanvasCustomCellDto build(String html) {
        CanvasCustomCellDto customCellDto = new CanvasCustomCellDto();
        customCellDto.setHtml(html);

        customCellDto.setWidth(0);
        customCellDto.setHeight(0);
        customCellDto.setCanvasPositionX(0);
        customCellDto.setCanvasPositionY(0);

        return customCellDto;
    }

}
