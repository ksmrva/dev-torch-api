package io.ksmrva.visual.torch.controller.model.code.source.file;

import io.ksmrva.visual.torch.api.arg.constant.DevTorchApiConstants;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension.CodeModelSourceLanguageFileExtensionDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node.CodeModelSourceFileTreeNodeDto;
import io.ksmrva.visual.torch.service.model.code.source.file.CodeModelSourceFileService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(DevTorchApiConstants.CODE_MODEL_SOURCE_FILE_BASE_URI_PATH)
public class CodeModelSourceFileController {

    private final CodeModelSourceFileService codeModelSourceFileService;

    public CodeModelSourceFileController(CodeModelSourceFileService codeModelSourceFileService) {
        this.codeModelSourceFileService = codeModelSourceFileService;
    }

    @GetMapping("/{fileId}")
    public @ResponseBody CodeModelSourceFileDto getFile(@PathVariable BigInteger fileId) {
        return codeModelSourceFileService.getFile(fileId);
    }

    @GetMapping("/node/{parentNodeId}/children")
    public @ResponseBody List<CodeModelSourceFileTreeNodeDto> getAllChildNodes(@PathVariable BigInteger parentNodeId) {
        return codeModelSourceFileService.getAllChildNodes(parentNodeId);
    }

    @GetMapping("/code/extension")
    public @ResponseBody List<CodeModelSourceLanguageFileExtensionDto> getFileCodeExtensions() {
        return codeModelSourceFileService.getFileCodeExtensions();
    }

}
