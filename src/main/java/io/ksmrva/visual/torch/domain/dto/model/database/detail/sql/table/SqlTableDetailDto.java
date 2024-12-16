package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.foreign.SqlForeignKeyDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyDetailDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column.SqlColumnDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.foreign.SqlForeignKeyDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class SqlTableDetailDto extends AbstractBaseDto<SqlTableDetailDto, SqlTableDetail> {

    private String name;

    private String description;

    private List<SqlColumnDetailDto> columns;

    private SqlPrimaryKeyDetailDto primaryKey;

    private List<SqlForeignKeyDetailDto> foreignKeys;

    private CollectionCategoryDto category;

    @Override
    public SqlTableDetail convertToEntity() {
        SqlTableDetail entity = super.createEntityWithBaseValues(SqlTableDetail::new);
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        if (this.getPrimaryKey() != null) {
            entity.setPrimaryKey(this.getPrimaryKey()
                                     .convertToEntity());
        }

        List<SqlColumnDetail> columns = new ArrayList<>();
        if (CollectionUtils.isEmpty(this.getColumns())) {
            for (SqlColumnDetailDto columnDto : this.getColumns()) {
                columns.add(columnDto.convertToEntity());
            }
        }
        entity.setColumns(columns);

        List<SqlForeignKeyDetail> foreignKeys = new ArrayList<>();
        if (CollectionUtils.isEmpty(this.getForeignKeys())) {
            for (SqlForeignKeyDetailDto foreignKeyDto : this.getForeignKeys()) {
                foreignKeys.add(foreignKeyDto.convertToEntity());
            }
        }
        entity.setForeignKeys(foreignKeys);

        if (this.getCategory() != null) {
            entity.setCategory(this.getCategory()
                                   .convertToEntity());
        }

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

    public List<SqlColumnDetailDto> getColumns() {
        return columns;
    }

    public void setColumns(List<SqlColumnDetailDto> columns) {
        this.columns = columns;
    }

    public SqlPrimaryKeyDetailDto getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(SqlPrimaryKeyDetailDto primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<SqlForeignKeyDetailDto> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<SqlForeignKeyDetailDto> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public CollectionCategoryDto getCategory() {
        return category;
    }

    public void setCategory(CollectionCategoryDto category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlTableDetailDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getColumns(), that.getColumns())
                                  .append(getPrimaryKey(), that.getPrimaryKey())
                                  .append(getForeignKeys(), that.getForeignKeys())
                                  .append(getCategory(), that.getCategory())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getColumns())
                .append(getPrimaryKey())
                .append(getForeignKeys())
                .append(getCategory())
                .toHashCode();
    }

    public static SqlTableDetailDto build(String name) {
        SqlTableDetailDto tableModelDto = new SqlTableDetailDto();
        tableModelDto.setName(name);
        tableModelDto.setDescription("");
        tableModelDto.setCategory(null);
        tableModelDto.setColumns(new ArrayList<>());
        tableModelDto.setForeignKeys(new ArrayList<>());
        tableModelDto.setPrimaryKey(null);

        return tableModelDto;
    }

}
