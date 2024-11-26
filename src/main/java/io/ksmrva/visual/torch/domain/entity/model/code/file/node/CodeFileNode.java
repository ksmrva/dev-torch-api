package io.ksmrva.visual.torch.domain.entity.model.code.file.node;

import io.ksmrva.visual.torch.domain.dto.model.code.file.node.CodeFileNodeDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import java.math.BigInteger;

@Entity
@DynamicUpdate
@Table(name = "file_node", schema = "code_model")
public class CodeFileNode extends AbstractBaseEntity<CodeFileNodeDto, CodeFileNode> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_file_node_id")
    private CodeFileNode parentNode;

    @Formula(value = "(SELECT f.name FROM code_model.file f WHERE f.file_node_id=id)")
    private String name;

    @Formula(value = "(SELECT f.id FROM code_model.file f WHERE f.file_node_id=id)")
    private BigInteger fileId;

    @Formula(value = "(SELECT COUNT(*) > 0 FROM code_model.file_node n WHERE n.parent_file_node_id=id)")
    private boolean hasChildren;

    @Override
    public CodeFileNodeDto convertToDto() {
        CodeFileNodeDto dto = super.createDtoWithBaseValues(CodeFileNodeDto::new);
        dto.setName(this.getName());
        dto.setFileId(this.getFileId());
        dto.setHasChildren(this.isHasChildren());

        return dto;
    }

    public CodeFileNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(CodeFileNode parentNode) {
        this.parentNode = parentNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getFileId() {
        return fileId;
    }

    public void setFileId(BigInteger fileId) {
        this.fileId = fileId;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeFileNode that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getParentNode(), that.getParentNode())
                                  .append(getName(), that.getName())
                                  .append(getFileId(), that.getFileId())
                                  .append(isHasChildren(), that.isHasChildren())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getParentNode())
                .append(getName())
                .append(getFileId())
                .append(isHasChildren())
                .toHashCode();
    }

}
