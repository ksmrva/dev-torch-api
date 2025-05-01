package io.ksmrva.visual.torch.data.common.explorer;

import io.ksmrva.visual.torch.data.common.explorer.panel.ExplorerPanel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Explorer {

    private ExplorerPanel panelDetails;

    public ExplorerPanel getPanelDetails() {
        return panelDetails;
    }

    public void setPanelDetails(ExplorerPanel panelDetails) {
        this.panelDetails = panelDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Explorer that)) {
            return false;
        }

        return new EqualsBuilder().append(getPanelDetails(), that.getPanelDetails())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPanelDetails())
                .toHashCode();
    }

}
