package io.ksmrva.visual.torch.service.common.explorer.panel.list;

import io.ksmrva.visual.torch.data.common.explorer.panel.list.entry.ExplorerPanelListEntry;

import java.math.BigInteger;
import java.util.List;

public interface ExplorerPanelListService {

    List<ExplorerPanelListEntry> getSubEntries(String entryType, BigInteger entryId);

}
