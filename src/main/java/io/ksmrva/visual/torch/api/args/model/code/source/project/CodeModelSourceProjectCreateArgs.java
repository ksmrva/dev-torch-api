package io.ksmrva.visual.torch.api.args.model.code.source.project;

import io.ksmrva.visual.torch.api.args.model.code.source.file.extension.FileExtensionMatcher;

public class CodeModelSourceProjectCreateArgs {

    private final String name;

    private final String path;

    private final FileExtensionMatcher fileExtensionMatcher;

    public CodeModelSourceProjectCreateArgs(String name, String path, FileExtensionMatcher fileExtensionMatcher) {
        this.name = name;
        this.path = path;
        this.fileExtensionMatcher = fileExtensionMatcher;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public FileExtensionMatcher getFileExtensionMatcher() {
        return fileExtensionMatcher;
    }

}
