package io.ksmrva.visual.torch.domain.entity.model.database.component.table;

import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.foreign.ForeignKeyComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.component.DatabaseComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.foreign.ForeignKeyComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyComponent;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "table", schema = "db_model_component")
public class TableComponent extends AbstractBaseEntity<TableComponentDto, TableComponent> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "database_id")
    private DatabaseComponent database;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private TableCategory category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ColumnComponent> columns;

    @OneToOne(mappedBy = "table",
              cascade = {CascadeType.ALL}, orphanRemoval = true)
    private PrimaryKeyComponent primaryKey;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ForeignKeyComponent> foreignKeys;

    @Override
    public TableComponentDto convertToDto() {
        TableComponentDto dto = super.createDtoWithBaseValues(TableComponentDto::new);
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        if (this.getPrimaryKey() != null) {
            dto.setPrimaryKey(this.getPrimaryKey()
                                  .convertToDto());
        }

        List<ColumnComponentDto> columnDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getColumns())) {
            for (ColumnComponent column : this.getColumns()) {
                columnDtos.add(column.convertToDto());
            }
        }
        dto.setColumns(columnDtos);

        List<ForeignKeyComponentDto> foreignKeyDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getForeignKeys())) {
            for (ForeignKeyComponent foreignKey : this.getForeignKeys()) {
                foreignKeyDtos.add(foreignKey.convertToDto());
            }
        }
        dto.setForeignKeys(foreignKeyDtos);

        if (this.getCategory() != null) {
            dto.setCategory(this.getCategory()
                                .convertToDto());
        }
        return dto;
    }

    public DatabaseComponent getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseComponent database) {
        this.database = database;
    }

    public TableCategory getCategory() {
        return category;
    }

    public void setCategory(TableCategory category) {
        this.category = category;
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

    public List<ColumnComponent> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnComponent> columns) {
        this.columns = columns;
    }

    public PrimaryKeyComponent getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKeyComponent primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<ForeignKeyComponent> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKeyComponent> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TableComponent that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getDatabase(), that.getDatabase())
                                  .append(getCategory(), that.getCategory())
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getColumns(), that.getColumns())
                                  .append(getPrimaryKey(), that.getPrimaryKey())
                                  .append(getForeignKeys(), that.getForeignKeys())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getDatabase())
                .append(getCategory())
                .append(getName())
                .append(getDescription())
                .append(getColumns())
                .append(getPrimaryKey())
                .append(getForeignKeys())
                .toHashCode();
    }

    public TableComponent build(DatabaseComponent database, String name) {
        TableComponent tableModel = new TableComponent();
        tableModel.setDatabase(database);
        tableModel.setName(name);
        tableModel.setCategory(null);
        tableModel.setDescription("");
        tableModel.setColumns(new ArrayList<>());
        tableModel.setPrimaryKey(null);
        tableModel.setForeignKeys(new ArrayList<>());

        return tableModel;
    }

}
