package io.ksmrva.visual.torch.domain.entity.model.code.language;

import io.ksmrva.visual.torch.domain.dto.model.code.language.CodeModelLanguageDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "language", schema = "code_model")
public class CodeModelLanguage extends AbstractBaseEntity<CodeModelLanguageDto, CodeModelLanguage> {

    @Column(name = "name")
    private String name;

    @Override
    public CodeModelLanguageDto convertToDto() {
        CodeModelLanguageDto dto = super.createDtoWithBaseValues(CodeModelLanguageDto::new);
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

        if (!(o instanceof CodeModelLanguage that)) {
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
