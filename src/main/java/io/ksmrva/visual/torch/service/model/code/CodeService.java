package io.ksmrva.visual.torch.service.model.code;

import io.ksmrva.visual.torch.controller.args.model.code.CodeRepositoryProjectDirectoryDescription;
import io.ksmrva.visual.torch.domain.entity.model.code.Code;

import java.util.List;

public interface CodeService {

    List<Code> createCodeBlocksFromProjectDirectory(CodeRepositoryProjectDirectoryDescription projectDirectoryDescription);

}
