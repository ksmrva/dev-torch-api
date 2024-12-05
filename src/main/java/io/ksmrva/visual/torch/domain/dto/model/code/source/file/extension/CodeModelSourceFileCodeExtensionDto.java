package io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.code.language.CodeModelLanguageDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.extension.CodeModelSourceFileCodeExtension;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodeModelSourceFileCodeExtensionDto extends AbstractBaseDto<CodeModelSourceFileCodeExtensionDto, CodeModelSourceFileCodeExtension> {

    private String extension;

    private CodeModelLanguageDto language;

    @Override
    public CodeModelSourceFileCodeExtension convertToEntity() {
        CodeModelSourceFileCodeExtension entity = super.createEntityWithBaseValues(CodeModelSourceFileCodeExtension::new);
        entity.setExtension(this.getExtension());
        if (this.getLanguage() != null) {
            entity.setLanguage(this.getLanguage()
                                   .convertToEntity());
        }

        return entity;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public CodeModelLanguageDto getLanguage() {
        return language;
    }

    public void setLanguage(CodeModelLanguageDto language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceFileCodeExtensionDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getExtension(), that.getExtension())
                                  .append(getLanguage(), that.getLanguage())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getExtension())
                .append(getLanguage())
                .toHashCode();
    }

}
