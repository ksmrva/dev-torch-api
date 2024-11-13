package io.ksmrva.visual.torch.domain.dto;

import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;
import java.util.Date;
import java.util.function.Supplier;

public abstract class AbstractBaseDto<D extends AbstractBaseDto<D, E>, E extends AbstractBaseEntity<D, E>> {

    private BigInteger id;

    private String createdUid;

    private Date createdDate;

    private String modifiedUid;

    private Date modifiedDate;

    public abstract E convertToEntity();

    protected E createEntityWithBaseValues(Supplier<E> entitySupplier) {
        E entity = null;
        if (entitySupplier != null) {
            entity = entitySupplier.get();
            if (this.getId() != null && this.getId()
                                            .compareTo(BigInteger.ZERO) > 0) {
                entity.setId(this.getId());
            }
            entity.setCreatedUid(this.getCreatedUid());
            entity.setCreatedDate(this.getCreatedDate());
            entity.setModifiedUid(this.getModifiedUid());
            entity.setModifiedDate(this.getModifiedDate());
        }
        return entity;
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

        if (!(o instanceof AbstractBaseDto<?, ?> that)) {
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
