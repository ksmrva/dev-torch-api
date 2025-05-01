package io.ksmrva.visual.torch.service.common.explorer.panel.list.entry.provider;

import io.ksmrva.visual.torch.data.common.explorer.panel.list.entry.ExplorerPanelListEntry;

import java.math.BigInteger;
import java.util.List;

public interface ExplorerPanelListServiceEntryProvider {

    String getEntryTypeKey();

    List<ExplorerPanelListEntry> getSubEntries(BigInteger entryId);

}
