package io.ksmrva.visual.torch.data.common.explorer.panel.list;

import io.ksmrva.visual.torch.data.common.explorer.panel.list.entry.ExplorerPanelListEntry;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class ExplorerPanelList {

    private final List<ExplorerPanelListEntry> entryDetails;

    public ExplorerPanelList() {
        entryDetails = new ArrayList<>();
    }

    public void addEntryDetails(final ExplorerPanelListEntry entryDetails) {
        this.entryDetails.add(entryDetails);
    }

    public List<ExplorerPanelListEntry> getEntryDetails() {
        return entryDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ExplorerPanelList that)) {
            return false;
        }

        return new EqualsBuilder().append(getEntryDetails(), that.getEntryDetails())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getEntryDetails())
                .toHashCode();
    }

}
