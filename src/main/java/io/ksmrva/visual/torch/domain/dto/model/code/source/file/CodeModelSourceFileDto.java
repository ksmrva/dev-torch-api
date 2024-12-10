package io.ksmrva.visual.torch.domain.dto.model.code.source.file;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.data.CodeModelSourceFileDataDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node.CodeModelSourceFileTreeNodeDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.CodeModelSourceFile;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class CodeModelSourceFileDto extends AbstractBaseDto<CodeModelSourceFileDto, CodeModelSourceFile> {

    private CodeModelSourceFileTreeNodeDto treeNode;

    private String name;

    private boolean isDirectory;

    private CodeModelSourceFileDataDto data;

    private List<CodeModelSourceFileDto> children;

    @Override
    public CodeModelSourceFile convertToEntity() {
        CodeModelSourceFile entity = super.createEntityWithBaseValues(CodeModelSourceFile::new);
        entity.setName(this.getName());
        entity.setIsDirectory(this.isDirectory());

        if (this.getTreeNode() != null) {
            entity.setTreeNode(this.getTreeNode()
                                   .convertToEntity());
        }

        if (this.getData() != null) {
            entity.setData(this.getData()
                               .convertToEntity());
        }

        // Ignore the children field since this is a DTO-only field

        return entity;
    }

    public CodeModelSourceFileTreeNodeDto getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(CodeModelSourceFileTreeNodeDto treeNode) {
        this.treeNode = treeNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public CodeModelSourceFileDataDto getData() {
        return data;
    }

    public void setData(CodeModelSourceFileDataDto data) {
        this.data = data;
    }

    public List<CodeModelSourceFileDto> getChildren() {
        return children;
    }

    public void setChildren(List<CodeModelSourceFileDto> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceFileDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(isDirectory(), that.isDirectory())
                                  .append(getTreeNode(), that.getTreeNode())
                                  .append(getName(), that.getName())
                                  .append(getData(), that.getData())
                                  .append(getChildren(), that.getChildren())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getTreeNode())
                .append(getName())
                .append(isDirectory())
                .append(getData())
                .append(getChildren())
                .toHashCode();
    }

}
