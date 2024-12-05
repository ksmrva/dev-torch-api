package io.ksmrva.visual.torch.controller.model.code.source.project;

import io.ksmrva.visual.torch.controller.AbstractApiController;
import io.ksmrva.visual.torch.api.args.model.code.source.project.CodeModelSourceProjectCreateArgs;
import io.ksmrva.visual.torch.domain.dto.model.code.source.project.CodeModelSourceProjectDto;
import io.ksmrva.visual.torch.service.model.code.source.project.CodeModelProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AbstractApiController.BASE_URI + "/model/code/source/project")
public class CodeModelSourceProjectController extends AbstractApiController {

    private static final Logger LOGGER = LogManager.getLogger(CodeModelSourceProjectController.class);

    private final CodeModelProjectService codeModelProjectService;

    public CodeModelSourceProjectController(CodeModelProjectService codeModelProjectService) {
        this.codeModelProjectService = codeModelProjectService;
    }

    @PostMapping
    public @ResponseBody CodeModelSourceProjectDto createProject(@RequestBody CodeModelSourceProjectCreateArgs projectCreateArgs) {
        String projectName = projectCreateArgs.getName();
        String projectDirectoryPath = projectCreateArgs.getPath();
        LOGGER.info("Creating Code Files for Project [" + projectName + "] at Path [" + projectDirectoryPath + "]");

        CodeModelSourceProjectDto project = codeModelProjectService.createProject(projectName, projectDirectoryPath);
        LOGGER.info("Finished creating Code Files for Project [" + projectName + "]");

        return project;
    }

    @GetMapping("/{projectName}")
    public @ResponseBody CodeModelSourceProjectDto getProject(@PathVariable String projectName) {
        return codeModelProjectService.getProject(projectName);
    }

    @GetMapping("/name")
    public @ResponseBody List<String> getAllProjectNames() {
        return codeModelProjectService.getAllProjectNames();
    }

}
