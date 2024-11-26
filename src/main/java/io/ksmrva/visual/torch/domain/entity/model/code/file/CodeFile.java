package io.ksmrva.visual.torch.domain.entity.model.code.file;

import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.code.file.node.CodeFileNode;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.CodeFileType;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.util.function.Supplier;

@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "file", schema = "code_model")
public class CodeFile<D extends CodeFileDto<D, E>, E extends CodeFile<D, E>> extends AbstractBaseEntity<D, E> {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_node_id")
    private CodeFileNode fileNode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_type_id")
    private CodeFileType fileType;

    @Column(name = "name")
    private String name;

    @Override
    public D convertToDto() {
        throw new UnsupportedOperationException("Code File can not be converted directly into a DTO, must use a sub-class such as a Text or Data File");
    }

    protected D createDtoWithBaseValues(Supplier<D> dtoSupplier) {
        D dto = null;
        if (dtoSupplier != null) {
            dto = super.createDtoWithBaseValues(dtoSupplier);
            dto.setName(this.getName());

            if (this.getFileNode() != null) {
                dto.setFileNode(this.getFileNode()
                                    .convertToDto());
            }

            if (this.getFileType() != null) {
                dto.setFileType(this.getFileType()
                                    .convertToDto());
            }
        }
        return dto;
    }

    public CodeFileNode getFileNode() {
        return fileNode;
    }

    public void setFileNode(CodeFileNode fileNode) {
        this.fileNode = fileNode;
    }

    public CodeFileType getFileType() {
        return fileType;
    }

    public void setFileType(CodeFileType fileType) {
        this.fileType = fileType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeFile<?, ?> codeFile)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getFileNode(), codeFile.getFileNode())
                                  .append(getFileType(), codeFile.getFileType())
                                  .append(getName(), codeFile.getName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getFileNode())
                .append(getFileType())
                .append(getName())
                .toHashCode();
    }

}
