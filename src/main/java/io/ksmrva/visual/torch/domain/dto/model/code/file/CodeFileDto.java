package io.ksmrva.visual.torch.domain.dto.model.code.file;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.node.CodeFileNodeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.CodeFileTypeDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.CodeFile;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.data.CodeDataFile;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.directory.CodeDirectoryFile;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.text.CodeTextFile;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.function.Supplier;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(value = CodeTextFile.class, name = "text"),
               @JsonSubTypes.Type(value = CodeDataFile.class, name = "data"),
               @JsonSubTypes.Type(value = CodeDirectoryFile.class, name = "directory")})
public abstract class CodeFileDto<D extends CodeFileDto<D, E>, E extends CodeFile<D, E>> extends AbstractBaseDto<D, E> {

    private CodeFileNodeDto fileNode;

    private CodeFileTypeDto fileType;

    private String name;

    protected E createEntityWithBaseValues(Supplier<E> entitySupplier) {
        E entity = null;
        if (entitySupplier != null) {
            entity = super.createEntityWithBaseValues(entitySupplier);
            entity.setName(this.getName());

            if (this.getFileNode() != null) {
                entity.setFileNode(this.getFileNode()
                                       .convertToEntity());
            }

            if (this.getFileType() != null) {
                entity.setFileType(this.getFileType()
                                       .convertToEntity());
            }

        }
        return entity;
    }

    public CodeFileNodeDto getFileNode() {
        return fileNode;
    }

    public void setFileNode(CodeFileNodeDto fileNode) {
        this.fileNode = fileNode;
    }

    public CodeFileTypeDto getFileType() {
        return fileType;
    }

    public void setFileType(CodeFileTypeDto fileType) {
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

        if (!(o instanceof CodeFileDto<?, ?> that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getFileNode(), that.getFileNode())
                                  .append(getFileType(), that.getFileType())
                                  .append(getName(), that.getName())
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
