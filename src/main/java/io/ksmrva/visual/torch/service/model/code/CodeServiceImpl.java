package io.ksmrva.visual.torch.service.model.code;

import io.ksmrva.visual.torch.controller.args.model.code.CodeRepositoryDirectory;
import io.ksmrva.visual.torch.controller.args.model.code.CodeRepositoryProjectDirectoryDescription;
import io.ksmrva.visual.torch.controller.args.model.code.FileExtensionMatcher;
import io.ksmrva.visual.torch.domain.entity.model.code.Code;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CodeServiceImpl implements CodeService {

    private static final Logger LOGGER = LogManager.getLogger(CodeServiceImpl.class);

    @Override
    public List<Code> createCodeBlocksFromProjectDirectory(CodeRepositoryProjectDirectoryDescription projectDirectoryDescription) {
        List<Code> codeBlocks = new ArrayList<>();
        CodeRepositoryDirectory codeRepositoryDirectory = new CodeRepositoryDirectory(projectDirectoryDescription.getProjectName(),
                                                                                      projectDirectoryDescription.getDirectoryPath());
        FileExtensionMatcher fileExtensionMatcher = projectDirectoryDescription.getFileExtensionMatcher();
        File codeRepositoryDirectoryFile = codeRepositoryDirectory.getPath().toFile();
        for (File codeRepositoryFile : Objects.requireNonNull(codeRepositoryDirectoryFile.listFiles())) {
            codeBlocks.addAll(convertProjectDirectoryIntoCodeBlocks(codeRepositoryFile, fileExtensionMatcher));
        }

        return codeBlocks;
    }

    private List<Code> convertProjectDirectoryIntoCodeBlocks(File projectDirectoryFile, FileExtensionMatcher fileExtensionMatcher) {
        List<Code> codeBlocks = new ArrayList<>();
        if (projectDirectoryFile.isDirectory()) {
            for (File codeRepositoryFile : Objects.requireNonNull(projectDirectoryFile.listFiles())) {
                codeBlocks.addAll(convertProjectDirectoryIntoCodeBlocks(codeRepositoryFile, fileExtensionMatcher));
            }
        } else if (projectDirectoryFile.canRead()) {
            String fileName = projectDirectoryFile.getName();
            int fileExtensionIndex = fileName.lastIndexOf(".");
            if (fileExtensionIndex == -1
                || fileExtensionIndex == (fileName.length() - 1)) {
                LOGGER.info("File has no extension, skipping");
            } else {
                String fileExtension = fileName.substring(fileExtensionIndex + 1);
                if (fileExtensionMatcher.isAcceptable(fileExtension)) {
                    codeBlocks.add(createCodeBlockFromFileContents(projectDirectoryFile));
                } else {
                    LOGGER.info("Skipping file with path [" + projectDirectoryFile.getAbsolutePath() + "] due to extension");
                }
            }
        } else {
            throw new RuntimeException("Failed to ingest code repository due to contained file [" + projectDirectoryFile.getAbsolutePath() + "] being neither a directory nor a readable file");
        }
        return codeBlocks;
    }

    private Code createCodeBlockFromFileContents(File projectDirectoryFile) {
        try {
            String fileContents = Files.readString(projectDirectoryFile.toPath());

            Code codeBlock = new Code();
            // TODO: Auto-detect language from file extension
            codeBlock.setLanguage("Java");
            codeBlock.setSourceFileName(projectDirectoryFile.getName());
            codeBlock.setSourceFilePath(projectDirectoryFile.getAbsolutePath());
            codeBlock.setValue(fileContents);

            return codeBlock;
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file contents as a String due to [" + e.getLocalizedMessage() + "]; path [" + projectDirectoryFile.getAbsolutePath() + "]", e);
        }
    }

}
