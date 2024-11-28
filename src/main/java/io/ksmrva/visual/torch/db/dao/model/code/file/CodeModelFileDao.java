package io.ksmrva.visual.torch.db.dao.model.code.file;

import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.node.CodeFileNodeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.CodeFileTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.extension.CodeTextFileExtensionDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.CodeFile;

import java.math.BigInteger;
import java.util.List;

public interface CodeModelFileDao {

    /**
     * Create
     **/
    CodeFile<?, ?> createRootCodeFileEntity(CodeFileDto<?, ?> codeFileDtoToCreate);

    /**
     * Read
     **/
    CodeFileDto<?, ?> getFile(BigInteger fileId, boolean resolveChildren);

    List<CodeFileNodeDto> getAllChildNodes(BigInteger parentNodeId);

    List<CodeFileTypeDto> getFileTypes();

    List<CodeTextFileExtensionDto> getTextFileExtensions();

    /**
     * Update
     **/

    /**
     * Delete
     **/

}
