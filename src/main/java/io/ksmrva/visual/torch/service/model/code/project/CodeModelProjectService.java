package io.ksmrva.visual.torch.service.model.code.project;

import io.ksmrva.visual.torch.domain.dto.model.code.project.CodeProjectDto;

import java.util.List;

public interface CodeModelProjectService {

    CodeProjectDto createProject(String projectName, String projectDirectoryPath);

    CodeProjectDto getProject(String projectName);

    List<String> getAllProjectNames();

}
