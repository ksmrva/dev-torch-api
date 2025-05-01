package io.ksmrva.visual.torch.controller.common.explorer.panel.list;

import io.ksmrva.visual.torch.api.arg.constant.DevTorchApiConstants;
import io.ksmrva.visual.torch.data.common.explorer.panel.list.entry.ExplorerPanelListEntry;
import io.ksmrva.visual.torch.service.common.explorer.panel.list.ExplorerPanelListService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(DevTorchApiConstants.EXPLORER_PANEL_LIST_BASE_URI_PATH)
public class ExplorerPanelListController {

    private final ExplorerPanelListService explorerPanelListService;

    public ExplorerPanelListController(ExplorerPanelListService explorerPanelListService) {
        this.explorerPanelListService = explorerPanelListService;
    }

    @GetMapping("/entry/{entryId}/sub")
    public @ResponseBody List<ExplorerPanelListEntry> getSubEntries(@PathVariable BigInteger entryId, @RequestParam String entryType) {
        return explorerPanelListService.getSubEntries(entryType, entryId);
    }

}
