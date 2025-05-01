package io.ksmrva.visual.torch.data.common.explorer.panel;

import io.ksmrva.visual.torch.data.common.explorer.panel.list.ExplorerPanelList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ExplorerPanel {

    private ExplorerPanelList listDetails;

    public ExplorerPanelList getListDetails() {
        return listDetails;
    }

    public void setListDetails(ExplorerPanelList listDetails) {
        this.listDetails = listDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ExplorerPanel that)) {
            return false;
        }

        return new EqualsBuilder().append(getListDetails(), that.getListDetails())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getListDetails())
                .toHashCode();
    }

}
