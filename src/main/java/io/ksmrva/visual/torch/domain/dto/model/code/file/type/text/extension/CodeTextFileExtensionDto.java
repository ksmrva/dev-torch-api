package io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.extension;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.text.extension.CodeTextFileExtension;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodeTextFileExtensionDto extends AbstractBaseDto<CodeTextFileExtensionDto, CodeTextFileExtension> {

    private String extension;

    @Override
    public CodeTextFileExtension convertToEntity() {
        CodeTextFileExtension entity = super.createEntityWithBaseValues(CodeTextFileExtension::new);
        entity.setExtension(this.getExtension());

        return entity;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeTextFileExtensionDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getExtension(), that.getExtension())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getExtension())
                .toHashCode();
    }

}
