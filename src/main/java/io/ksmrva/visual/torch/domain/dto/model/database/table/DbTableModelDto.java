package io.ksmrva.visual.torch.domain.dto.model.database.table;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.foreign.DbForeignKeyModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary.DbPrimaryKeyModelDto;
import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnModel;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.foreign.DbForeignKeyModel;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class DbTableModelDto extends AbstractBaseDto<DbTableModelDto, DbTableModel> {

    private String name;

    private String description;

    private List<DbColumnModelDto> columns;

    private DbPrimaryKeyModelDto primaryKey;

    private List<DbForeignKeyModelDto> foreignKeys;

    private DbTableCategoryDto tableCategory;

    @Override
    public DbTableModel convertToEntity() {
        DbTableModel entity = super.createEntityWithBaseValues(DbTableModel::new);

        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        if (this.getPrimaryKey() != null) {
            entity.setPrimaryKey(this.getPrimaryKey()
                                     .convertToEntity());
        }

        List<DbColumnModel> columns = new ArrayList<>();
        if (CollectionUtils.isEmpty(this.getColumns())) {
            for (DbColumnModelDto columnDto : this.getColumns()) {
                columns.add(columnDto.convertToEntity());
            }
        }
        entity.setColumns(columns);

        List<DbForeignKeyModel> foreignKeys = new ArrayList<>();
        if (CollectionUtils.isEmpty(this.getForeignKeys())) {
            for (DbForeignKeyModelDto foreignKeyDto : this.getForeignKeys()) {
                foreignKeys.add(foreignKeyDto.convertToEntity());
            }
        }
        entity.setForeignKeys(foreignKeys);

        if (this.getTableCategory() != null) {
            entity.setTableCategory(this.getTableCategory()
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

    public List<DbColumnModelDto> getColumns() {
        return columns;
    }

    public void setColumns(List<DbColumnModelDto> columns) {
        this.columns = columns;
    }

    public DbPrimaryKeyModelDto getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(DbPrimaryKeyModelDto primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<DbForeignKeyModelDto> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<DbForeignKeyModelDto> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public DbTableCategoryDto getTableCategory() {
        return tableCategory;
    }

    public void setTableCategory(DbTableCategoryDto tableCategory) {
        this.tableCategory = tableCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbTableModelDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getColumns(), that.getColumns())
                                  .append(getPrimaryKey(), that.getPrimaryKey())
                                  .append(getForeignKeys(), that.getForeignKeys())
                                  .append(getTableCategory(), that.getTableCategory())
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
                .append(getTableCategory())
                .toHashCode();
    }

    public static DbTableModelDto build(String name) {
        DbTableModelDto tableModelDto = new DbTableModelDto();
        tableModelDto.setName(name);

        tableModelDto.setDescription("");
        tableModelDto.setTableCategory(null);
        tableModelDto.setColumns(new ArrayList<>());
        tableModelDto.setForeignKeys(new ArrayList<>());
        tableModelDto.setPrimaryKey(null);

        return tableModelDto;
    }

}
