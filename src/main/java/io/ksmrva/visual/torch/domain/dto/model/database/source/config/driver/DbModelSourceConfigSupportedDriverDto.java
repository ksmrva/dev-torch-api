package io.ksmrva.visual.torch.domain.dto.model.database.source.config.driver;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.driver.DbModelSourceConfigSupportedDriver;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbModelSourceConfigSupportedDriverDto extends AbstractBaseDto<DbModelSourceConfigSupportedDriverDto, DbModelSourceConfigSupportedDriver> {

    private String name;

    @Override
    public DbModelSourceConfigSupportedDriver convertToEntity() {
        DbModelSourceConfigSupportedDriver entity = super.createEntityWithBaseValues(DbModelSourceConfigSupportedDriver::new);
        entity.setName(this.getName());

        return entity;
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

        if (!(o instanceof DbModelSourceConfigSupportedDriverDto that)) {
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

    public static DbModelSourceConfigSupportedDriverDto build(String name) {
        DbModelSourceConfigSupportedDriverDto sourceConfigSupportedDriverDto = new DbModelSourceConfigSupportedDriverDto();
        sourceConfigSupportedDriverDto.setName(name);

        return sourceConfigSupportedDriverDto;
    }

}
