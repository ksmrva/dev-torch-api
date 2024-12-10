package io.ksmrva.visual.torch.domain.dto.model.database.component.table;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.foreign.ForeignKeyComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.primary.PrimaryKeyComponentDto;
import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.foreign.ForeignKeyComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class TableComponentDto extends AbstractBaseDto<TableComponentDto, TableComponent> {

    private String name;

    private String description;

    private List<ColumnComponentDto> columns;

    private PrimaryKeyComponentDto primaryKey;

    private List<ForeignKeyComponentDto> foreignKeys;

    private TableCategoryDto category;

    @Override
    public TableComponent convertToEntity() {
        TableComponent entity = super.createEntityWithBaseValues(TableComponent::new);
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        if (this.getPrimaryKey() != null) {
            entity.setPrimaryKey(this.getPrimaryKey()
                                     .convertToEntity());
        }

        List<ColumnComponent> columns = new ArrayList<>();
        if (CollectionUtils.isEmpty(this.getColumns())) {
            for (ColumnComponentDto columnDto : this.getColumns()) {
                columns.add(columnDto.convertToEntity());
            }
        }
        entity.setColumns(columns);

        List<ForeignKeyComponent> foreignKeys = new ArrayList<>();
        if (CollectionUtils.isEmpty(this.getForeignKeys())) {
            for (ForeignKeyComponentDto foreignKeyDto : this.getForeignKeys()) {
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

    public List<ColumnComponentDto> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnComponentDto> columns) {
        this.columns = columns;
    }

    public PrimaryKeyComponentDto getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKeyComponentDto primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<ForeignKeyComponentDto> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKeyComponentDto> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public TableCategoryDto getCategory() {
        return category;
    }

    public void setCategory(TableCategoryDto category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TableComponentDto that)) {
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

    public static TableComponentDto build(String name) {
        TableComponentDto tableModelDto = new TableComponentDto();
        tableModelDto.setName(name);
        tableModelDto.setDescription("");
        tableModelDto.setCategory(null);
        tableModelDto.setColumns(new ArrayList<>());
        tableModelDto.setForeignKeys(new ArrayList<>());
        tableModelDto.setPrimaryKey(null);

        return tableModelDto;
    }

}
