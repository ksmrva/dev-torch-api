package io.ksmrva.visual.torch.domain.dto.model.database.source.preset;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.preset.DbModelSourcePreset;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbModelSourcePresetDto extends AbstractBaseDto<DbModelSourcePresetDto, DbModelSourcePreset> {

    private String name;

    private String scheme;

    private String provider;

    private String driverName;

    @Override
    public DbModelSourcePreset convertToEntity() {
        DbModelSourcePreset entity = super.createEntityWithBaseValues(DbModelSourcePreset::new);
        entity.setName(this.getName());
        entity.setScheme(this.getScheme());
        entity.setProvider(this.getProvider());
        entity.setDriverName(this.getDriverName());

        return entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModelSourcePresetDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getScheme(), that.getScheme())
                                  .append(getProvider(), that.getProvider())
                                  .append(getDriverName(), that.getDriverName())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getScheme())
                .append(getProvider())
                .append(getDriverName())
                .toHashCode();
    }

    public static DbModelSourcePresetDto build(String name, String scheme, String provider, String driverName) {
        DbModelSourcePresetDto dbModelSourcePresetDto = new DbModelSourcePresetDto();
        dbModelSourcePresetDto.setName(name);
        dbModelSourcePresetDto.setScheme(scheme);
        dbModelSourcePresetDto.setProvider(provider);
        dbModelSourcePresetDto.setDriverName(driverName);

        return dbModelSourcePresetDto;
    }

}
