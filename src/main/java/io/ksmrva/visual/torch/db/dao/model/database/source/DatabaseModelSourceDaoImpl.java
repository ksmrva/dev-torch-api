package io.ksmrva.visual.torch.db.dao.model.database.source;

import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.driver.DbModelSourceConfigSupportedDriverDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.preset.DbModelSourcePresetDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.DbModelSourceUrlDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider.DbModelSourceUrlSupportedProviderDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme.DbModelSourceUrlSupportedSchemeDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.driver.DbModelSourceConfigSupportedDriver;
import io.ksmrva.visual.torch.domain.entity.model.database.source.data.DbModelSourceDataType;
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
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Types;
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
    public List<DbModelSourceDataTypeDto> createAllDataTypes() {
        List<DbModelSourceDataTypeDto> dataTypeDtosToReturn = new ArrayList<>();

        List<DbModelSourceDataType> createdDataTypes = DatabaseModelSourceDaoImpl.createDataTypesFromJavaSqlTypes();
        for (DbModelSourceDataType createdDataType : createdDataTypes) {
            this.sessionFactory.getCurrentSession()
                               .persist(createdDataType);

            dataTypeDtosToReturn.add(createdDataType.convertToDto());
        }

        LOGGER.info("Created [" + dataTypeDtosToReturn.size() + "] Data Types");
        return dataTypeDtosToReturn;
    }

    @Override
    public DbModelSourceUrlDto createUrl(DbModelSourceUrlDto urlDtoToCreate) {
        Assert.notNull(urlDtoToCreate, "Was provided a null Database Model Source URL to create");
        DbModelSourceUrl url = DtoFactory.toEntity(urlDtoToCreate);
        this.sessionFactory.getCurrentSession()
                           .persist(url);

        return url.convertToDto();
    }

    @Override
    public DbModelSourceConfigDto createConfig(DbModelSourceConfigDto configDtoToCreate) {
        Assert.notNull(configDtoToCreate, "Was provided a null Database Model Source Config to create");
        DbModelSourceConfig config = DtoFactory.toEntity(configDtoToCreate);
        this.sessionFactory.getCurrentSession()
                           .persist(config);

        return config.convertToDto();
    }

    /**
     * Read
     **/
    @Override
    public List<DbModelSourceDataTypeDto> getOrCreateAllDataTypes() {
        List<DbModelSourceDataTypeDto> dataTypeDtos = this.getAllDataTypes();
        if (CollectionUtils.isEmpty(dataTypeDtos)) {
            LOGGER.warn("Found no Data Types, attempting to create them");

            dataTypeDtos = this.createAllDataTypes();
            if (CollectionUtils.isEmpty(dataTypeDtos)) {
                LOGGER.warn("Failed to create any Data Types");
            }
        }
        return dataTypeDtos;
    }

    @Override
    public List<DbModelSourceDataTypeDto> getAllDataTypes() {
        List<DbModelSourceDataType> dataTypesQueryResult;
        try {
            dataTypesQueryResult = this.sessionFactory.getCurrentSession()
                                                      .createSelectionQuery("from DbModelSourceDataType", DbModelSourceDataType.class)
                                                      .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Data Types", e);
            dataTypesQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(dataTypesQueryResult);
    }

    @Override
    public List<DbModelSourcePresetDto> getAllPresets() {
        List<DbModelSourcePreset> presetsQueryResult;
        try {
            presetsQueryResult = this.sessionFactory.getCurrentSession()
                                                    .createSelectionQuery("from DbModelSourcePreset", DbModelSourcePreset.class)
                                                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source Presets", e);
            presetsQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(presetsQueryResult);
    }

    @Override
    public List<DbModelSourceConfigSupportedDriverDto> getAllConfigSupportedDrivers() {
        List<DbModelSourceConfigSupportedDriver> configSupportedDriversQueryResult;
        try {
            configSupportedDriversQueryResult = this.sessionFactory.getCurrentSession()
                                                                   .createSelectionQuery("from DbModelSourceConfigSupportedDriver", DbModelSourceConfigSupportedDriver.class)
                                                                   .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source Config Supported Drivers", e);
            configSupportedDriversQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(configSupportedDriversQueryResult);
    }

    @Override
    public List<DbModelSourceUrlSupportedSchemeDto> getAllUrlSupportedSchemes() {
        List<DbModelSourceUrlSupportedScheme> urlSupportedSchemesQueryResult;
        try {
            urlSupportedSchemesQueryResult = this.sessionFactory.getCurrentSession()
                                                                .createSelectionQuery("from DbModelSourceUrlSupportedScheme", DbModelSourceUrlSupportedScheme.class)
                                                                .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source URL Supported Schemes", e);
            urlSupportedSchemesQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(urlSupportedSchemesQueryResult);
    }

    @Override
    public List<DbModelSourceUrlSupportedProviderDto> getAllUrlSupportedProviders() {
        List<DbModelSourceUrlSupportedProvider> urlSupportedProvidersQueryResult;
        try {
            urlSupportedProvidersQueryResult = this.sessionFactory.getCurrentSession()
                                                                  .createSelectionQuery("from DbModelSourceUrlSupportedProvider", DbModelSourceUrlSupportedProvider.class)
                                                                  .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source URL Supported Providers", e);
            urlSupportedProvidersQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(urlSupportedProvidersQueryResult);
    }

    @Override
    public DbModelSourceConfigDto getConfig(BigInteger configId) {
        Assert.notNull(configId, "Was provided a null Database Model Source Config ID");
        DbModelSourceConfig configQueryResult = null;
        try {
            configQueryResult = this.sessionFactory.getCurrentSession()
                                                   .createSelectionQuery("from DbModelSourceConfig", DbModelSourceConfig.class)
                                                   .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source Config using ID [" + configId + "]", e);
        }

        return DtoFactory.fromEntity(configQueryResult);
    }

    @Override
    public List<DbModelSourceUrlDto> getAllUrls() {
        List<DbModelSourceUrl> urlsQueryResult;
        try {
            urlsQueryResult = this.sessionFactory.getCurrentSession()
                                                 .createSelectionQuery("from DbModelSourceUrl", DbModelSourceUrl.class)
                                                 .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source URLs", e);
            urlsQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(urlsQueryResult);
    }

    @Override
    public List<DbModelSourceConfigDto> getAllConfigs() {
        List<DbModelSourceConfig> configsQueryResultConfig;
        try {
            configsQueryResultConfig = this.sessionFactory.getCurrentSession()
                                                          .createSelectionQuery("from DbModelSourceConfig", DbModelSourceConfig.class)
                                                          .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Model Source Configs", e);
            configsQueryResultConfig = new ArrayList<>();
        }

        return DtoFactory.fromEntities(configsQueryResultConfig);
    }

    private static List<DbModelSourceDataType> createDataTypesFromJavaSqlTypes() {
        List<DbModelSourceDataType> dataTypes = new ArrayList<>();
        Class<?> javaSqlTypesClazz = Types.class;
        List<Field> staticIntFields = DatabaseModelSourceDaoImpl.getAllStaticIntFieldsFromJavaClazz(javaSqlTypesClazz);

        for (Field field : staticIntFields) {
            String fieldName = field.getName();
            Object fieldValue;
            try {
                // Since we are getting the static value we do not need an instance to pass into the get() method and can just pass null
                fieldValue = field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to find the value for static Field using Name [" + fieldName + "] for Class [" + javaSqlTypesClazz + "] due to [" + e.getLocalizedMessage() + "]", e);
            }

            Class<?> fieldValueType = fieldValue.getClass();
            boolean isFieldValueTypeInteger = DatabaseModelSourceDaoImpl.isTypeInteger(fieldValueType);
            if (!isFieldValueTypeInteger) {
                throw new RuntimeException("Found a non-Integer value Type [" + fieldValueType + "] for static Field using Name [" + fieldName + "] for Class [" + javaSqlTypesClazz + "]");
            }

            LOGGER.info("Found static int Field with Name [" + field.getName() + "] and Value [" + fieldValue + "] for Class [" + javaSqlTypesClazz + "]");
            DbModelSourceDataType dbModelSourceDataType = new DbModelSourceDataType();
            dbModelSourceDataType.setName(fieldName);
            dbModelSourceDataType.setJavaSqlConstant((Integer) fieldValue);

            dataTypes.add(dbModelSourceDataType);
        }
        return dataTypes;
    }

    private static List<Field> getAllStaticIntFieldsFromJavaClazz(Class<?> clazz) {
        List<Field> staticIntFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            boolean isStatic = java.lang.reflect.Modifier.isStatic(field.getModifiers());
            boolean isPublic = java.lang.reflect.Modifier.isPublic(field.getModifiers());
            boolean isTypeInteger = DatabaseModelSourceDaoImpl.isTypeInteger(field.getType());
            if (isStatic && isPublic && isTypeInteger) {
                staticIntFields.add(field);
            }
        }
        return staticIntFields;
    }

    private static boolean isTypeInteger(Class<?> type) {
        return type.equals(Integer.TYPE) || type.equals(Integer.class);
    }

}
