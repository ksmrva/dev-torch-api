package io.ksmrva.visual.torch.api.args.model.code;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CodeRepositoryDirectory {

    private String repositoryName;

    private Path path;

    private File file;

    public CodeRepositoryDirectory(String repositoryName, String filePath) {
        if (StringUtils.isEmpty(repositoryName)) {
            throw new IllegalArgumentException("Repository Name cannot be null or empty");
        }
        this.repositoryName = repositoryName;
        this.path = Paths.get(filePath);
        if (!Files.exists(this.path)) {
            throw new IllegalArgumentException("File does not exist at path [" + filePath + "]; Code Repository name [" + this.repositoryName + "]");
        }
        this.file = this.path.toFile();
        if (!this.file.isDirectory()) {
            throw new IllegalArgumentException("File is not a directory at path [" + filePath + "]");
        }
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public Path getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }
}
