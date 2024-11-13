package io.ksmrva.visual.torch.controller.args.model.code;

public class CodeRepositoryProjectDirectoryDescription {

    private final String projectName;

    private final String directoryPath;

    private final FileExtensionMatcher fileExtensionMatcher;

    public CodeRepositoryProjectDirectoryDescription(String projectName, String directoryPath, FileExtensionMatcher fileExtensionMatcher) {
        this.projectName = projectName;
        this.directoryPath = directoryPath;
        this.fileExtensionMatcher = fileExtensionMatcher;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public FileExtensionMatcher getFileExtensionMatcher() {
        return fileExtensionMatcher;
    }
}
