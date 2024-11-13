package io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary.DbPrimaryKeyColumnModel;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary.DbPrimaryKeyModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class DbPrimaryKeyModelDto extends AbstractBaseDto<DbPrimaryKeyModelDto, DbPrimaryKeyModel> {

    private String name;

    private String description;

    private List<DbPrimaryKeyColumnModelDto> primaryKeyModelColumns;

    @Override
    public DbPrimaryKeyModel convertToEntity() {
        DbPrimaryKeyModel entity = super.createEntityWithBaseValues(DbPrimaryKeyModel::new);

        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        List<DbPrimaryKeyColumnModel> primaryKeyColumns = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getPrimaryKeyModelColumns())) {
            for (DbPrimaryKeyColumnModelDto primaryKeyColumnDto : this.getPrimaryKeyModelColumns()) {
                primaryKeyColumns.add(primaryKeyColumnDto.convertToEntity());
            }
        }
        entity.setPrimaryKeyColumns(primaryKeyColumns);

        return entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DbPrimaryKeyColumnModelDto> getPrimaryKeyModelColumns() {
        return primaryKeyModelColumns;
    }

    public void setPrimaryKeyModelColumns(List<DbPrimaryKeyColumnModelDto> primaryKeyModelColumns) {
        this.primaryKeyModelColumns = primaryKeyModelColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbPrimaryKeyModelDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getPrimaryKeyModelColumns(), that.getPrimaryKeyModelColumns())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getPrimaryKeyModelColumns())
                .toHashCode();
    }

    public static DbPrimaryKeyModelDto build(String name) {
        DbPrimaryKeyModelDto primaryKeyModelDto = new DbPrimaryKeyModelDto();
        primaryKeyModelDto.setName(name);

        primaryKeyModelDto.setDescription("");
        primaryKeyModelDto.setPrimaryKeyModelColumns(new ArrayList<>());

        return primaryKeyModelDto;
    }

}
