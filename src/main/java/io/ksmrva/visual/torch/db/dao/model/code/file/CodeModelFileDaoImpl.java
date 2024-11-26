package io.ksmrva.visual.torch.db.dao.model.code.file;

import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.model.code.file.CodeFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.node.CodeFileNodeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.CodeFileTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.directory.CodeDirectoryFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.CodeTextFileExtensionDto;
import io.ksmrva.visual.torch.domain.entity.model.code.file.CodeFile;
import io.ksmrva.visual.torch.domain.entity.model.code.file.node.CodeFileNode;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.CodeFileType;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.directory.CodeDirectoryFile;
import io.ksmrva.visual.torch.domain.entity.model.code.file.type.text.extension.CodeTextFileExtension;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CodeModelFileDaoImpl implements CodeModelFileDao {

    private static final Logger LOGGER = LogManager.getLogger(CodeModelFileDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public CodeModelFileDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create
     **/
    @Override
    public CodeFile<?, ?> createRootCodeFileEntity(CodeFileDto<?, ?> codeFileDtoToCreate) {
        return this.createRootFileEntity(codeFileDtoToCreate, true);
    }

    /**
     * Read
     **/
    @Override
    public CodeFileDto<?, ?> getFile(BigInteger fileId, boolean resolveChildren) {
        CodeFileDto<?, ?> fileResultDto = null;

        CodeFile<?, ?> codeFileQueryResult = this.getCodeFileEntity(fileId);
        if (codeFileQueryResult != null) {
            fileResultDto = codeFileQueryResult.convertToDto();
            if (resolveChildren
                && codeFileQueryResult instanceof CodeDirectoryFile) {
                List<CodeFileDto<?, ?>> childCodeFiles = this.getChildCodeFiles(codeFileQueryResult.getFileNode()
                                                                                                   .getId());
                ((CodeDirectoryFileDto) fileResultDto).setNestedFiles(childCodeFiles);
            }
        }

        return fileResultDto;
    }

    @Override
    public List<CodeFileNodeDto> getAllChildNodes(BigInteger parentNodeId) {
        return DtoFactory.fromEntities(this.getChildFileNodeEntities(parentNodeId, true));
    }

    @Override
    public List<CodeFileTypeDto> getFileTypes() {
        List<CodeFileType> fileTypesQueryResult;
        try {
            fileTypesQueryResult = this.sessionFactory.getCurrentSession()
                                                      .createSelectionQuery("from CodeFileType", CodeFileType.class)
                                                      .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Code Model File Types", e);
            fileTypesQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(fileTypesQueryResult);
    }

    @Override
    public List<CodeTextFileExtensionDto> getTextFileExtensions() {
        List<CodeTextFileExtension> textFileExtensionsQueryResult;
        try {
            textFileExtensionsQueryResult = this.sessionFactory.getCurrentSession()
                                                               .createSelectionQuery("from CodeTextFileExtension", CodeTextFileExtension.class)
                                                               .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Code Text File Extensions", e);
            textFileExtensionsQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(textFileExtensionsQueryResult);
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

    private CodeFile<?, ?> createRootFileEntity(CodeFileDto<?, ?> rootCodeFileDtoToCreate, boolean createSubFiles) {
        return this.createFileEntity(rootCodeFileDtoToCreate, null, createSubFiles);
    }

    private CodeFile<?, ?> createFileEntity(CodeFileDto<?, ?> codeFileDtoToCreate, CodeFileNode parentFileNode, boolean createSubFiles) {
        CodeFileNode codeFileNode = new CodeFileNode();
        codeFileNode.setParentNode(parentFileNode);
        this.sessionFactory.getCurrentSession()
                           .persist(codeFileNode);

        CodeFile<?, ?> codeFile = codeFileDtoToCreate.convertToEntity();
        codeFile.setFileNode(codeFileNode);
        this.sessionFactory.getCurrentSession()
                           .merge(codeFile);

        if (createSubFiles &&
            codeFileDtoToCreate instanceof CodeDirectoryFileDto) {
            List<CodeFileDto<?, ?>> codeDirectoryFilesToCreate = ((CodeDirectoryFileDto) codeFileDtoToCreate).getNestedFiles();
            for (CodeFileDto<?, ?> codeDirectoryFileToCreate : codeDirectoryFilesToCreate) {
                this.createFileEntity(codeDirectoryFileToCreate, codeFileNode, true);
            }
        }

        return codeFile;
    }

    private CodeFile<?, ?> getCodeFileEntity(BigInteger codeFileId) {
        CodeFile<?, ?> codeFileQueryResult = null;
        try {
            codeFileQueryResult = this.sessionFactory.getCurrentSession()
                                                     .createSelectionQuery("from CodeFile where id=:codeFileId", CodeFile.class)
                                                     .setParameter("codeFileId", codeFileId)
                                                     .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Code File with ID [" + codeFileId + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Code File with ID [" + codeFileId + "]", e);
        }
        return codeFileQueryResult;
    }

    private List<CodeFileDto<?, ?>> getChildCodeFiles(BigInteger parentFileNodeId) {
        List<CodeFileDto<?, ?>> childCodeFileDtos = new ArrayList<>();
        List<CodeFileNode> childFileNodes = getChildFileNodeEntities(parentFileNodeId, false);
        for (CodeFileNode childFileNode : childFileNodes) {
            childCodeFileDtos.add(this.getFile(childFileNode.getId(), false));
        }
        return childCodeFileDtos;
    }

    private List<CodeFileNode> getChildFileNodeEntities(BigInteger parentFileNodeId, boolean resolveFileNames) {
        List<CodeFileNode> childFileNodesQueryResult;
        try {
            childFileNodesQueryResult = this.sessionFactory.getCurrentSession()
                                                           .createSelectionQuery("from CodeFileNode where parentNode.id=:parentFileNodeId", CodeFileNode.class)
                                                           .setParameter("parentFileNodeId", parentFileNodeId)
                                                           .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Child File Nodes using Parent Node ID [" + parentFileNodeId + "]", e);
            childFileNodesQueryResult = new ArrayList<>();
        }

        if (resolveFileNames) {
            for (CodeFileNode childFileNode : childFileNodesQueryResult) {
                childFileNode.getName();
            }
        }

        return childFileNodesQueryResult;
    }

}
