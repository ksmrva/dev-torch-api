package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyColumnDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class SqlPrimaryKeyDetailDto extends AbstractBaseDto<SqlPrimaryKeyDetailDto, SqlPrimaryKeyDetail> {

    private String name;

    private String description;

    private List<SqlPrimaryKeyColumnDetailDto> columns;

    @Override
    public SqlPrimaryKeyDetail convertToEntity() {
        SqlPrimaryKeyDetail entity = super.createEntityWithBaseValues(SqlPrimaryKeyDetail::new);
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        List<SqlPrimaryKeyColumnDetail> primaryKeyColumns = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getColumns())) {
            for (SqlPrimaryKeyColumnDetailDto primaryKeyColumnDto : this.getColumns()) {
                primaryKeyColumns.add(primaryKeyColumnDto.convertToEntity());
            }
        }
        entity.setColumns(primaryKeyColumns);

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

    public List<SqlPrimaryKeyColumnDetailDto> getColumns() {
        return columns;
    }

    public void setColumns(List<SqlPrimaryKeyColumnDetailDto> columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlPrimaryKeyDetailDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getColumns(), that.getColumns())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getColumns())
                .toHashCode();
    }

    public static SqlPrimaryKeyDetailDto build(String name) {
        SqlPrimaryKeyDetailDto primaryKeyModelDto = new SqlPrimaryKeyDetailDto();
        primaryKeyModelDto.setName(name);
        primaryKeyModelDto.setDescription("");
        primaryKeyModelDto.setColumns(new ArrayList<>());

        return primaryKeyModelDto;
    }

}
