package io.ksmrva.visual.torch.domain.entity.model.database.column;

import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.data.DbDataType;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "db_column_model", schema = "model")
public class DbColumnModel extends AbstractBaseEntity<DbColumnModelDto, DbColumnModel> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private DbTableModel table;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_category_id")
    private DbColumnCategory columnCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_type_id")
    private DbDataType dataType;

    @Column(name = "is_nullable")
    private boolean isNullable;

    @Column(name = "is_auto_increment")
    private boolean isAutoIncrement;

    @Column(name = "column_index")
    private int columnIndex;

    @Override
    public DbColumnModelDto convertToDto() {
        DbColumnModelDto dto = super.createDtoWithBaseValues(DbColumnModelDto::new);
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        if (this.getDataType() != null) {
            dto.setDataType(this.getDataType()
                                .convertToDto());
        }

        if (this.getColumnCategory() != null) {
            dto.setColumnCategory(this.getColumnCategory()
                                      .convertToDto());
        }

        dto.setAutoIncrement(this.isAutoIncrement());
        dto.setNullable(this.isNullable());
        dto.setColumnIndex(this.getColumnIndex());

        return dto;
    }

    public DbTableModel getTable() {
        return table;
    }

    public void setTable(DbTableModel table) {
        this.table = table;
    }

    public DbColumnCategory getColumnCategory() {
        return columnCategory;
    }

    public void setColumnCategory(DbColumnCategory columnCategory) {
        this.columnCategory = columnCategory;
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

    public DbDataType getDataType() {
        return dataType;
    }

    public void setDataType(DbDataType dataType) {
        this.dataType = dataType;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbColumnModel that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(isNullable(), that.isNullable())
                                  .append(isAutoIncrement(), that.isAutoIncrement())
                                  .append(getColumnIndex(), that.getColumnIndex())
                                  .append(getTable(), that.getTable())
                                  .append(getColumnCategory(), that.getColumnCategory())
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getDataType(), that.getDataType())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getTable())
                .append(getColumnCategory())
                .append(getName())
                .append(getDescription())
                .append(getDataType())
                .append(isNullable())
                .append(isAutoIncrement())
                .append(getColumnIndex())
                .toHashCode();
    }

    public static DbColumnModel build(String name, String description, DbDataType dataType, DbColumnCategory columnCategory, boolean isNullable, boolean isAutoIncrement, int columnIndex) {
        DbColumnModel column = new DbColumnModel();
        column.setName(name);
        column.setDescription(description);
        column.setDataType(dataType);
        column.setColumnCategory(columnCategory);
        column.setNullable(isNullable);
        column.setAutoIncrement(isAutoIncrement);
        column.setColumnIndex(columnIndex);

        return column;
    }

}
