package io.ksmrva.visual.torch.data.common.explorer.panel.list.entry;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

public class ExplorerPanelListEntry {

    private BigInteger id;

    private String type;

    private String name;

    private boolean hasSubEntries;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasSubEntries() {
        return hasSubEntries;
    }

    public void setHasSubEntries(boolean hasSubEntries) {
        this.hasSubEntries = hasSubEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ExplorerPanelListEntry that)) {
            return false;
        }

        return new EqualsBuilder().append(isHasSubEntries(), that.isHasSubEntries())
                                  .append(getId(), that.getId())
                                  .append(getType(), that.getType())
                                  .append(getName(), that.getName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getType())
                .append(getName())
                .append(isHasSubEntries())
                .toHashCode();
    }

}
