package io.ksmrva.visual.torch.domain.dto.model.code;

public class CodeDto {
//public class CodeDto extends AbstractBaseDto {

    private long id = -1;

    private String sourceFileName = null;

    private String sourceFilePath = null;

    private String language = null;

    private String value = null;

    public CodeDto() {
    }

    public CodeDto(long id, String sourceFileName, String sourceFilePath, String language, String value) {
        this.id = id;
        this.sourceFileName = sourceFilePath;
        this.sourceFilePath = sourceFilePath;
        this.language = language;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
