package io.ksmrva.visual.torch.domain.dto.model.code.file.type.directory;

import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.directory.CodeDirectoryFile;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class CodeDirectoryFileDto extends CodeFileDto<CodeDirectoryFileDto, CodeDirectoryFile> {

    private List<CodeFileDto<?, ?>> nestedFiles;

    @Override
    public CodeDirectoryFile convertToEntity() {
        CodeDirectoryFile entity = super.createEntityWithBaseValues(CodeDirectoryFile::new);
        // Ignore nestedFiles as it is a DTO-only field

        return entity;
    }

    public List<CodeFileDto<?, ?>> getNestedFiles() {
        return nestedFiles;
    }

    public void setNestedFiles(List<CodeFileDto<?, ?>> nestedFiles) {
        this.nestedFiles = nestedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeDirectoryFileDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getNestedFiles(), that.getNestedFiles())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getNestedFiles())
                .toHashCode();
    }

}
