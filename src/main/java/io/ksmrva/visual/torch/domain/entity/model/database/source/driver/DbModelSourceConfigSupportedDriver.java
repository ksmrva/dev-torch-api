package io.ksmrva.visual.torch.domain.entity.model.database.source.driver;

import io.ksmrva.visual.torch.domain.dto.model.database.source.driver.DbModelSourceConfigSupportedDriverDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "db_model_source_config_supported_driver", schema = "model")
public class DbModelSourceConfigSupportedDriver extends AbstractBaseEntity<DbModelSourceConfigSupportedDriverDto, DbModelSourceConfigSupportedDriver> {

    @Column(name = "name")
    private String name;

    @Override
    public DbModelSourceConfigSupportedDriverDto convertToDto() {
        DbModelSourceConfigSupportedDriverDto dto = super.createDtoWithBaseValues(DbModelSourceConfigSupportedDriverDto::new);
        dto.setName(this.getName());

        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModelSourceConfigSupportedDriver that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .toHashCode();
    }

    public static DbModelSourceConfigSupportedDriver build(String name) {
        DbModelSourceConfigSupportedDriver supportedDriver = new DbModelSourceConfigSupportedDriver();
        supportedDriver.setName(name);

        return supportedDriver;
    }

}