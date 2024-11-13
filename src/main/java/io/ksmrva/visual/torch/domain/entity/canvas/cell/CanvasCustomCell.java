package io.ksmrva.visual.torch.domain.entity.canvas.cell;

import io.ksmrva.visual.torch.domain.dto.canvas.cell.CanvasCustomCellDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@PrimaryKeyJoinColumn(name = "canvas_cell_id")
@Table(name = "canvas_custom_cell", schema = "canvas")
public class CanvasCustomCell extends CanvasCell<CanvasCustomCellDto, CanvasCustomCell> {

    @Column(name = "html")
    private String html;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "canvas_position_x")
    private Integer canvasPositionX;

    @Column(name = "canvas_position_y")
    private Integer canvasPositionY;

    @Override
    public CanvasCustomCellDto convertToDto() {
        CanvasCustomCellDto dto = super.createDtoWithBaseValues(CanvasCustomCellDto::new);
        dto.setHtml(this.getHtml());
        dto.setWidth(this.getWidth());
        dto.setHeight(this.getHeight());
        dto.setCanvasPositionX(this.getCanvasPositionX());
        dto.setCanvasPositionY(this.getCanvasPositionY());

        return dto;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public int getCanvasPositionX() {
        return canvasPositionX;
    }

    public void setCanvasPositionX(Integer canvasPositionX) {
        this.canvasPositionX = canvasPositionX;
    }

    public int getCanvasPositionY() {
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

        if (!(o instanceof CanvasCustomCell that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getHtml(), that.getHtml())
                                  .append(getWidth(), that.getWidth())
                                  .append(getHeight(), that.getHeight())
                                  .append(getCanvasPositionX(), that.getCanvasPositionX())
                                  .append(getCanvasPositionY(), that.getCanvasPositionY())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getHtml())
                .append(getWidth())
                .append(getHeight())
                .append(getCanvasPositionX())
                .append(getCanvasPositionY())
                .toHashCode();
    }

    public static CanvasCustomCell build(String html) {
        CanvasCustomCell customCell = new CanvasCustomCell();
        customCell.setHtml(html);

        customCell.setWidth(0);
        customCell.setHeight(0);
        customCell.setCanvasPositionX(0);
        customCell.setCanvasPositionY(0);

        return customCell;
    }

}
