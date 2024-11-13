package io.ksmrva.visual.torch.service.model.database.source;

import io.ksmrva.visual.torch.db.dao.model.database.source.DatabaseModelSourceDao;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.driver.DbModelSourceConfigSupportedDriverDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.preset.DbModelSourcePresetDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.DbModelSourceUrlDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider.DbModelSourceUrlSupportedProviderDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme.DbModelSourceUrlSupportedSchemeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class DatabaseModelSourceServiceImpl implements DatabaseModelSourceService {

    private final DatabaseModelSourceDao databaseModelSourceDao;

    @Autowired
    public DatabaseModelSourceServiceImpl(DatabaseModelSourceDao databaseModelSourceDao) {
        this.databaseModelSourceDao = databaseModelSourceDao;
    }

    @Override
    public DbModelSourceUrlDto createSourceUrl(DbModelSourceUrlDto sourceUrl) {
        return databaseModelSourceDao.createSourceUrl(sourceUrl);
    }

    @Override
    public DbModelSourceConfigDto createSourceConfig(DbModelSourceConfigDto sourceConfig) {
        return databaseModelSourceDao.createSourceConfig(sourceConfig);
    }

    @Override
    public List<DbModelSourcePresetDto> getAllSourcePresets() {
        return databaseModelSourceDao.getAllSourcePresets();
    }

    @Override
    public List<DbModelSourceConfigSupportedDriverDto> getAllSourceConfigSupportedDrivers() {
        return databaseModelSourceDao.getAllSourceConfigSupportedDrivers();
    }

    @Override
    public List<DbModelSourceUrlSupportedSchemeDto> getAllSourceUrlSupportedSchemes() {
        return databaseModelSourceDao.getAllSourceUrlSupportedSchemes();
    }

    @Override
    public List<DbModelSourceUrlSupportedProviderDto> getAllSourceUrlSupportedProviders() {
        return databaseModelSourceDao.getAllSourceUrlSupportedProviders();
    }

    @Override
    public DbModelSourceConfigDto getSourceConfig(BigInteger sourceConfigId) {
        return databaseModelSourceDao.getSourceConfig(sourceConfigId);
    }

    @Override
    public List<DbModelSourceUrlDto> getAllSourceUrls() {
        return databaseModelSourceDao.getAllSourceUrls();
    }

    @Override
    public List<DbModelSourceConfigDto> getAllSourceConfigs() {
        return databaseModelSourceDao.getAllSourceConfigs();
    }

}
