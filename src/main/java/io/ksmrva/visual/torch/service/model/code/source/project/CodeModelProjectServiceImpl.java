package io.ksmrva.visual.torch.service.model.code.source.project;

import io.ksmrva.visual.torch.db.dao.model.code.source.project.CodeModelProjectDao;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.project.CodeModelSourceProjectDto;
import io.ksmrva.visual.torch.service.model.code.source.file.CodeModelSourceFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeModelProjectServiceImpl implements CodeModelProjectService {

    private final CodeModelSourceFileService codeModelSourceFileService;

    private final CodeModelProjectDao codeModelProjectDao;

    public CodeModelProjectServiceImpl(CodeModelSourceFileService codeModelSourceFileService, CodeModelProjectDao codeModelProjectDao) {
        this.codeModelSourceFileService = codeModelSourceFileService;
        this.codeModelProjectDao = codeModelProjectDao;
    }

    @Override
    public CodeModelSourceProjectDto createProject(String projectName, String projectDirectoryPath) {
        CodeModelSourceFileDto projectRootFile = codeModelSourceFileService.createCodeFileFromSystemFilePath(projectDirectoryPath);

        return this.codeModelProjectDao.createProject(projectName, projectRootFile);
    }

    @Override
    public CodeModelSourceProjectDto getProject(String projectName) {
        return this.codeModelProjectDao.getProject(projectName);
    }

    @Override
    public List<String> getAllProjectNames() {
        return this.codeModelProjectDao.getAllProjectNames();
    }

}
