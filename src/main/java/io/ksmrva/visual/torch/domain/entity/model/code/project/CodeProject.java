package io.ksmrva.visual.torch.domain.entity.model.code.project;

import io.ksmrva.visual.torch.domain.dto.model.code.project.CodeProjectDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.code.file.node.CodeFileNode;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "project", schema = "code_model")
public class CodeProject extends AbstractBaseEntity<CodeProjectDto, CodeProject> {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_file_node_id")
    private CodeFileNode rootFileNode;

    @Override
    public CodeProjectDto convertToDto() {
        CodeProjectDto dto = super.createDtoWithBaseValues(CodeProjectDto::new);
        dto.setName(this.getName());
        dto.setRootFileNode(this.getRootFileNode()
                                .convertToDto());

        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodeFileNode getRootFileNode() {
        return rootFileNode;
    }

    public void setRootFileNode(CodeFileNode rootFileNode) {
        this.rootFileNode = rootFileNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeProject that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getRootFileNode(), that.getRootFileNode())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getRootFileNode())
                .toHashCode();
    }

}
