package io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.source.url.scheme.DbModelSourceUrlSupportedScheme;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbModelSourceUrlSupportedSchemeDto extends AbstractBaseDto<DbModelSourceUrlSupportedSchemeDto, DbModelSourceUrlSupportedScheme> {

    private String scheme;

    @Override
    public DbModelSourceUrlSupportedScheme convertToEntity() {
        DbModelSourceUrlSupportedScheme entity = super.createEntityWithBaseValues(DbModelSourceUrlSupportedScheme::new);
        entity.setScheme(this.getScheme());

        return entity;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModelSourceUrlSupportedSchemeDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getScheme(), that.getScheme())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getScheme())
                .toHashCode();
    }

    public static DbModelSourceUrlSupportedSchemeDto build(String scheme) {
        DbModelSourceUrlSupportedSchemeDto supportedSchemeDto = new DbModelSourceUrlSupportedSchemeDto();
        supportedSchemeDto.setScheme(scheme);

        return supportedSchemeDto;
    }

}
