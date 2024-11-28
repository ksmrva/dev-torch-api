package io.ksmrva.visual.torch.service.model.database.source;

import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.driver.DbModelSourceConfigSupportedDriverDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.preset.DbModelSourcePresetDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.DbModelSourceUrlDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider.DbModelSourceUrlSupportedProviderDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme.DbModelSourceUrlSupportedSchemeDto;

import java.math.BigInteger;
import java.util.List;

public interface DatabaseModelSourceService {

    DbModelSourceUrlDto createSourceUrl(DbModelSourceUrlDto sourceUrl);

    DbModelSourceConfigDto createSourceConfig(DbModelSourceConfigDto sourceConfig);

    List<DbModelSourcePresetDto> getAllSourcePresets();

    List<DbModelSourceConfigSupportedDriverDto> getAllSourceConfigSupportedDrivers();

    List<DbModelSourceUrlSupportedSchemeDto> getAllSourceUrlSupportedSchemes();

    List<DbModelSourceUrlSupportedProviderDto> getAllSourceUrlSupportedProviders();

    DbModelSourceConfigDto getSourceConfig(BigInteger sourceConfigId);

    List<DbModelSourceUrlDto> getAllSourceUrls();

    List<DbModelSourceConfigDto> getAllSourceConfigs();

}
