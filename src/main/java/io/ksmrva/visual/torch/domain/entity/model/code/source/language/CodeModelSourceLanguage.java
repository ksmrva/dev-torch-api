package io.ksmrva.visual.torch.domain.entity.model.code.source.language;

import io.ksmrva.visual.torch.domain.dto.model.code.source.language.CodeModelSourceLanguageDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "language", schema = "code_model_source")
public class CodeModelSourceLanguage extends AbstractBaseEntity<CodeModelSourceLanguageDto, CodeModelSourceLanguage> {

    @Column(name = "name")
    private String name;

    @Override
    public CodeModelSourceLanguageDto convertToDto() {
        CodeModelSourceLanguageDto dto = super.createDtoWithBaseValues(CodeModelSourceLanguageDto::new);
        dto.setName(this.getName());

        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceLanguage that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .toHashCode();
    }

}
