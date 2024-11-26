package io.ksmrva.visual.torch.domain.entity.model.database.source.preset;

import io.ksmrva.visual.torch.domain.dto.model.database.source.preset.DbModelSourcePresetDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "source_preset", schema = "db_model")
public class DbModelSourcePreset extends AbstractBaseEntity<DbModelSourcePresetDto, DbModelSourcePreset> {

    @Column(name = "name")
    private String name;

    @Column(name = "scheme")
    private String scheme;

    @Column(name = "provider")
    private String provider;

    @Column(name = "driver_name")
    private String driverName;

    @Override
    public DbModelSourcePresetDto convertToDto() {
        DbModelSourcePresetDto dto = super.createDtoWithBaseValues(DbModelSourcePresetDto::new);
        dto.setName(this.getName());
        dto.setScheme(this.getScheme());
        dto.setProvider(this.getProvider());
        dto.setDriverName(this.getDriverName());

        return dto;
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

        if (!(o instanceof DbModelSourcePreset that)) {
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

    public static DbModelSourcePreset build(String name, String scheme, String provider, String driverName) {
        DbModelSourcePreset dbModelSourcePreset = new DbModelSourcePreset();
        dbModelSourcePreset.setName(name);
        dbModelSourcePreset.setScheme(scheme);
        dbModelSourcePreset.setProvider(provider);
        dbModelSourcePreset.setDriverName(driverName);

        return dbModelSourcePreset;
    }

}
