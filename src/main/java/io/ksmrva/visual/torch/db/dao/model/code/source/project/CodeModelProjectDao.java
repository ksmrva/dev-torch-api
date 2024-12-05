package io.ksmrva.visual.torch.db.dao.model.code.source.project;

import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.project.CodeModelSourceProjectDto;

import java.util.List;

public interface CodeModelProjectDao {

    /**
     * Create
     **/
    CodeModelSourceProjectDto createProject(String projectName, CodeModelSourceFileDto rootFile);

    /**
     * Read
     **/
    CodeModelSourceProjectDto getProject(String projectName);

    List<String> getAllProjectNames();

    /**
     * Update
     **/

    /**
     * Delete
     **/
}
