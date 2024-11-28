package io.ksmrva.visual.torch.controller.model.database.source;

import io.ksmrva.visual.torch.controller.AbstractApiController;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.driver.DbModelSourceConfigSupportedDriverDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.preset.DbModelSourcePresetDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.DbModelSourceUrlDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider.DbModelSourceUrlSupportedProviderDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme.DbModelSourceUrlSupportedSchemeDto;
import io.ksmrva.visual.torch.service.model.database.source.DatabaseModelSourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AbstractApiController.BASE_URI + "/model/db/source")
public class DatabaseModelSourceController extends AbstractApiController {

    private final DatabaseModelSourceService databaseModelSourceService;

    public DatabaseModelSourceController(final DatabaseModelSourceService databaseModelSourceService) {
        this.databaseModelSourceService = databaseModelSourceService;
    }

    @PostMapping("/url")
    public @ResponseBody DbModelSourceUrlDto createSourceUrl(@RequestBody DbModelSourceUrlDto sourceUrl) {
        return databaseModelSourceService.createSourceUrl(sourceUrl);
    }

    @PostMapping()
    public @ResponseBody DbModelSourceConfigDto createSourceConfig(@RequestBody DbModelSourceConfigDto sourceConfigToCreate) {
        return databaseModelSourceService.createSourceConfig(sourceConfigToCreate);
    }

    @GetMapping("/preset")
    public List<DbModelSourcePresetDto> getAllSourcePresets() {
        return databaseModelSourceService.getAllSourcePresets();
    }

    @GetMapping("/driver")
    public List<DbModelSourceConfigSupportedDriverDto> getAllSourceConfigSupportedDrivers() {
        return databaseModelSourceService.getAllSourceConfigSupportedDrivers();
    }

    @GetMapping("/url/scheme")
    public List<DbModelSourceUrlSupportedSchemeDto> getAllSourceUrlSupportedSchemes() {
        return databaseModelSourceService.getAllSourceUrlSupportedSchemes();
    }

    @GetMapping("/url/provider")
    public List<DbModelSourceUrlSupportedProviderDto> getAllSourceUrlSupportedProviders() {
        return databaseModelSourceService.getAllSourceUrlSupportedProviders();
    }

    @GetMapping("/url")
    public @ResponseBody List<DbModelSourceUrlDto> getAllSourceUrls() {
        return databaseModelSourceService.getAllSourceUrls();
    }

    @GetMapping()
    public @ResponseBody List<DbModelSourceConfigDto> getAllSourceConfigs() {
        return databaseModelSourceService.getAllSourceConfigs();
    }

}
