package io.ksmrva.visual.torch.domain.dto.canvas.cell;

import io.ksmrva.visual.torch.domain.entity.canvas.cell.CanvasLinkCell;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CanvasLinkCellDto extends CanvasCellDto<CanvasLinkCellDto, CanvasLinkCell> {

    private String sourceCellLocalName;

    private String targetCellLocalName;

    private Integer linkSourceDx;

    private Integer linkSourceDy;

    private Integer linkTargetDx;

    private Integer linkTargetDy;

    @Override
    public CanvasLinkCell convertToEntity() {
        CanvasLinkCell entity = super.createEntityWithBaseValues(CanvasLinkCell::new);
        entity.setSourceCellLocalName(this.getSourceCellLocalName());
        entity.setTargetCellLocalName(this.getTargetCellLocalName());
        entity.setLinkSourceDx(this.getLinkSourceDx());
        entity.setLinkSourceDy(this.getLinkSourceDy());
        entity.setLinkTargetDx(this.getLinkTargetDx());
        entity.setLinkTargetDy(this.getLinkTargetDy());

        return entity;
    }

    public String getSourceCellLocalName() {
        return sourceCellLocalName;
    }

    public void setSourceCellLocalName(String sourceCellLocalName) {
        this.sourceCellLocalName = sourceCellLocalName;
    }

    public String getTargetCellLocalName() {
        return targetCellLocalName;
    }

    public void setTargetCellLocalName(String targetCellLocalName) {
        this.targetCellLocalName = targetCellLocalName;
    }

    public Integer getLinkSourceDx() {
        return linkSourceDx;
    }

    public void setLinkSourceDx(Integer linkSourceDx) {
        this.linkSourceDx = linkSourceDx;
    }

    public Integer getLinkSourceDy() {
        return linkSourceDy;
    }

    public void setLinkSourceDy(Integer linkSourceDy) {
        this.linkSourceDy = linkSourceDy;
    }

    public Integer getLinkTargetDx() {
        return linkTargetDx;
    }

    public void setLinkTargetDx(Integer linkTargetDx) {
        this.linkTargetDx = linkTargetDx;
    }

    public Integer getLinkTargetDy() {
        return linkTargetDy;
    }

    public void setLinkTargetDy(Integer linkTargetDy) {
        this.linkTargetDy = linkTargetDy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CanvasLinkCellDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getSourceCellLocalName(), that.getSourceCellLocalName())
                                  .append(getTargetCellLocalName(), that.getTargetCellLocalName())
                                  .append(getLinkSourceDx(), that.getLinkSourceDx())
                                  .append(getLinkSourceDy(), that.getLinkSourceDy())
                                  .append(getLinkTargetDx(), that.getLinkTargetDx())
                                  .append(getLinkTargetDy(), that.getLinkTargetDy())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getSourceCellLocalName())
                .append(getTargetCellLocalName())
                .append(getLinkSourceDx())
                .append(getLinkSourceDy())
                .append(getLinkTargetDx())
                .append(getLinkTargetDy())
                .toHashCode();
    }

    public static CanvasLinkCellDto build(String sourceCellLocalName, String targetCellLocalName) {
        CanvasLinkCellDto linkCellDto = new CanvasLinkCellDto();
        linkCellDto.setSourceCellLocalName(sourceCellLocalName);
        linkCellDto.setTargetCellLocalName(targetCellLocalName);

        linkCellDto.setLinkSourceDx(0);
        linkCellDto.setLinkSourceDy(0);
        linkCellDto.setLinkTargetDx(0);
        linkCellDto.setLinkTargetDy(0);

        return linkCellDto;
    }

}
