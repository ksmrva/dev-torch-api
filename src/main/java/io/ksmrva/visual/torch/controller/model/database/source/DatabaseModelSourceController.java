package io.ksmrva.visual.torch.controller.model.database.source;

import io.ksmrva.visual.torch.api.arg.constant.DevTorchApiConstants;
import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
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
@RequestMapping(DevTorchApiConstants.DATABASE_MODEL_SOURCE_BASE_URI_PATH)
public class DatabaseModelSourceController {

    private final DatabaseModelSourceService databaseModelSourceService;

    public DatabaseModelSourceController(final DatabaseModelSourceService databaseModelSourceService) {
        this.databaseModelSourceService = databaseModelSourceService;
    }

    @PostMapping("/url")
    public @ResponseBody DbModelSourceUrlDto createUrl(@RequestBody DbModelSourceUrlDto urlToCreate) {
        return databaseModelSourceService.createUrl(urlToCreate);
    }

    @PostMapping()
    public @ResponseBody DbModelSourceConfigDto createConfig(@RequestBody DbModelSourceConfigDto configToCreate) {
        return databaseModelSourceService.createConfig(configToCreate);
    }

    @GetMapping("/data/type")
    public @ResponseBody List<DbModelSourceDataTypeDto> getOrCreateAllDataTypes() {
        return databaseModelSourceService.getOrCreateAllDataTypes();
    }

    @GetMapping("/preset")
    public List<DbModelSourcePresetDto> getAllPresets() {
        return databaseModelSourceService.getAllPresets();
    }

    @GetMapping("/driver")
    public List<DbModelSourceConfigSupportedDriverDto> getAllConfigSupportedDrivers() {
        return databaseModelSourceService.getAllConfigSupportedDrivers();
    }

    @GetMapping("/url/scheme")
    public List<DbModelSourceUrlSupportedSchemeDto> getAllUrlSupportedSchemes() {
        return databaseModelSourceService.getAllUrlSupportedSchemes();
    }

    @GetMapping("/url/provider")
    public List<DbModelSourceUrlSupportedProviderDto> getAllUrlSupportedProviders() {
        return databaseModelSourceService.getAllUrlSupportedProviders();
    }

    @GetMapping("/url")
    public @ResponseBody List<DbModelSourceUrlDto> getAllUrls() {
        return databaseModelSourceService.getAllUrls();
    }

    @GetMapping()
    public @ResponseBody List<DbModelSourceConfigDto> getAllConfigs() {
        return databaseModelSourceService.getAllConfigs();
    }

}
