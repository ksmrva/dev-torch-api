package io.ksmrva.visual.torch.service.model.code.block;

import io.ksmrva.visual.torch.controller.args.model.code.CodeProjectCreateArgs;
import io.ksmrva.visual.torch.domain.entity.model.code.block.Code;

import java.util.List;

public interface CodeModelBlockService {

    List<Code> createCodeBlocksFromProjectDirectory(CodeProjectCreateArgs projectDirectoryDescription);

}
