package io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.url.provider.DbModelSourceUrlSupportedProvider;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbModelSourceUrlSupportedProviderDto extends AbstractBaseDto<DbModelSourceUrlSupportedProviderDto, DbModelSourceUrlSupportedProvider> {

    private String provider;

    @Override
    public DbModelSourceUrlSupportedProvider convertToEntity() {
        DbModelSourceUrlSupportedProvider entity = super.createEntityWithBaseValues(DbModelSourceUrlSupportedProvider::new);
        entity.setProvider(this.getProvider());

        return entity;
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

        if (!(o instanceof DbModelSourceUrlSupportedProviderDto that)) {
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

    public static DbModelSourceUrlSupportedProviderDto build(String provider) {
        DbModelSourceUrlSupportedProviderDto supportedProviderDto = new DbModelSourceUrlSupportedProviderDto();
        supportedProviderDto.setProvider(provider);

        return supportedProviderDto;
    }

}
