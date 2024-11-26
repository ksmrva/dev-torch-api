package io.ksmrva.visual.torch.domain.dto.model.code.file.type.text;

import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.text.CodeTextFile;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodeTextFileDto extends CodeFileDto<CodeTextFileDto, CodeTextFile> {

    private String contents;

    @Override
    public CodeTextFile convertToEntity() {
        CodeTextFile entity = super.createEntityWithBaseValues(CodeTextFile::new);
        entity.setContents(this.getContents());

        return entity;
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

        if (!(o instanceof CodeTextFileDto that)) {
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
