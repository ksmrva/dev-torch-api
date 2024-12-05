package io.ksmrva.visual.torch.db.dao.model.code.source.project;

import io.ksmrva.visual.torch.db.dao.model.code.source.file.CodeModelFileDao;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.project.CodeModelSourceProjectDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.CodeModelSourceFile;
import io.ksmrva.visual.torch.domain.entity.model.code.source.project.CodeModelSourceProject;
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
    public CodeModelSourceProjectDto createProject(String projectName, CodeModelSourceFileDto rootFile) {
        CodeModelSourceFile createdRootCodeModelSourceFile = codeModelFileDao.createRootCodeFileEntity(rootFile);

        CodeModelSourceProject codeModelSourceProject = new CodeModelSourceProject();
        codeModelSourceProject.setName(projectName);
        codeModelSourceProject.setRootTreeNode(createdRootCodeModelSourceFile.getTreeNode());
        this.sessionFactory.getCurrentSession()
                           .persist(codeModelSourceProject);

        return codeModelSourceProject.convertToDto();
    }

    /**
     * Read
     **/
    @Override
    public CodeModelSourceProjectDto getProject(String projectName) {
        CodeModelSourceProject codeModelSourceProjectQueryResult = null;
        try {
            codeModelSourceProjectQueryResult = this.sessionFactory.getCurrentSession()
                                                                   .createSelectionQuery("from CodeModelSourceProject where name=:projectName", CodeModelSourceProject.class)
                                                                   .setParameter("projectName", projectName)
                                                                   .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Code Project with name [" + projectName + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Code Project with name [" + projectName + "]", e);
        }

        if (codeModelSourceProjectQueryResult == null) {
            throw new IllegalArgumentException("Unable to find a Code Project using name [" + projectName + "]");
        }

        return codeModelSourceProjectQueryResult.convertToDto();
    }

    @Override
    public List<String> getAllProjectNames() {
        List<String> allProjectNamesQueryResult;
        try {
            allProjectNamesQueryResult = this.sessionFactory.getCurrentSession()
                                                            .createSelectionQuery("select name from CodeModelSourceProject", String.class)
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
