package io.ksmrva.visual.torch.db.dao.model.code.source.file;

import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.data.CodeModelSourceFileDataDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension.CodeModelSourceFileCodeExtensionDto;
import io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node.CodeModelSourceFileTreeNodeDto;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.CodeModelSourceFile;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.data.CodeModelSourceFileData;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.extension.CodeModelSourceFileCodeExtension;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.tree.node.CodeModelSourceFileTreeNode;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

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
    public CodeModelSourceFile createRootCodeFileEntity(CodeModelSourceFileDto codeModelSourceFileDtoToCreate) {
        return this.createRootFileEntity(codeModelSourceFileDtoToCreate, true);
    }

    @Override
    public CodeModelSourceFileDataDto createFileData(CodeModelSourceFileDataDto fileDataDtoToCreate) {
        Assert.notNull(fileDataDtoToCreate, "Was provided a null Code Model Source File Data to create");
        CodeModelSourceFileData fileDataToCreate = DtoFactory.toEntity(fileDataDtoToCreate);
        this.sessionFactory.getCurrentSession()
                           .persist(fileDataToCreate);

        return DtoFactory.fromEntity(fileDataToCreate);
    }

    /**
     * Read
     **/
    @Override
    public CodeModelSourceFileDto getFile(BigInteger fileId, boolean resolveChildren) {
        CodeModelSourceFileDto fileResultDto = null;

        CodeModelSourceFile codeModelSourceFileQueryResult = this.getCodeFileEntity(fileId);
        if (codeModelSourceFileQueryResult != null) {
            fileResultDto = codeModelSourceFileQueryResult.convertToDto();
            if (resolveChildren
                && codeModelSourceFileQueryResult.isDirectory()) {
                List<CodeModelSourceFileDto> childCodeFiles = this.getChildCodeFiles(codeModelSourceFileQueryResult.getTreeNode()
                                                                                                                   .getId());
                fileResultDto.setChildren(childCodeFiles);
            }
        }

        return fileResultDto;
    }

    @Override
    public List<CodeModelSourceFileTreeNodeDto> getAllChildNodes(BigInteger parentNodeId) {
        return DtoFactory.fromEntities(this.getChildFileNodeEntities(parentNodeId, true));
    }

    @Override
    public List<CodeModelSourceFileCodeExtensionDto> getFileCodeExtensions() {
        List<CodeModelSourceFileCodeExtension> fileCodeExtensionsQueryResult;
        try {
            fileCodeExtensionsQueryResult = this.sessionFactory.getCurrentSession()
                                                               .createSelectionQuery("from CodeModelSourceFileCodeExtension", CodeModelSourceFileCodeExtension.class)
                                                               .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Code Model Source File Code Extensions", e);
            fileCodeExtensionsQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(fileCodeExtensionsQueryResult);
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

    private CodeModelSourceFile createRootFileEntity(CodeModelSourceFileDto rootCodeModelSourceFileDtoToCreate, boolean createSubFiles) {
        return this.createFileEntity(rootCodeModelSourceFileDtoToCreate, null, createSubFiles);
    }

    private CodeModelSourceFile createFileEntity(CodeModelSourceFileDto codeModelSourceFileDtoToCreate, CodeModelSourceFileTreeNode parentFileNode, boolean createSubFiles) {
        CodeModelSourceFileTreeNode codeFileNode = new CodeModelSourceFileTreeNode();
        codeFileNode.setParentNode(parentFileNode);
        this.sessionFactory.getCurrentSession()
                           .persist(codeFileNode);

        CodeModelSourceFile codeModelSourceFile = codeModelSourceFileDtoToCreate.convertToEntity();
        codeModelSourceFile.setTreeNode(codeFileNode);
        if (codeModelSourceFile.getData() != null) {
            codeModelSourceFile.getData()
                               .setFile(codeModelSourceFile);
        }

        this.sessionFactory.getCurrentSession()
                           .merge(codeModelSourceFile);

        if (createSubFiles &&
            codeModelSourceFileDtoToCreate.isDirectory()) {
            List<CodeModelSourceFileDto> codeDirectoryFilesToCreate = codeModelSourceFileDtoToCreate.getChildren();
            for (CodeModelSourceFileDto codeDirectoryFileToCreate : codeDirectoryFilesToCreate) {
                this.createFileEntity(codeDirectoryFileToCreate, codeFileNode, true);
            }
        }

        return codeModelSourceFile;
    }

    private CodeModelSourceFile getCodeFileEntity(BigInteger codeFileId) {
        CodeModelSourceFile codeModelSourceFileQueryResult = null;
        try {
            codeModelSourceFileQueryResult = this.sessionFactory.getCurrentSession()
                                                                .createSelectionQuery("from CodeModelSourceFile where id=:codeFileId", CodeModelSourceFile.class)
                                                                .setParameter("codeFileId", codeFileId)
                                                                .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Code File with ID [" + codeFileId + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Code File with ID [" + codeFileId + "]", e);
        }
        return codeModelSourceFileQueryResult;
    }

    private List<CodeModelSourceFileDto> getChildCodeFiles(BigInteger parentFileNodeId) {
        List<CodeModelSourceFileDto> childCodeModelSourceFileDtos = new ArrayList<>();
        List<CodeModelSourceFileTreeNode> childFileNodes = getChildFileNodeEntities(parentFileNodeId, false);
        for (CodeModelSourceFileTreeNode childFileNode : childFileNodes) {
            childCodeModelSourceFileDtos.add(this.getFile(childFileNode.getId(), false));
        }
        return childCodeModelSourceFileDtos;
    }

    private List<CodeModelSourceFileTreeNode> getChildFileNodeEntities(BigInteger parentFileNodeId, boolean resolveFileNames) {
        List<CodeModelSourceFileTreeNode> childFileNodesQueryResult;
        try {
            childFileNodesQueryResult = this.sessionFactory.getCurrentSession()
                                                           .createSelectionQuery("from CodeModelSourceFileTreeNode where parentNode.id=:parentFileNodeId", CodeModelSourceFileTreeNode.class)
                                                           .setParameter("parentFileNodeId", parentFileNodeId)
                                                           .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Child File Nodes using Parent Node ID [" + parentFileNodeId + "]", e);
            childFileNodesQueryResult = new ArrayList<>();
        }

        if (resolveFileNames) {
            for (CodeModelSourceFileTreeNode childFileNode : childFileNodesQueryResult) {
                childFileNode.getName();
            }
        }

        return childFileNodesQueryResult;
    }

}
