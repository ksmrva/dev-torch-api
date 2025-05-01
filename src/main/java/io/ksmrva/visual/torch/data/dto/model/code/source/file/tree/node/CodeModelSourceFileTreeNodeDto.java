package io.ksmrva.visual.torch.data.dto.model.code.source.file.tree.node;

import io.ksmrva.visual.torch.data.common.explorer.panel.list.entry.ExplorerPanelListEntry;
import io.ksmrva.visual.torch.data.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.data.entity.model.code.source.file.tree.node.CodeModelSourceFileTreeNode;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

public class CodeModelSourceFileTreeNodeDto extends AbstractBaseDto<CodeModelSourceFileTreeNodeDto, CodeModelSourceFileTreeNode> {

    public static final String EXPLORER_PANEL_LIST_ENTRY_TYPE_KEY = "code_model_source_file";

    private String name;

    private BigInteger fileId;

    private boolean hasChildren;

    private final ExplorerPanelListEntry explorerPanelListEntry;

    public CodeModelSourceFileTreeNodeDto() {
        this.explorerPanelListEntry = new ExplorerPanelListEntry();
        this.explorerPanelListEntry.setType(EXPLORER_PANEL_LIST_ENTRY_TYPE_KEY);
    }

    @Override
    public CodeModelSourceFileTreeNode convertToEntity() {
        CodeModelSourceFileTreeNode entity = super.createEntityWithBaseValues(CodeModelSourceFileTreeNode::new);
        entity.setName(this.getName());
        entity.setFileId(this.getFileId());
        entity.setHasChildren(this.isHasChildren());

        return entity;
    }

    @Override
    public void setId(BigInteger id) {
        super.setId(id);
        this.explorerPanelListEntry.setId(this.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.explorerPanelListEntry.setName(this.getName());
    }

    public BigInteger getFileId() {
        return fileId;
    }

    public void setFileId(BigInteger fileId) {
        this.fileId = fileId;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
        this.explorerPanelListEntry.setHasSubEntries(this.isHasChildren());
    }

    public ExplorerPanelListEntry getExplorerPanelListEntry() {
        return explorerPanelListEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeModelSourceFileTreeNodeDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getFileId(), that.getFileId())
                                  .append(isHasChildren(), that.isHasChildren())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getFileId())
                .append(isHasChildren())
                .toHashCode();
    }

}
