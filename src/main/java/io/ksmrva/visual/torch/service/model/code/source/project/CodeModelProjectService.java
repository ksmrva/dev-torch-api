package io.ksmrva.visual.torch.service.model.code.source.project;

import io.ksmrva.visual.torch.domain.dto.model.code.source.project.CodeModelSourceProjectDto;

import java.util.List;

public interface CodeModelProjectService {

    CodeModelSourceProjectDto createProject(String projectName, String projectDirectoryPath);

    CodeModelSourceProjectDto getProject(String projectName);

    List<String> getAllProjectNames();

}
