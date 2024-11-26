package io.ksmrva.visual.torch.db.dao.model.code.project;

import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.project.CodeProjectDto;

import java.util.List;

public interface CodeModelProjectDao {

    /**
     * Create
     **/
    CodeProjectDto createProject(String projectName, CodeFileDto<?, ?> rootFile);

    /**
     * Read
     **/
    CodeProjectDto getProject(String projectName);

    List<String> getAllProjectNames();

    /**
     * Update
     **/

    /**
     * Delete
     **/
}
