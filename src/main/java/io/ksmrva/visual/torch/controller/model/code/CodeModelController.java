package io.ksmrva.visual.torch.controller.model.code;

import io.ksmrva.visual.torch.controller.AbstractApiController;
import io.ksmrva.visual.torch.controller.args.model.code.CodeRepositoryProjectDirectoryDescription;
import io.ksmrva.visual.torch.domain.entity.model.code.Code;
import io.ksmrva.visual.torch.service.model.code.CodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AbstractApiController.BASE_URI + "/model/code")
public class CodeModelController extends AbstractApiController {

    private final CodeService codeService;

    public CodeModelController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping
    public @ResponseBody List<Code> ingestCodeRepositoryFromProjectDirectory(@RequestBody CodeRepositoryProjectDirectoryDescription projectDirectoryDescription) {
        return codeService.createCodeBlocksFromProjectDirectory(projectDirectoryDescription);
    }

}
