package io.ksmrva.visual.torch.domain.entity.model.code.source.file.data;

import io.ksmrva.visual.torch.domain.dto.model.code.source.file.data.CodeModelSourceFileDataDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.CodeModelSourceFile;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "source_file_data", schema = "code_model")
public class CodeModelSourceFileData extends AbstractBaseEntity<CodeModelSourceFileDataDto, CodeModelSourceFileData> {

    @OneToOne
    @JoinColumn(name = "file_id")
    private CodeModelSourceFile file;

    @Column(name = "is_binary")
    private boolean isBinary;

    @Column(name = "binary_content_uri")
    private String binaryContentUri;

    @Column(name = "text_content")
    private String textContent;

    @Override
    public CodeModelSourceFileDataDto convertToDto() {
        CodeModelSourceFileDataDto dto = super.createDtoWithBaseValues(CodeModelSourceFileDataDto::new);
        dto.setIsBinary(this.isBinary());
        dto.setBinaryContentUri(this.getBinaryContentUri());
        dto.setTextContent(this.getTextContent());

        if (this.getFile() != null) {
            dto.setFileId(this.getFile()
                              .getId());
        }

        return dto;
    }

    public CodeModelSourceFile getFile() {
        return file;
    }

    public void setFile(CodeModelSourceFile file) {
        this.file = file;
    }

    public boolean isBinary() {
        return isBinary;
    }

    public void setIsBinary(boolean isBinary) {
        this.isBinary = isBinary;
    }

    public String getBinaryContentUri() {
        return binaryContentUri;
    }

    public void setBinaryContentUri(String binaryContentUri) {
        this.binaryContentUri = binaryContentUri;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceFileData that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(isBinary(), that.isBinary())
                                  .append(getFile(), that.getFile())
                                  .append(getBinaryContentUri(), that.getBinaryContentUri())
                                  .append(getTextContent(), that.getTextContent())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getFile())
                .append(isBinary())
                .append(getBinaryContentUri())
                .append(getTextContent())
                .toHashCode();
    }

}
