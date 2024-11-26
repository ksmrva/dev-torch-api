package io.ksmrva.visual.torch.domain.entity.model.code.file.type.text.extension;

import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.CodeTextFileExtensionDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "text_file_extension", schema = "code_model")
public class CodeTextFileExtension extends AbstractBaseEntity<CodeTextFileExtensionDto, CodeTextFileExtension> {

    @Column(name = "extension")
    private String extension;

    @Override
    public CodeTextFileExtensionDto convertToDto() {
        CodeTextFileExtensionDto dto = super.createDtoWithBaseValues(CodeTextFileExtensionDto::new);
        dto.setExtension(this.getExtension());

        return dto;
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

        if (!(o instanceof CodeTextFileExtension that)) {
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
