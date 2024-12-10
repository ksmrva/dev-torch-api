package io.ksmrva.visual.torch.domain.entity.model.code.source.file.extension;

import io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension.CodeModelSourceLanguageFileExtensionDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.code.source.language.CodeModelSourceLanguage;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "language_file_extension", schema = "code_model_source")
public class CodeModelSourceLanguageFileExtension extends AbstractBaseEntity<CodeModelSourceLanguageFileExtensionDto, CodeModelSourceLanguageFileExtension> {

    @Column(name = "extension")
    private String extension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private CodeModelSourceLanguage language;

    @Override
    public CodeModelSourceLanguageFileExtensionDto convertToDto() {
        CodeModelSourceLanguageFileExtensionDto dto = super.createDtoWithBaseValues(CodeModelSourceLanguageFileExtensionDto::new);
        dto.setExtension(this.getExtension());

        if (this.getLanguage() != null) {
            dto.setLanguage(this.getLanguage()
                                .convertToDto());
        }

        return dto;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public CodeModelSourceLanguage getLanguage() {
        return language;
    }

    public void setLanguage(CodeModelSourceLanguage language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceLanguageFileExtension that)) {
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
