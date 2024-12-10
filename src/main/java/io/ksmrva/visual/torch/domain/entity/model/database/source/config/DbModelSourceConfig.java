package io.ksmrva.visual.torch.domain.entity.model.database.source.config;

import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.source.url.DbModelSourceUrl;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "config", schema = "db_model_source")
public class DbModelSourceConfig extends AbstractBaseEntity<DbModelSourceConfigDto, DbModelSourceConfig> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id")
    private DbModelSourceUrl url;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Override
    public DbModelSourceConfigDto convertToDto() {
        DbModelSourceConfigDto dto = super.createDtoWithBaseValues(DbModelSourceConfigDto::new);

        if (this.getUrl() != null) {
            dto.setUrl(this.getUrl()
                           .convertToDto());
        }

        dto.setDriverName(this.getDriverName());
        dto.setUsername(this.getUsername());
        dto.setPassword(this.getPassword());

        return dto;
    }

    public DbModelSourceUrl getUrl() {
        return url;
    }

    public void setUrl(DbModelSourceUrl url) {
        this.url = url;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModelSourceConfig that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getUrl(), that.getUrl())
                                  .append(getDriverName(), that.getDriverName())
                                  .append(getUsername(), that.getUsername())
                                  .append(getPassword(), that.getPassword())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getUrl())
                .append(getDriverName())
                .append(getUsername())
                .append(getPassword())
                .toHashCode();
    }

}
