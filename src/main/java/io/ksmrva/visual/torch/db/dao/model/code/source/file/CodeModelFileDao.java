package io.ksmrva.visual.torch.db.dao.model.code.source.file;

import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.data.CodeModelSourceFileDataDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension.CodeModelSourceLanguageFileExtensionDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node.CodeModelSourceFileTreeNodeDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.CodeModelSourceFile;

import java.math.BigInteger;
import java.util.List;

public interface CodeModelFileDao {

    /**
     * Create
     **/
    CodeModelSourceFile createRootCodeFileEntity(CodeModelSourceFileDto codeModelSourceFileDtoToCreate);

    CodeModelSourceFileDataDto createFileData(CodeModelSourceFileDataDto fileDataDtoToCreate);

    /**
     * Read
     **/
    CodeModelSourceFileDto getFile(BigInteger fileId, boolean resolveChildren);

    List<CodeModelSourceFileTreeNodeDto> getAllChildNodes(BigInteger parentNodeId);

    List<CodeModelSourceLanguageFileExtensionDto> getFileCodeExtensions();

    /**
     * Update
     **/

    /**
     * Delete
     **/

}
