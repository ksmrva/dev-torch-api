package io.ksmrva.visual.torch.service.model.code.file;

import io.ksmrva.visual.torch.db.dao.model.code.file.CodeModelFileDao;
import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.node.CodeFileNodeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.CodeFileTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.data.CodeDataFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.directory.CodeDirectoryFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.CodeTextFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.CodeTextFileExtensionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CodeModelFileServiceImpl implements CodeModelFileService {

    private static final Logger LOGGER = LogManager.getLogger(CodeModelFileServiceImpl.class);

    private final CodeModelFileDao codeModelFileDao;

    public CodeModelFileServiceImpl(CodeModelFileDao codeModelFileDao) {
        this.codeModelFileDao = codeModelFileDao;
    }

    @Override
    public CodeFileDto<?, ?> createCodeFileFromSystemFilePath(String systemFilePathString) {
        Assert.notNull(systemFilePathString, "System File Path String must not be null");
        Path projectPath = Paths.get(systemFilePathString);

        return this.createCodeFileFromSystemFilePath(projectPath);
    }

    @Override
    public CodeFileDto<?, ?> getFile(BigInteger fileId) {
        return this.codeModelFileDao.getFile(fileId, true);
    }

    @Override
    public List<CodeFileNodeDto> getAllChildNodes(BigInteger parentNodeId) {
        return this.codeModelFileDao.getAllChildNodes(parentNodeId);
    }

    @Override
    public List<CodeFileTypeDto> getFileTypes() {
        return this.codeModelFileDao.getFileTypes();
    }

    @Override
    public List<CodeTextFileExtensionDto> getTextFileExtensions() {
        return this.codeModelFileDao.getTextFileExtensions();
    }

    private CodeFileDto<?, ?> createCodeFileFromSystemFilePath(Path systemFilePath) {
        Assert.notNull(systemFilePath, "System File Path must not be null");
        File projectFile = systemFilePath.toFile();

        return this.createCodeFileFromSystemFilePath(projectFile);
    }

    private CodeFileDto<?, ?> createCodeFileFromSystemFilePath(File systemFilePath) {
        Assert.notNull(systemFilePath, "System File must not be null");
        if (!systemFilePath.exists()) {
            throw new IllegalArgumentException("System File does not exist at path [" + systemFilePath.getPath() + "]");
        }
        LOGGER.info("Creating Code File from System File at path [" + systemFilePath.getPath() + "]");

        CodeFileDto<?, ?> codeFileDto;
        String codeFileName = systemFilePath.getName();
        CodeFileTypeDto codeFileTypeDto;

        if (systemFilePath.isDirectory()) {
            File[] directoryFiles = systemFilePath.listFiles();
            if (directoryFiles == null) {
                throw new IllegalArgumentException("Code File [" + codeFileName + "] was a directory but produced a null file array for its child files");
            }
            LOGGER.info("[" + codeFileName + "] is a directory and contains [" + directoryFiles.length + "] files");
            List<CodeFileDto<?, ?>> codeDirectoryFiles = new ArrayList<>();
            for (File directoryFile : Objects.requireNonNull(directoryFiles)) {
                CodeFileDto<?, ?> codeFileFromDirectory = this.createCodeFileFromSystemFilePath(directoryFile);
                codeDirectoryFiles.add(codeFileFromDirectory);
            }
            CodeDirectoryFileDto codeDirectoryFile = new CodeDirectoryFileDto();
            codeDirectoryFile.setNestedFiles(codeDirectoryFiles);

            codeFileTypeDto = this.getDirectoryCodeFileType();
            codeFileDto = codeDirectoryFile;

        } else {
            byte[] fileBytes;
            try {
                fileBytes = com.google.common.io.Files.toByteArray(systemFilePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to convert the Code File into a byte array; File Path [" + systemFilePath.getPath() + "]", e);
            }

            String codeFileExtension = com.google.common.io.Files.getFileExtension(codeFileName);
            if (this.isFileExtensionForTextFile(codeFileExtension)) {
                CodeTextFileDto codeTextFileDto = new CodeTextFileDto();

                String codeFileText = new String(fileBytes, Charset.defaultCharset());
                codeTextFileDto.setContents(codeFileText);

                codeFileTypeDto = this.getTextCodeFileType();
                codeFileDto = codeTextFileDto;

            } else {
                CodeDataFileDto codeDataFileDto = new CodeDataFileDto();
                // codeDataFileDto.setContents(fileBytes);
                codeDataFileDto.setContents(new byte[0]);

                codeFileTypeDto = this.getDataCodeFileType();
                codeFileDto = codeDataFileDto;

            }
        }

        codeFileDto.setName(codeFileName);
        codeFileDto.setFileType(codeFileTypeDto);

        LOGGER.info("Finished creating Code File from Project File at path [" + systemFilePath.getPath() + "]");
        return codeFileDto;
    }

    private CodeFileTypeDto getTextCodeFileType() {
        return this.getFileTypeByName("text");
    }

    private CodeFileTypeDto getDataCodeFileType() {
        return this.getFileTypeByName("data");
    }

    private CodeFileTypeDto getDirectoryCodeFileType() {
        return this.getFileTypeByName("directory");
    }

    private CodeFileTypeDto getFileTypeByName(String name) {
        return this.codeModelFileDao.getFileTypes()
                                    .stream()
                                    .filter(codeFileTypeDto -> codeFileTypeDto.getName()
                                                                              .equals(name))
                                    .findFirst()
                                    .orElse(null);
    }

    private boolean isFileExtensionForTextFile(String fileExtensionToMatch) {
        List<CodeTextFileExtensionDto> fileExtensionsForTextFiles = this.codeModelFileDao.getTextFileExtensions();

        return fileExtensionsForTextFiles.stream()
                                         .anyMatch(fileExtensionForTextFiles -> fileExtensionForTextFiles.getExtension()
                                                                                                         .equals(fileExtensionToMatch));
    }

}
