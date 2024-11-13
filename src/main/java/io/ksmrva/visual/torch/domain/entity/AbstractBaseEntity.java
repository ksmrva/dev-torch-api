package io.ksmrva.visual.torch.domain.entity;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;
import java.util.Date;
import java.util.function.Supplier;

@MappedSuperclass
public abstract class AbstractBaseEntity<D extends AbstractBaseDto<D, E>, E extends AbstractBaseEntity<D, E>> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "created_uid")
    private String createdUid;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_uid")
    private String modifiedUid;

    @Column(name = "modified_date")
    private Date modifiedDate;

    public abstract D convertToDto();

    protected D createDtoWithBaseValues(Supplier<D> dtoSupplier) {
        D dto = null;
        if (dtoSupplier != null) {
            dto = dtoSupplier.get();
            dto.setId(this.getId());
            dto.setCreatedUid(this.getCreatedUid());
            dto.setCreatedDate(this.getCreatedDate());
            dto.setModifiedUid(this.getModifiedUid());
            dto.setModifiedDate(this.getModifiedDate());
        }
        return dto;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCreatedUid() {
        return createdUid;
    }

    public void setCreatedUid(String createdUid) {
        this.createdUid = createdUid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedUid() {
        return modifiedUid;
    }

    public void setModifiedUid(String modifiedUid) {
        this.modifiedUid = modifiedUid;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AbstractBaseEntity<?, ?> that)) {
            return false;
        }

        return new EqualsBuilder().append(getId(), that.getId())
                                  .append(getCreatedUid(), that.getCreatedUid())
                                  .append(getCreatedDate(), that.getCreatedDate())
                                  .append(getModifiedUid(), that.getModifiedUid())
                                  .append(getModifiedDate(), that.getModifiedDate())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCreatedUid())
                .append(getCreatedDate())
                .append(getModifiedUid())
                .append(getModifiedDate())
                .toHashCode();
    }

}
