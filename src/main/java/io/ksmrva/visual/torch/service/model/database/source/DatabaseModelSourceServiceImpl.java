package io.ksmrva.visual.torch.service.model.database.source;

import io.ksmrva.visual.torch.db.dao.model.database.source.DatabaseModelSourceDao;
import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.driver.DbModelSourceConfigSupportedDriverDto;
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
    public DbModelSourceUrlDto createUrl(DbModelSourceUrlDto urlToCreate) {
        return databaseModelSourceDao.createUrl(urlToCreate);
    }

    @Override
    public DbModelSourceConfigDto createConfig(DbModelSourceConfigDto configToCreate) {
        return databaseModelSourceDao.createConfig(configToCreate);
    }

    @Override
    public List<DbModelSourceDataTypeDto> getOrCreateAllDataTypes() {
        return databaseModelSourceDao.getOrCreateAllDataTypes();
    }

    @Override
    public List<DbModelSourcePresetDto> getAllPresets() {
        return databaseModelSourceDao.getAllPresets();
    }

    @Override
    public List<DbModelSourceConfigSupportedDriverDto> getAllConfigSupportedDrivers() {
        return databaseModelSourceDao.getAllConfigSupportedDrivers();
    }

    @Override
    public List<DbModelSourceUrlSupportedSchemeDto> getAllUrlSupportedSchemes() {
        return databaseModelSourceDao.getAllUrlSupportedSchemes();
    }

    @Override
    public List<DbModelSourceUrlSupportedProviderDto> getAllUrlSupportedProviders() {
        return databaseModelSourceDao.getAllUrlSupportedProviders();
    }

    @Override
    public DbModelSourceConfigDto getConfig(BigInteger configId) {
        return databaseModelSourceDao.getConfig(configId);
    }

    @Override
    public List<DbModelSourceUrlDto> getAllUrls() {
        return databaseModelSourceDao.getAllUrls();
    }

    @Override
    public List<DbModelSourceConfigDto> getAllConfigs() {
        return databaseModelSourceDao.getAllConfigs();
    }

}
