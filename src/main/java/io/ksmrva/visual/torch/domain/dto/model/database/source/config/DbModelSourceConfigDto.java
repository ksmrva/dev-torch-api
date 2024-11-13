package io.ksmrva.visual.torch.domain.dto.model.database.source.config;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.url.DbModelSourceUrlDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbModelSourceConfigDto extends AbstractBaseDto<DbModelSourceConfigDto, DbModelSourceConfig> {

    private DbModelSourceUrlDto url;

    private String driverName;

    private String username;

    private String password;

    @Override
    public DbModelSourceConfig convertToEntity() {
        DbModelSourceConfig entity = super.createEntityWithBaseValues(DbModelSourceConfig::new);
        if (this.getUrl() != null) {
            entity.setSourceUrl(this.getUrl()
                                    .convertToEntity());
        }

        entity.setDriverName(this.getDriverName());
        entity.setUsername(this.getUsername());
        entity.setPassword(this.getPassword());

        return entity;
    }

    public DbModelSourceUrlDto getUrl() {
        return url;
    }

    public void setUrl(DbModelSourceUrlDto url) {
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

        if (!(o instanceof DbModelSourceConfigDto that)) {
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

    public static DbModelSourceConfigDto build(DbModelSourceUrlDto url, String driverName, String username, String password) {
        DbModelSourceConfigDto sourceDto = new DbModelSourceConfigDto();
        sourceDto.setUrl(url);
        sourceDto.setDriverName(driverName);
        sourceDto.setUsername(username);
        sourceDto.setPassword(password);

        return sourceDto;
    }

}
