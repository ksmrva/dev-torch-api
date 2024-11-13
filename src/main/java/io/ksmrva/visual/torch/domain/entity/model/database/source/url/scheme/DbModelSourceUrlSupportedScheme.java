package io.ksmrva.visual.torch.domain.entity.model.database.source.url.scheme;

import io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme.DbModelSourceUrlSupportedSchemeDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "db_model_source_url_supported_scheme", schema = "model")
public class DbModelSourceUrlSupportedScheme extends AbstractBaseEntity<DbModelSourceUrlSupportedSchemeDto, DbModelSourceUrlSupportedScheme> {

    @Column(name = "scheme")
    private String scheme;

    @Override
    public DbModelSourceUrlSupportedSchemeDto convertToDto() {
        DbModelSourceUrlSupportedSchemeDto dto = super.createDtoWithBaseValues(DbModelSourceUrlSupportedSchemeDto::new);
        dto.setScheme(this.getScheme());

        return dto;
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

        if (!(o instanceof DbModelSourceUrlSupportedScheme that)) {
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

    public static DbModelSourceUrlSupportedScheme build(String scheme) {
        DbModelSourceUrlSupportedScheme supportedScheme = new DbModelSourceUrlSupportedScheme();
        supportedScheme.setScheme(scheme);

        return supportedScheme;
    }

}

