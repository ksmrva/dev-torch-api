package io.ksmrva.visual.torch.domain.entity.model.code.file.type.directory;

import io.ksmrva.visual.torch.domain.dto.model.code.file.type.directory.CodeDirectoryFileDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.CodeFile;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@PrimaryKeyJoinColumn(name = "file_id")
@Table(name = "directory_file", schema = "code_model")
public class CodeDirectoryFile extends CodeFile<CodeDirectoryFileDto, CodeDirectoryFile> {

    @Override
    public CodeDirectoryFileDto convertToDto() {
        CodeDirectoryFileDto dto = super.createDtoWithBaseValues(CodeDirectoryFileDto::new);

        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeDirectoryFile that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

}
