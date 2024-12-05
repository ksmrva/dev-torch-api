package io.ksmrva.visual.torch.service.model.code.source.file;

import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension.CodeModelSourceFileCodeExtensionDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node.CodeModelSourceFileTreeNodeDto;

import java.math.BigInteger;
import java.util.List;

public interface CodeModelSourceFileService {

    CodeModelSourceFileDto createCodeFileFromSystemFilePath(String systemFilePathString);

    CodeModelSourceFileDto getFile(BigInteger fileId);

    List<CodeModelSourceFileTreeNodeDto> getAllChildNodes(BigInteger parentNodeId);

    List<CodeModelSourceFileCodeExtensionDto> getFileCodeExtensions();

}
