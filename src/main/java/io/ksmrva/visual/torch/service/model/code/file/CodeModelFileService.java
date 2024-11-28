package io.ksmrva.visual.torch.service.model.code.file;

import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.node.CodeFileNodeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.CodeFileTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.extension.CodeTextFileExtensionDto;

import java.math.BigInteger;
import java.util.List;

public interface CodeModelFileService {

    CodeFileDto<?, ?> createCodeFileFromSystemFilePath(String systemFilePathString);

    CodeFileDto<?, ?> getFile(BigInteger fileId);

    List<CodeFileNodeDto> getAllChildNodes(BigInteger parentNodeId);

    List<CodeFileTypeDto> getFileTypes();

    List<CodeTextFileExtensionDto> getTextFileExtensions();

}
