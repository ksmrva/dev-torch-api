package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.foreign.SqlForeignKeyDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.category.collection.CollectionCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.SqlDatabaseDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column.SqlColumnDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.foreign.SqlForeignKeyDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyDetail;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "sql_table", schema = "db_model_detail")
public class SqlTableDetail extends AbstractBaseEntity<SqlTableDetailDto, SqlTableDetail> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "database_id")
    private SqlDatabaseDetail database;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_category_id")
    private CollectionCategory category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<SqlColumnDetail> columns;

    @OneToOne(mappedBy = "table",
              cascade = {CascadeType.ALL}, orphanRemoval = true)
    private SqlPrimaryKeyDetail primaryKey;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<SqlForeignKeyDetail> foreignKeys;

    @Override
    public SqlTableDetailDto convertToDto() {
        SqlTableDetailDto dto = super.createDtoWithBaseValues(SqlTableDetailDto::new);
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        if (this.getPrimaryKey() != null) {
            dto.setPrimaryKey(this.getPrimaryKey()
                                  .convertToDto());
        }

        List<SqlColumnDetailDto> columnDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getColumns())) {
            for (SqlColumnDetail column : this.getColumns()) {
                columnDtos.add(column.convertToDto());
            }
        }
        dto.setColumns(columnDtos);

        List<SqlForeignKeyDetailDto> foreignKeyDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getForeignKeys())) {
            for (SqlForeignKeyDetail foreignKey : this.getForeignKeys()) {
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

    public SqlDatabaseDetail getDatabase() {
        return database;
    }

    public void setDatabase(SqlDatabaseDetail database) {
        this.database = database;
    }

    public CollectionCategory getCategory() {
        return category;
    }

    public void setCategory(CollectionCategory category) {
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

    public List<SqlColumnDetail> getColumns() {
        return columns;
    }

    public void setColumns(List<SqlColumnDetail> columns) {
        this.columns = columns;
    }

    public SqlPrimaryKeyDetail getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(SqlPrimaryKeyDetail primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<SqlForeignKeyDetail> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<SqlForeignKeyDetail> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlTableDetail that)) {
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

    public SqlTableDetail build(SqlDatabaseDetail database, String name) {
        SqlTableDetail tableModel = new SqlTableDetail();
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
