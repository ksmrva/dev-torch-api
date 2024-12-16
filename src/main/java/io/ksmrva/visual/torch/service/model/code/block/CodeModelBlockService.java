package io.ksmrva.visual.torch.service.model.code.block;

import io.ksmrva.visual.torch.api.arg.model.code.source.project.CodeModelSourceProjectCreateArgs;
import io.ksmrva.visual.torch.domain.entity.model.code.detail.block.Code;

import java.util.List;

public interface CodeModelBlockService {

    List<Code> createCodeBlocksFromProjectDirectory(CodeModelSourceProjectCreateArgs projectDirectoryDescription);

}
