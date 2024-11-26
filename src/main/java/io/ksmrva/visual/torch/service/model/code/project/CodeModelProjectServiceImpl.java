package io.ksmrva.visual.torch.service.model.code.project;

import io.ksmrva.visual.torch.db.dao.model.code.project.CodeModelProjectDao;
import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.project.CodeProjectDto;
import io.ksmrva.visual.torch.service.model.code.file.CodeModelFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeModelProjectServiceImpl implements CodeModelProjectService {

    private final CodeModelFileService codeModelFileService;

    private final CodeModelProjectDao codeModelProjectDao;

    public CodeModelProjectServiceImpl(CodeModelFileService codeModelFileService, CodeModelProjectDao codeModelProjectDao) {
        this.codeModelFileService = codeModelFileService;
        this.codeModelProjectDao = codeModelProjectDao;
    }

    @Override
    public CodeProjectDto createProject(String projectName, String projectDirectoryPath) {
        CodeFileDto<?, ?> projectRootFile = codeModelFileService.createCodeFileFromSystemFilePath(projectDirectoryPath);

        return this.codeModelProjectDao.createProject(projectName, projectRootFile);
    }

    @Override
    public CodeProjectDto getProject(String projectName) {
        return this.codeModelProjectDao.getProject(projectName);
    }

    @Override
    public List<String> getAllProjectNames() {
        return this.codeModelProjectDao.getAllProjectNames();
    }

}
