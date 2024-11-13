package io.ksmrva.visual.torch.db.dao.model.database.source;

import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.driver.DbModelSourceConfigSupportedDriverDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.preset.DbModelSourcePresetDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.DbModelSourceUrlDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider.DbModelSourceUrlSupportedProviderDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme.DbModelSourceUrlSupportedSchemeDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
import io.ksmrva.visual.torch.domain.entity.model.database.source.driver.DbModelSourceConfigSupportedDriver;
import io.ksmrva.visual.torch.domain.entity.model.database.source.preset.DbModelSourcePreset;
import io.ksmrva.visual.torch.domain.entity.model.database.source.url.DbModelSourceUrl;
import io.ksmrva.visual.torch.domain.entity.model.database.source.url.provider.DbModelSourceUrlSupportedProvider;
import io.ksmrva.visual.torch.domain.entity.model.database.source.url.scheme.DbModelSourceUrlSupportedScheme;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DatabaseModelSourceDaoImpl implements DatabaseModelSourceDao {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseModelSourceDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public DatabaseModelSourceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create
     **/
    @Override
    public DbModelSourceUrlDto createSourceUrl(DbModelSourceUrlDto sourceUrlDtoToCreate) {
        Assert.notNull(sourceUrlDtoToCreate, "Was provided a null Database Model Source URL to create");
        DbModelSourceUrl sourceUrl = DtoFactory.toEntity(sourceUrlDtoToCreate);
        this.sessionFactory.getCurrentSession()
                           .persist(sourceUrl);

        return sourceUrl.convertToDto();
    }

    @Override
    public DbModelSourceConfigDto createSourceConfig(DbModelSourceConfigDto sourceConfigDtoToCreate) {
        Assert.notNull(sourceConfigDtoToCreate, "Was provided a null Database Model Source Config to create");
        DbModelSourceConfig sourceConfig = DtoFactory.toEntity(sourceConfigDtoToCreate);
        this.sessionFactory.getCurrentSession()
                           .persist(sourceConfig);

        return sourceConfig.convertToDto();
    }

    /**
     * Read
     **/
    @Override
    public List<DbModelSourcePresetDto> getAllSourcePresets() {
        List<DbModelSourcePreset> sourcePresetsQueryResult;
        try {
            sourcePresetsQueryResult = this.sessionFactory.getCurrentSession()
                                                          .createSelectionQuery("from DbModelSourcePreset", DbModelSourcePreset.class)
                                                          .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source Presets", e);
            sourcePresetsQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(sourcePresetsQueryResult);
    }

    @Override
    public List<DbModelSourceConfigSupportedDriverDto> getAllSourceConfigSupportedDrivers() {
        List<DbModelSourceConfigSupportedDriver> sourceConfigSupportedDriversQueryResult;
        try {
            sourceConfigSupportedDriversQueryResult = this.sessionFactory.getCurrentSession()
                                                                         .createSelectionQuery("from DbModelSourceConfigSupportedDriver", DbModelSourceConfigSupportedDriver.class)
                                                                         .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source Config Supported Drivers", e);
            sourceConfigSupportedDriversQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(sourceConfigSupportedDriversQueryResult);
    }

    @Override
    public List<DbModelSourceUrlSupportedSchemeDto> getAllSourceUrlSupportedSchemes() {
        List<DbModelSourceUrlSupportedScheme> sourceUrlSupportedSchemesQueryResult;
        try {
            sourceUrlSupportedSchemesQueryResult = this.sessionFactory.getCurrentSession()
                                                                      .createSelectionQuery("from DbModelSourceUrlSupportedScheme", DbModelSourceUrlSupportedScheme.class)
                                                                      .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source URL Supported Schemes", e);
            sourceUrlSupportedSchemesQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(sourceUrlSupportedSchemesQueryResult);
    }

    @Override
    public List<DbModelSourceUrlSupportedProviderDto> getAllSourceUrlSupportedProviders() {
        List<DbModelSourceUrlSupportedProvider> sourceUrlSupportedProvidersQueryResult;
        try {
            sourceUrlSupportedProvidersQueryResult = this.sessionFactory.getCurrentSession()
                                                                        .createSelectionQuery("from DbModelSourceUrlSupportedProvider", DbModelSourceUrlSupportedProvider.class)
                                                                        .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source URL Supported Providers", e);
            sourceUrlSupportedProvidersQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(sourceUrlSupportedProvidersQueryResult);
    }

    @Override
    public DbModelSourceConfigDto getSourceConfig(BigInteger sourceConfigId) {
        Assert.notNull(sourceConfigId, "Was provided a null Database Model Source Config Id");
        DbModelSourceConfig sourceConfigQueryResult = null;
        try {
            sourceConfigQueryResult = this.sessionFactory.getCurrentSession()
                                                         .createSelectionQuery("from DbModelSourceConfig", DbModelSourceConfig.class)
                                                         .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source Config using ID [" + sourceConfigId + "]", e);
        }

        return DtoFactory.fromEntity(sourceConfigQueryResult);
    }

    @Override
    public List<DbModelSourceUrlDto> getAllSourceUrls() {
        List<DbModelSourceUrl> sourceUrlsQueryResult;
        try {
            sourceUrlsQueryResult = this.sessionFactory.getCurrentSession()
                                                       .createSelectionQuery("from DbModelSourceUrl", DbModelSourceUrl.class)
                                                       .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source URLs", e);
            sourceUrlsQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(sourceUrlsQueryResult);
    }

    @Override
    public List<DbModelSourceConfigDto> getAllSourceConfigs() {
        List<DbModelSourceConfig> sourceConfigsQueryResultConfig;
        try {
            sourceConfigsQueryResultConfig = this.sessionFactory.getCurrentSession()
                                                                .createSelectionQuery("from DbModelSourceConfig", DbModelSourceConfig.class)
                                                                .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source Configs", e);
            sourceConfigsQueryResultConfig = new ArrayList<>();
        }

        return DtoFactory.fromEntities(sourceConfigsQueryResultConfig);
    }

}
