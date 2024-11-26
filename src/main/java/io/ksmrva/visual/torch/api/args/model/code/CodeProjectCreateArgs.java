package io.ksmrva.visual.torch.api.args.model.code;

public class CodeProjectCreateArgs {

    private final String projectName;

    private final String directoryPath;

    private final FileExtensionMatcher fileExtensionMatcher;

    public CodeProjectCreateArgs(String projectName, String directoryPath, FileExtensionMatcher fileExtensionMatcher) {
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
