package io.ksmrva.visual.torch.domain.dto.model.code.project;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.node.CodeFileNodeDto;
import io.ksmrva.visual.torch.domain.entity.model.code.project.CodeProject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodeProjectDto extends AbstractBaseDto<CodeProjectDto, CodeProject> {

    private String name;

    private CodeFileNodeDto rootFileNode;

    @Override
    public CodeProject convertToEntity() {
        CodeProject entity = super.createEntityWithBaseValues(CodeProject::new);
        entity.setName(this.getName());
        entity.setRootFileNode(this.getRootFileNode()
                                   .convertToEntity());

        return entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodeFileNodeDto getRootFileNode() {
        return rootFileNode;
    }

    public void setRootFileNode(CodeFileNodeDto rootFileNode) {
        this.rootFileNode = rootFileNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeProjectDto that)) {
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
