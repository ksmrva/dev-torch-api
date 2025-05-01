package io.ksmrva.visual.torch.service.model.code.source.file;

import io.ksmrva.visual.torch.data.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.data.dto.model.code.source.file.extension.CodeModelSourceLanguageFileExtensionDto;
import io.ksmrva.visual.torch.data.dto.model.code.source.file.tree.node.CodeModelSourceFileTreeNodeDto;
import io.ksmrva.visual.torch.service.common.explorer.panel.list.entry.provider.ExplorerPanelListServiceEntryProvider;

import java.math.BigInteger;
import java.util.List;

public interface CodeModelSourceFileService extends ExplorerPanelListServiceEntryProvider {

    CodeModelSourceFileDto createCodeFileFromSystemFilePath(String systemFilePathString);

    CodeModelSourceFileDto getFile(BigInteger fileId);

    List<CodeModelSourceFileTreeNodeDto> getAllChildNodes(BigInteger parentNodeId);

    List<CodeModelSourceLanguageFileExtensionDto> getFileCodeExtensions();

}
