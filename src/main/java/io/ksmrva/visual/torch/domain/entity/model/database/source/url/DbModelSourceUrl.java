package io.ksmrva.visual.torch.domain.entity.model.database.source.url;

import io.ksmrva.visual.torch.domain.dto.model.database.source.url.DbModelSourceUrlDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "source_url", schema = "db_model")
public class DbModelSourceUrl extends AbstractBaseEntity<DbModelSourceUrlDto, DbModelSourceUrl> {

    @Column(name = "scheme")
    private String scheme;

    @Column(name = "provider")
    private String provider;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "port")
    private String port;

    @Column(name = "admin_db_name")
    private String adminDatabaseName;

    @Override
    public DbModelSourceUrlDto convertToDto() {
        DbModelSourceUrlDto dto = super.createDtoWithBaseValues(DbModelSourceUrlDto::new);
        dto.setScheme(this.getScheme());
        dto.setProvider(this.getProvider());
        dto.setHostname(this.getHostname());
        dto.setPort(this.getPort());
        dto.setAdminDatabaseName(this.getAdminDatabaseName());

        return dto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModelSourceUrl that)) {
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

    public static DbModelSourceUrl build(String scheme, String provider, String hostname, String port, String adminDatabaseName) {
        DbModelSourceUrl sourceUrl = new DbModelSourceUrl();
        sourceUrl.setScheme(scheme);
        sourceUrl.setProvider(provider);
        sourceUrl.setHostname(hostname);
        sourceUrl.setPort(port);
        sourceUrl.setAdminDatabaseName(adminDatabaseName);

        return sourceUrl;
    }

}
