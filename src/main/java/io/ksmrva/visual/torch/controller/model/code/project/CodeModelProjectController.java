package io.ksmrva.visual.torch.controller.model.code.project;

import io.ksmrva.visual.torch.controller.AbstractApiController;
import io.ksmrva.visual.torch.api.args.model.code.CodeProjectCreateArgs;
import io.ksmrva.visual.torch.domain.dto.model.code.project.CodeProjectDto;
import io.ksmrva.visual.torch.service.model.code.project.CodeModelProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AbstractApiController.BASE_URI + "/model/code/project")
public class CodeModelProjectController extends AbstractApiController {

    private static final Logger LOGGER = LogManager.getLogger(CodeModelProjectController.class);

    private final CodeModelProjectService codeModelProjectService;

    public CodeModelProjectController(CodeModelProjectService codeModelProjectService) {
        this.codeModelProjectService = codeModelProjectService;
    }

    @PostMapping
    public @ResponseBody CodeProjectDto createProject(@RequestBody CodeProjectCreateArgs projectCreateArgs) {
        String projectName = projectCreateArgs.getProjectName();
        String projectDirectoryPath = projectCreateArgs.getDirectoryPath();
        LOGGER.info("Creating Code Files for Project [" + projectName + "] at Path [" + projectDirectoryPath + "]");

        CodeProjectDto project = codeModelProjectService.createProject(projectName, projectDirectoryPath);
        LOGGER.info("Finished creating Code Files for Project [" + projectName + "]");

        return project;
    }

    @GetMapping("/{projectName}")
    public @ResponseBody CodeProjectDto getProject(@PathVariable String projectName) {
        return codeModelProjectService.getProject(projectName);
    }

    @GetMapping("/name")
    public @ResponseBody List<String> getAllProjectNames() {
        return codeModelProjectService.getAllProjectNames();
    }

}
