package io.ksmrva.visual.torch.domain.dto.model.code.source.language;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.language.CodeModelSourceLanguage;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodeModelSourceLanguageDto extends AbstractBaseDto<CodeModelSourceLanguageDto, CodeModelSourceLanguage> {

    private String name;

    @Override
    public CodeModelSourceLanguage convertToEntity() {
        CodeModelSourceLanguage entity = super.createEntityWithBaseValues(CodeModelSourceLanguage::new);
        entity.setName(this.getName());

        return entity;
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

        if (!(o instanceof CodeModelSourceLanguageDto that)) {
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
