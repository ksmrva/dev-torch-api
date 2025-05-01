package io.ksmrva.visual.torch.service.common.explorer.panel.list;

import io.ksmrva.visual.torch.data.common.explorer.panel.list.entry.ExplorerPanelListEntry;
import io.ksmrva.visual.torch.service.common.explorer.panel.list.entry.provider.ExplorerPanelListServiceEntryProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExplorerPanelListServiceImpl implements ExplorerPanelListService {

    private static final Logger LOGGER = LogManager.getLogger(ExplorerPanelListServiceImpl.class);

    private final List<ExplorerPanelListServiceEntryProvider> entryProviders;

    @Autowired
    public ExplorerPanelListServiceImpl(List<ExplorerPanelListServiceEntryProvider> entryProviders) {
        this.entryProviders = entryProviders;
    }

    @Override
    public List<ExplorerPanelListEntry> getSubEntries(String entryType, BigInteger entryId) {
        List<ExplorerPanelListEntry> subEntries = new ArrayList<>();
        if (!StringUtils.isEmpty(entryType) && entryId != null) {
            ExplorerPanelListServiceEntryProvider matchedEntryProvider = entryProviders.stream()
                                                                                       .filter(entryProvider -> entryProvider.getEntryTypeKey()
                                                                                                                             .equals(entryType))
                                                                                       .findFirst()
                                                                                       .orElseThrow(() -> new RuntimeException("Failed to find Explorer Panel List Entry Provider for Type [" + entryType + "]"));
            subEntries = matchedEntryProvider.getSubEntries(entryId);
        }

        return subEntries;
    }

}
