package io.ksmrva.visual.torch.domain.entity.model.code.file.type.text;

import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.CodeTextFileDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.CodeFile;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@PrimaryKeyJoinColumn(name = "file_id")
@Table(name = "text_file", schema = "code_model")
public class CodeTextFile extends CodeFile<CodeTextFileDto, CodeTextFile> {

    @Column(name = "contents")
    private String contents;

    @Override
    public CodeTextFileDto convertToDto() {
        CodeTextFileDto dto = super.createDtoWithBaseValues(CodeTextFileDto::new);
        dto.setContents(this.getContents());

        return dto;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeTextFile that)) {
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
