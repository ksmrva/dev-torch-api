package io.ksmrva.visual.torch.service.model.code.source.file;

import io.ksmrva.visual.torch.db.dao.model.code.source.file.CodeModelFileDao;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.data.CodeModelSourceFileDataDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension.CodeModelSourceLanguageFileExtensionDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node.CodeModelSourceFileTreeNodeDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CodeModelSourceFileServiceImpl implements CodeModelSourceFileService {

    private static final Logger LOGGER = LogManager.getLogger(CodeModelSourceFileServiceImpl.class);

    private final CodeModelFileDao codeModelFileDao;

    public CodeModelSourceFileServiceImpl(CodeModelFileDao codeModelFileDao) {
        this.codeModelFileDao = codeModelFileDao;
    }

    @Override
    public CodeModelSourceFileDto createCodeFileFromSystemFilePath(String systemFilePathString) {
        Assert.notNull(systemFilePathString, "System File Path String must not be null");
        Path projectPath = Paths.get(systemFilePathString);

        return this.createCodeFileFromSystemFilePath(projectPath);
    }

    @Override
    public CodeModelSourceFileDto getFile(BigInteger fileId) {
        return this.codeModelFileDao.getFile(fileId, true);
    }

    @Override
    public List<CodeModelSourceFileTreeNodeDto> getAllChildNodes(BigInteger parentNodeId) {
        return this.codeModelFileDao.getAllChildNodes(parentNodeId);
    }

    @Override
    public List<CodeModelSourceLanguageFileExtensionDto> getFileCodeExtensions() {
        return this.codeModelFileDao.getFileCodeExtensions();
    }

    private CodeModelSourceFileDto createCodeFileFromSystemFilePath(Path systemFilePath) {
        Assert.notNull(systemFilePath, "System File Path must not be null");
        File projectFile = systemFilePath.toFile();

        return this.createCodeFileFromSystemFilePath(projectFile);
    }

    private CodeModelSourceFileDto createCodeFileFromSystemFilePath(File systemFilePath) {
        Assert.notNull(systemFilePath, "System File must not be null");
        if (!systemFilePath.exists()) {
            throw new IllegalArgumentException("System File does not exist at path [" + systemFilePath.getPath() + "]");
        }
        LOGGER.info("Creating Code File from System File at path [" + systemFilePath.getPath() + "]");

        String fileName = systemFilePath.getName();

        CodeModelSourceFileDto codeModelSourceFileDto = new CodeModelSourceFileDto();
        codeModelSourceFileDto.setName(fileName);

        if (systemFilePath.isDirectory()) {
            File[] directoryFiles = systemFilePath.listFiles();
            if (directoryFiles == null) {
                throw new IllegalArgumentException("System File [" + fileName + "] was a directory but produced a null file array for its child files");
            }
            LOGGER.info("[" + fileName + "] is a directory and contains [" + directoryFiles.length + "] files");
            List<CodeModelSourceFileDto> codeDirectoryFiles = new ArrayList<>();
            for (File directoryFile : Objects.requireNonNull(directoryFiles)) {
                CodeModelSourceFileDto codeFileFromDirectory = this.createCodeFileFromSystemFilePath(directoryFile);
                codeDirectoryFiles.add(codeFileFromDirectory);
            }
            codeModelSourceFileDto.setDirectory(true);
            codeModelSourceFileDto.setChildren(codeDirectoryFiles);

        } else {
            CodeModelSourceFileDataDto sourceFileData = new CodeModelSourceFileDataDto();
            codeModelSourceFileDto.setDirectory(false);

            byte[] fileBytes;
            try {
                fileBytes = com.google.common.io.Files.toByteArray(systemFilePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to convert the Code File into a byte array; System File Path [" + systemFilePath.getPath() + "]", e);
            }

            String codeFileExtension = com.google.common.io.Files.getFileExtension(fileName);
            if (this.isFileExtensionForCodeFile(codeFileExtension)) {
                sourceFileData.setIsBinary(false);
                sourceFileData.setTextContent(new String(fileBytes, StandardCharsets.UTF_8));

            } else {
                // TODO: Upload file to S3 and capture URL
                String fileContentDataUri = "s3://address-placeholder/" + fileName;

                sourceFileData.setIsBinary(true);
                sourceFileData.setBinaryContentUri(fileContentDataUri);
            }
            codeModelSourceFileDto.setData(sourceFileData);
        }

        LOGGER.info("Finished creating Code File from System File at path [" + systemFilePath.getPath() + "]");
        return codeModelSourceFileDto;
    }

    private boolean isFileExtensionForCodeFile(String fileExtensionToMatch) {
        List<CodeModelSourceLanguageFileExtensionDto> fileExtensionsForCodeFiles = this.codeModelFileDao.getFileCodeExtensions();

        return fileExtensionsForCodeFiles.stream()
                                         .anyMatch(fileExtensionForCodeFile -> fileExtensionForCodeFile.getExtension()
                                                                                                       .equals(fileExtensionToMatch));
    }

}
