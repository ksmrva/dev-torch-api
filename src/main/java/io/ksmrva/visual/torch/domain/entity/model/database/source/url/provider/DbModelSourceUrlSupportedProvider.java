package io.ksmrva.visual.torch.domain.entity.model.database.source.url.provider;

import io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider.DbModelSourceUrlSupportedProviderDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "db_model_source_url_supported_provider", schema = "model")
public class DbModelSourceUrlSupportedProvider extends AbstractBaseEntity<DbModelSourceUrlSupportedProviderDto, DbModelSourceUrlSupportedProvider> {

    @Column(name = "provider")
    private String provider;

    @Override
    public DbModelSourceUrlSupportedProviderDto convertToDto() {
        DbModelSourceUrlSupportedProviderDto dto = super.createDtoWithBaseValues(DbModelSourceUrlSupportedProviderDto::new);
        dto.setProvider(this.getProvider());

        return dto;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModelSourceUrlSupportedProvider that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getProvider(), that.getProvider())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getProvider())
                .toHashCode();
    }

    public static DbModelSourceUrlSupportedProvider build(String provider) {
        DbModelSourceUrlSupportedProvider supportedProvider = new DbModelSourceUrlSupportedProvider();
        supportedProvider.setProvider(provider);

        return supportedProvider;
    }

}
