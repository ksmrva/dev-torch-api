package io.ksmrva.visual.torch.domain.entity.model.database.table;

import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.foreign.DbForeignKeyModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.DbModel;
import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnModel;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.foreign.DbForeignKeyModel;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary.DbPrimaryKeyModel;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "table_model", schema = "db_model")
public class DbTableModel extends AbstractBaseEntity<DbTableModelDto, DbTableModel> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "database_id")
    private DbModel database;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_category_id")
    private DbTableCategory tableCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DbColumnModel> columns;

    @OneToOne(mappedBy = "table",
              cascade = {CascadeType.ALL}, orphanRemoval = true)
    private DbPrimaryKeyModel primaryKey;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DbForeignKeyModel> foreignKeys;

    @Override
    public DbTableModelDto convertToDto() {
        DbTableModelDto dto = super.createDtoWithBaseValues(DbTableModelDto::new);

        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        if (this.getPrimaryKey() != null) {
            dto.setPrimaryKey(this.getPrimaryKey()
                                  .convertToDto());
        }

        List<DbColumnModelDto> columnDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getColumns())) {
            for (DbColumnModel column : this.getColumns()) {
                columnDtos.add(column.convertToDto());
            }
        }
        dto.setColumns(columnDtos);

        List<DbForeignKeyModelDto> foreignKeyDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getForeignKeys())) {
            for (DbForeignKeyModel foreignKey : this.getForeignKeys()) {
                foreignKeyDtos.add(foreignKey.convertToDto());
            }
        }
        dto.setForeignKeys(foreignKeyDtos);

        if (this.getTableCategory() != null) {
            dto.setTableCategory(this.getTableCategory()
                                     .convertToDto());
        }
        return dto;
    }

    public DbModel getDatabase() {
        return database;
    }

    public void setDatabase(DbModel database) {
        this.database = database;
    }

    public DbTableCategory getTableCategory() {
        return tableCategory;
    }

    public void setTableCategory(DbTableCategory tableCategory) {
        this.tableCategory = tableCategory;
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

    public List<DbColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<DbColumnModel> columns) {
        this.columns = columns;
    }

    public DbPrimaryKeyModel getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(DbPrimaryKeyModel primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<DbForeignKeyModel> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<DbForeignKeyModel> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbTableModel that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getDatabase(), that.getDatabase())
                                  .append(getTableCategory(), that.getTableCategory())
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
                .append(getTableCategory())
                .append(getName())
                .append(getDescription())
                .append(getColumns())
                .append(getPrimaryKey())
                .append(getForeignKeys())
                .toHashCode();
    }

    public DbTableModel build(DbModel database, String name) {
        DbTableModel tableModel = new DbTableModel();
        tableModel.setDatabase(database);
        tableModel.setName(name);

        tableModel.setTableCategory(null);
        tableModel.setDescription("");
        tableModel.setColumns(new ArrayList<>());
        tableModel.setPrimaryKey(null);
        tableModel.setForeignKeys(new ArrayList<>());

        return tableModel;
    }

}
