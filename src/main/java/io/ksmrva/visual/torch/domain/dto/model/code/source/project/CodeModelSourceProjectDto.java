package io.ksmrva.visual.torch.domain.dto.model.code.source.project;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node.CodeModelSourceFileTreeNodeDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.project.CodeModelSourceProject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodeModelSourceProjectDto extends AbstractBaseDto<CodeModelSourceProjectDto, CodeModelSourceProject> {

    private String name;

    private CodeModelSourceFileTreeNodeDto rootTreeNode;

    @Override
    public CodeModelSourceProject convertToEntity() {
        CodeModelSourceProject entity = super.createEntityWithBaseValues(CodeModelSourceProject::new);
        entity.setName(this.getName());
        if (this.getRootTreeNode() != null) {
            entity.setRootTreeNode(this.getRootTreeNode()
                                       .convertToEntity());
        }

        return entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodeModelSourceFileTreeNodeDto getRootTreeNode() {
        return rootTreeNode;
    }

    public void setRootTreeNode(CodeModelSourceFileTreeNodeDto rootTreeNode) {
        this.rootTreeNode = rootTreeNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceProjectDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getRootTreeNode(), that.getRootTreeNode())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getRootTreeNode())
                .toHashCode();
    }

}
