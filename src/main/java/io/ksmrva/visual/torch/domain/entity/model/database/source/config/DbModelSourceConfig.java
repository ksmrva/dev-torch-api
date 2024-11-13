package io.ksmrva.visual.torch.domain.entity.model.database.source.config;

import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.source.url.DbModelSourceUrl;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "db_model_source_config", schema = "model")
public class DbModelSourceConfig extends AbstractBaseEntity<DbModelSourceConfigDto, DbModelSourceConfig> {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_url_id")
    private DbModelSourceUrl sourceUrl;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Override
    public DbModelSourceConfigDto convertToDto() {
        DbModelSourceConfigDto dto = super.createDtoWithBaseValues(DbModelSourceConfigDto::new);

        if (this.getSourceUrl() != null) {
            dto.setUrl(this.getSourceUrl().convertToDto());
        }

        dto.setDriverName(this.getDriverName());
        dto.setUsername(this.getUsername());
        dto.setPassword(this.getPassword());

        return dto;
    }

    public DbModelSourceUrl getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(DbModelSourceUrl sourceUrl) {
        this.sourceUrl = sourceUrl;
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

}
