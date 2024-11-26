package io.ksmrva.visual.torch.service.model.code.block;

import io.ksmrva.visual.torch.controller.args.model.code.CodeProjectCreateArgs;
import io.ksmrva.visual.torch.controller.args.model.code.CodeRepositoryDirectory;
import io.ksmrva.visual.torch.controller.args.model.code.FileExtensionMatcher;
import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.data.CodeDataFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.directory.CodeDirectoryFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.CodeTextFileDto;
import io.ksmrva.visual.torch.domain.entity.model.code.block.Code;
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
public class CodeModelBlockServiceImpl implements CodeModelBlockService {

    private static final Logger LOGGER = LogManager.getLogger(CodeModelBlockServiceImpl.class);

    @Override
    public List<Code> createCodeBlocksFromProjectDirectory(CodeProjectCreateArgs projectDirectoryDescription) {
        List<Code> codeBlocks = new ArrayList<>();
        CodeRepositoryDirectory codeRepositoryDirectory = new CodeRepositoryDirectory(projectDirectoryDescription.getProjectName(),
                                                                                      projectDirectoryDescription.getDirectoryPath());
        FileExtensionMatcher fileExtensionMatcher = projectDirectoryDescription.getFileExtensionMatcher();
        File codeRepositoryDirectoryFile = codeRepositoryDirectory.getPath()
                                                                  .toFile();
        for (File codeRepositoryFile : Objects.requireNonNull(codeRepositoryDirectoryFile.listFiles())) {
            codeBlocks.addAll(convertProjectDirectoryIntoCodeBlocks(codeRepositoryFile, fileExtensionMatcher));
        }

        return codeBlocks;
    }

    private List<Code> convertProjectDirectoryIntoCodeBlocks(File projectDirectory, FileExtensionMatcher fileExtensionMatcher) {
        List<Code> codeBlocks = new ArrayList<>();
        if (projectDirectory.isDirectory()) {
            for (File codeRepositoryFile : Objects.requireNonNull(projectDirectory.listFiles())) {
                codeBlocks.addAll(convertProjectDirectoryIntoCodeBlocks(codeRepositoryFile, fileExtensionMatcher));
            }
        } else if (projectDirectory.canRead()) {
            String fileName = projectDirectory.getName();
            int fileExtensionIndex = fileName.lastIndexOf(".");
            if (fileExtensionIndex == -1
                || fileExtensionIndex == (fileName.length() - 1)) {
                LOGGER.info("File has no extension, skipping");
            } else {
                String fileExtension = fileName.substring(fileExtensionIndex + 1);
                if (fileExtensionMatcher.isAcceptable(fileExtension)) {
                    codeBlocks.add(createCodeBlockFromFileContents(projectDirectory));
                } else {
                    LOGGER.info("Skipping file with path [" + projectDirectory.getAbsolutePath() + "] due to extension");
                }
            }
        } else {
            throw new RuntimeException("Failed to ingest code repository due to contained file [" + projectDirectory.getAbsolutePath() + "] being neither a directory nor a readable file");
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

    private void printCodeFiles(CodeFileDto<?, ?> codeFile) {
        this.printCodeFiles(codeFile, "");
    }

    private void printCodeFiles(CodeFileDto<?, ?> codeFileToPrint, String spacing) {
        switch (codeFileToPrint) {
            case CodeDirectoryFileDto codeDirectoryFile -> {
                List<CodeFileDto<?, ?>> codeDirectoryFiles = codeDirectoryFile.getNestedFiles();
                System.out.println(spacing + "Directory: " + codeDirectoryFile.getName());
                for (CodeFileDto<?, ?> nestedCodeFile : codeDirectoryFiles) {
                    this.printCodeFiles(nestedCodeFile, spacing + "  ");
                }
            }
            case CodeDataFileDto codeDataFileDto -> {
                System.out.println(spacing + "Data: " + codeDataFileDto.getName());
            }
            case CodeTextFileDto codeTextFileDto -> {
                System.out.println(spacing + "Text: " + codeTextFileDto.getName());
            }
            case null, default ->
                    throw new IllegalArgumentException("Unrecognized Code File type [" + codeFileToPrint.getClass()
                                                                                                        .getSimpleName() + "]");
        }
    }

}
