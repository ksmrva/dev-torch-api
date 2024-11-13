package io.ksmrva.visual.torch.domain.entity.canvas.cell;

import io.ksmrva.visual.torch.domain.dto.canvas.cell.CanvasLinkCellDto;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@PrimaryKeyJoinColumn(name = "canvas_cell_id")
@Table(name = "canvas_link_cell", schema = "canvas")
public class CanvasLinkCell extends CanvasCell<CanvasLinkCellDto, CanvasLinkCell> {

    @Column(name = "source_cell_local_name")
    @JoinColumn(name = "source_cell_local_name")
    private String sourceCellLocalName;

    @Column(name = "target_cell_local_name")
    @JoinColumn(name = "target_cell_local_name")
    private String targetCellLocalName;

    @Column(name = "link_source_dx")
    private Integer linkSourceDx;

    @Column(name = "link_source_dy")
    private Integer linkSourceDy;

    @Column(name = "link_target_dx")
    private Integer linkTargetDx;

    @Column(name = "link_target_dy")
    private Integer linkTargetDy;

    @Override
    public CanvasLinkCellDto convertToDto() {
        CanvasLinkCellDto dto = super.createDtoWithBaseValues(CanvasLinkCellDto::new);
        dto.setSourceCellLocalName(this.getSourceCellLocalName());
        dto.setTargetCellLocalName(this.getTargetCellLocalName());
        dto.setLinkSourceDx(this.getLinkSourceDx());
        dto.setLinkSourceDy(this.getLinkSourceDy());
        dto.setLinkTargetDx(this.getLinkTargetDx());
        dto.setLinkTargetDy(this.getLinkTargetDy());

        return dto;
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

        if (!(o instanceof CanvasLinkCell that)) {
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

    public static CanvasLinkCell build(String sourceCellLocalName, String targetCellLocalName) {
        CanvasLinkCell linkCell = new CanvasLinkCell();
        linkCell.setSourceCellLocalName(sourceCellLocalName);
        linkCell.setTargetCellLocalName(targetCellLocalName);

        linkCell.setLinkSourceDx(0);
        linkCell.setLinkSourceDy(0);
        linkCell.setLinkTargetDx(0);
        linkCell.setLinkTargetDy(0);

        return linkCell;
    }

}
