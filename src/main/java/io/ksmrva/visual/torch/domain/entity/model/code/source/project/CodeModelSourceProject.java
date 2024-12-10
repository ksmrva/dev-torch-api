package io.ksmrva.visual.torch.domain.entity.model.code.source.project;

import io.ksmrva.visual.torch.domain.dto.model.code.source.project.CodeModelSourceProjectDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.tree.node.CodeModelSourceFileTreeNode;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "project", schema = "code_model_source")
public class CodeModelSourceProject extends AbstractBaseEntity<CodeModelSourceProjectDto, CodeModelSourceProject> {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_tree_node_id")
    private CodeModelSourceFileTreeNode rootTreeNode;

    @Override
    public CodeModelSourceProjectDto convertToDto() {
        CodeModelSourceProjectDto dto = super.createDtoWithBaseValues(CodeModelSourceProjectDto::new);
        dto.setName(this.getName());

        if (this.getRootTreeNode() != null) {
            dto.setRootTreeNode(this.getRootTreeNode()
                                    .convertToDto());
        }

        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodeModelSourceFileTreeNode getRootTreeNode() {
        return rootTreeNode;
    }

    public void setRootTreeNode(CodeModelSourceFileTreeNode rootFileNode) {
        this.rootTreeNode = rootFileNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceProject that)) {
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
