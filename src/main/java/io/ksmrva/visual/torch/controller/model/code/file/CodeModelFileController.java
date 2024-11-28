package io.ksmrva.visual.torch.controller.model.code.file;

import io.ksmrva.visual.torch.controller.AbstractApiController;
import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.node.CodeFileNodeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.CodeFileTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.extension.CodeTextFileExtensionDto;
import io.ksmrva.visual.torch.service.model.code.file.CodeModelFileService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(AbstractApiController.BASE_URI + "/model/code/file")
public class CodeModelFileController extends AbstractApiController {

    private final CodeModelFileService codeModelFileService;

    public CodeModelFileController(CodeModelFileService codeModelFileService) {
        this.codeModelFileService = codeModelFileService;
    }

    @GetMapping("/{fileId}")
    public @ResponseBody CodeFileDto<?, ?> getFile(@PathVariable BigInteger fileId) {
        return codeModelFileService.getFile(fileId);
    }

    @GetMapping("/node/{parentNodeId}/children")
    public @ResponseBody List<CodeFileNodeDto> getAllChildNodes(@PathVariable BigInteger parentNodeId) {
        return codeModelFileService.getAllChildNodes(parentNodeId);
    }

    @GetMapping("/type")
    public @ResponseBody List<CodeFileTypeDto> getFileTypes() {
        return codeModelFileService.getFileTypes();
    }

    @GetMapping("/extension")
    public @ResponseBody List<CodeTextFileExtensionDto> getCodeFileExtensions() {
        return codeModelFileService.getTextFileExtensions();
    }

}
