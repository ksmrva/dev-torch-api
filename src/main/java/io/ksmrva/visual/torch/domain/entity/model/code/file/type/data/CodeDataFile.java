package io.ksmrva.visual.torch.domain.entity.model.code.file.type.data;

import io.ksmrva.visual.torch.domain.dto.model.code.file.type.data.CodeDataFileDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.CodeFile;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@PrimaryKeyJoinColumn(name = "file_id")
@Table(name = "data_file", schema = "code_model")
public class CodeDataFile extends CodeFile<CodeDataFileDto, CodeDataFile> {

    @Column(name = "contents")
    private byte[] contents;

    @Override
    public CodeDataFileDto convertToDto() {
        CodeDataFileDto dto = super.createDtoWithBaseValues(CodeDataFileDto::new);
        dto.setContents(this.getContents());

        return dto;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeDataFile that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getContents(), that.getContents())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getContents())
                .toHashCode();
    }

}
