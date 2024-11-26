package io.ksmrva.visual.torch.domain.dto.model.code.file.type.data;

import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.data.CodeDataFile;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodeDataFileDto extends CodeFileDto<CodeDataFileDto, CodeDataFile> {

    private byte[] contents;

    @Override
    public CodeDataFile convertToEntity() {
        CodeDataFile entity = super.createEntityWithBaseValues(CodeDataFile::new);
        entity.setContents(this.getContents());

        return entity;
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

        if (!(o instanceof CodeDataFileDto that)) {
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
