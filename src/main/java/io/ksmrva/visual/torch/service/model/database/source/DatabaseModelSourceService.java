package io.ksmrva.visual.torch.service.model.database.source;

import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.driver.DbModelSourceConfigSupportedDriverDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.preset.DbModelSourcePresetDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.DbModelSourceUrlDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider.DbModelSourceUrlSupportedProviderDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme.DbModelSourceUrlSupportedSchemeDto;

import java.math.BigInteger;
import java.util.List;

public interface DatabaseModelSourceService {

    DbModelSourceUrlDto createUrl(DbModelSourceUrlDto urlToCreate);

    DbModelSourceConfigDto createConfig(DbModelSourceConfigDto configToCreate);

    List<DbModelSourceDataTypeDto> getOrCreateAllDataTypes();

    List<DbModelSourcePresetDto> getAllPresets();

    List<DbModelSourceConfigSupportedDriverDto> getAllConfigSupportedDrivers();

    List<DbModelSourceUrlSupportedSchemeDto> getAllUrlSupportedSchemes();

    List<DbModelSourceUrlSupportedProviderDto> getAllUrlSupportedProviders();

    DbModelSourceConfigDto getConfig(BigInteger configId);

    List<DbModelSourceUrlDto> getAllUrls();

    List<DbModelSourceConfigDto> getAllConfigs();

}
