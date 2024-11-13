package io.ksmrva.visual.torch.domain.entity.model.code;

import jakarta.persistence.*;

@Entity
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "source_file_name")
    private String sourceFileName;

    @Column(name = "source_file_path")
    private String sourceFilePath;

    @Column(name = "language")
    private String language;

    @Column(name = "value")
    private String value;

    public Code() {

    }

    public Code(int id, String sourceFileName, String sourceFilePath, String language, String value) {
        this.id = id;
        this.sourceFileName = sourceFileName;
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
    public void setSourceFilePath(String sourceFileLocation) {
        this.sourceFilePath = sourceFileLocation;
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
    public void setValue(String code) {
        this.value = code;
    }
}
