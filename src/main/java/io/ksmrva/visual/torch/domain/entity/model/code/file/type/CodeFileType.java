package io.ksmrva.visual.torch.domain.entity.model.code.file.type;

import io.ksmrva.visual.torch.domain.dto.model.code.file.type.CodeFileTypeDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "file_type", schema = "code_model")
public class CodeFileType extends AbstractBaseEntity<CodeFileTypeDto, CodeFileType> {

    @Column(name = "name")
    private String name;

    @Override
    public CodeFileTypeDto convertToDto() {
        CodeFileTypeDto dto = super.createDtoWithBaseValues(CodeFileTypeDto::new);
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

        if (!(o instanceof CodeFileType that)) {
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
