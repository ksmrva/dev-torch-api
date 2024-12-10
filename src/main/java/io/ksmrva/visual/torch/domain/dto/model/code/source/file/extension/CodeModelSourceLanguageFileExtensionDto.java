package io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.language.CodeModelSourceLanguageDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.extension.CodeModelSourceLanguageFileExtension;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodeModelSourceLanguageFileExtensionDto extends AbstractBaseDto<CodeModelSourceLanguageFileExtensionDto, CodeModelSourceLanguageFileExtension> {

    private String extension;

    private CodeModelSourceLanguageDto language;

    @Override
    public CodeModelSourceLanguageFileExtension convertToEntity() {
        CodeModelSourceLanguageFileExtension entity = super.createEntityWithBaseValues(CodeModelSourceLanguageFileExtension::new);
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

    public CodeModelSourceLanguageDto getLanguage() {
        return language;
    }

    public void setLanguage(CodeModelSourceLanguageDto language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceLanguageFileExtensionDto that)) {
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
