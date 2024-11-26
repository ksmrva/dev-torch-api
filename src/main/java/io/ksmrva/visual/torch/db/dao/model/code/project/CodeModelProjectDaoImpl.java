package io.ksmrva.visual.torch.db.dao.model.code.project;

import io.ksmrva.visual.torch.db.dao.model.code.file.CodeModelFileDao;
import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.project.CodeProjectDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.CodeFile;
import io.ksmrva.visual.torch.domain.entity.model.code.project.CodeProject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CodeModelProjectDaoImpl implements CodeModelProjectDao {

    private static final Logger LOGGER = LogManager.getLogger(CodeModelProjectDaoImpl.class);

    private final SessionFactory sessionFactory;

    private final CodeModelFileDao codeModelFileDao;

    @Autowired
    public CodeModelProjectDaoImpl(SessionFactory sessionFactory, CodeModelFileDao codeModelFileDao) {
        this.sessionFactory = sessionFactory;
        this.codeModelFileDao = codeModelFileDao;
    }

    /**
     * Create
     **/
    @Override
    public CodeProjectDto createProject(String projectName, CodeFileDto<?, ?> rootFile) {
        CodeFile<?, ?> createdRootCodeFile = codeModelFileDao.createRootCodeFileEntity(rootFile);

        CodeProject codeProject = new CodeProject();
        codeProject.setName(projectName);
        codeProject.setRootFileNode(createdRootCodeFile.getFileNode());
        this.sessionFactory.getCurrentSession()
                           .persist(codeProject);

        return codeProject.convertToDto();
    }

    /**
     * Read
     **/
    @Override
    public CodeProjectDto getProject(String projectName) {
        CodeProject codeProjectQueryResult = null;
        try {
            codeProjectQueryResult = this.sessionFactory.getCurrentSession()
                                                        .createSelectionQuery("from CodeProject where name=:projectName", CodeProject.class)
                                                        .setParameter("projectName", projectName)
                                                        .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Code Project with name [" + projectName + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Code Project with name [" + projectName + "]", e);
        }

        if (codeProjectQueryResult == null) {
            throw new IllegalArgumentException("Unable to find a Code Project using name [" + projectName + "]");
        }

        return codeProjectQueryResult.convertToDto();
    }

    @Override
    public List<String> getAllProjectNames() {
        List<String> allProjectNamesQueryResult;
        try {
            allProjectNamesQueryResult = this.sessionFactory.getCurrentSession()
                                                            .createSelectionQuery("select name from CodeProject", String.class)
                                                            .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Code Project Names", e);
            allProjectNamesQueryResult = new ArrayList<>();
        }

        return allProjectNamesQueryResult;
    }

    /**
     * Update
     **/

    /**
     * Delete
     **/

    /**
     * Private
     **/

}
