package io.ksmrva.visual.torch.domain.entity.model.code.source.file;

import io.ksmrva.visual.torch.domain.dto.model.code.source.file.CodeModelSourceFileDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.data.CodeModelSourceFileData;
import io.ksmrva.visual.torch.domain.entity.model.code.source.file.tree.node.CodeModelSourceFileTreeNode;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "source_file", schema = "code_model")
public class CodeModelSourceFile extends AbstractBaseEntity<CodeModelSourceFileDto, CodeModelSourceFile> {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_node_id")
    private CodeModelSourceFileTreeNode treeNode;

    @Column(name = "name")
    private String name;

    @Column(name = "is_directory")
    private boolean isDirectory;

    @OneToOne(mappedBy = "file",
              cascade = {CascadeType.ALL}, orphanRemoval = true)
    private CodeModelSourceFileData data;

    @Override
    public CodeModelSourceFileDto convertToDto() {
        CodeModelSourceFileDto dto = super.createDtoWithBaseValues(CodeModelSourceFileDto::new);
        dto.setName(this.getName());
        dto.setDirectory(this.isDirectory());

        if (this.getTreeNode() != null) {
            dto.setTreeNode(this.getTreeNode()
                                .convertToDto());
        }

        if (this.getData() != null) {
            dto.setData(this.getData()
                            .convertToDto());
        }

        return dto;
    }

    public CodeModelSourceFileTreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(CodeModelSourceFileTreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public CodeModelSourceFileData getData() {
        return data;
    }

    public void setData(CodeModelSourceFileData data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceFile that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(isDirectory(), that.isDirectory())
                                  .append(getTreeNode(), that.getTreeNode())
                                  .append(getName(), that.getName())
                                  .append(getData(), that.getData())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getTreeNode())
                .append(getName())
                .append(isDirectory())
                .append(getData())
                .toHashCode();
    }

}
