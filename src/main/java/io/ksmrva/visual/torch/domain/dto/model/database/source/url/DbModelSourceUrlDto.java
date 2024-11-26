package io.ksmrva.visual.torch.domain.dto.model.database.source.url;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.url.DbModelSourceUrl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbModelSourceUrlDto extends AbstractBaseDto<DbModelSourceUrlDto, DbModelSourceUrl> {

    private String scheme;

    private String provider;

    private String hostname;

    private String port;

    private String adminDatabaseName;

    @Override
    public DbModelSourceUrl convertToEntity() {
        DbModelSourceUrl entity = super.createEntityWithBaseValues(DbModelSourceUrl::new);
        entity.setScheme(this.getScheme());
        entity.setProvider(this.getProvider());
        entity.setHostname(this.getHostname());
        entity.setPort(this.getPort());
        entity.setAdminDatabaseName(this.getAdminDatabaseName());

        return entity;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAdminDatabaseName() {
        return adminDatabaseName;
    }

    public void setAdminDatabaseName(String adminDatabaseName) {
        this.adminDatabaseName = adminDatabaseName;
    }

    public String getFullyFormedUrl() {
        return DbModelSourceUrlDto.createFullyFormedUrl(scheme, provider, hostname, port, adminDatabaseName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModelSourceUrlDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getScheme(), that.getScheme())
                                  .append(getProvider(), that.getProvider())
                                  .append(getHostname(), that.getHostname())
                                  .append(getPort(), that.getPort())
                                  .append(getAdminDatabaseName(), that.getAdminDatabaseName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getScheme())
                .append(getProvider())
                .append(getHostname())
                .append(getPort())
                .append(getAdminDatabaseName())
                .toHashCode();
    }

    public static DbModelSourceUrlDto build(String scheme, String provider, String hostname, String port, String adminDatabaseName) {
        DbModelSourceUrlDto sourceUrlDto = new DbModelSourceUrlDto();
        sourceUrlDto.setScheme(scheme);
        sourceUrlDto.setProvider(provider);
        sourceUrlDto.setHostname(hostname);
        sourceUrlDto.setPort(port);
        sourceUrlDto.setAdminDatabaseName(adminDatabaseName);

        return sourceUrlDto;
    }

    public static String createFullyFormedUrl(String scheme, String provider, String hostname, String port, String adminDatabaseName) {
        return scheme + ":" + provider + "://" + hostname + ":" + port + "/" + adminDatabaseName;
    }

}
