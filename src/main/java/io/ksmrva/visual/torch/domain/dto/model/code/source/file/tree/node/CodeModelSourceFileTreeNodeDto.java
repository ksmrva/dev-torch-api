package io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.tree.node.CodeModelSourceFileTreeNode;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

public class CodeModelSourceFileTreeNodeDto extends AbstractBaseDto<CodeModelSourceFileTreeNodeDto, CodeModelSourceFileTreeNode> {

    private String name;

    private BigInteger fileId;

    private boolean hasChildren;

    @Override
    public CodeModelSourceFileTreeNode convertToEntity() {
        CodeModelSourceFileTreeNode entity = super.createEntityWithBaseValues(CodeModelSourceFileTreeNode::new);
        entity.setName(this.getName());
        entity.setFileId(this.getFileId());
        entity.setHasChildren(this.isHasChildren());

        return entity;
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

        if (!(o instanceof CodeModelSourceFileTreeNodeDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getFileId(), that.getFileId())
                                  .append(isHasChildren(), that.isHasChildren())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getFileId())
                .append(isHasChildren())
                .toHashCode();
    }

}
