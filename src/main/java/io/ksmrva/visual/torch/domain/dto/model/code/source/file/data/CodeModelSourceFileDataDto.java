package io.ksmrva.visual.torch.domain.dto.model.code.source.file.data;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.data.CodeModelSourceFileData;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

public class CodeModelSourceFileDataDto extends AbstractBaseDto<CodeModelSourceFileDataDto, CodeModelSourceFileData> {

    private BigInteger fileId;

    private boolean isBinary;

    private String binaryContentUri;

    private String textContent;

    @Override
    public CodeModelSourceFileData convertToEntity() {
        CodeModelSourceFileData entity = super.createEntityWithBaseValues(CodeModelSourceFileData::new);
        entity.setIsBinary(this.isBinary());
        entity.setBinaryContentUri(this.getBinaryContentUri());
        entity.setTextContent(this.getTextContent());

        return entity;
    }

    public BigInteger getFileId() {
        return fileId;
    }

    public void setFileId(BigInteger fileId) {
        this.fileId = fileId;
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

        if (!(o instanceof CodeModelSourceFileDataDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getFileId(), that.getFileId())
                                  .append(isBinary(), that.isBinary())
                                  .append(getBinaryContentUri(), that.getBinaryContentUri())
                                  .append(getTextContent(), that.getTextContent())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getFileId())
                .append(isBinary())
                .append(getBinaryContentUri())
                .append(getTextContent())
                .toHashCode();
    }

}
