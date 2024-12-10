package io.ksmrva.visual.torch.domain.entity.model.database.component.column;

import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.source.data.DbModelSourceDataType;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "column", schema = "db_model_component")
public class ColumnComponent extends AbstractBaseEntity<ColumnComponentDto, ColumnComponent> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private TableComponent table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ColumnCategory category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_type_id")
    private DbModelSourceDataType dbModelSourceDataType;

    @Column(name = "is_nullable")
    private boolean isNullable;

    @Column(name = "is_auto_increment")
    private boolean isAutoIncrement;

    @Column(name = "column_index")
    private int columnIndex;

    @Override
    public ColumnComponentDto convertToDto() {
        ColumnComponentDto dto = super.createDtoWithBaseValues(ColumnComponentDto::new);
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());
        dto.setAutoIncrement(this.isAutoIncrement());
        dto.setNullable(this.isNullable());
        dto.setColumnIndex(this.getColumnIndex());

        if (this.getDataType() != null) {
            dto.setDataType(this.getDataType()
                                .convertToDto());
        }

        if (this.getCategory() != null) {
            dto.setCategory(this.getCategory()
                                .convertToDto());
        }

        return dto;
    }

    public TableComponent getTable() {
        return table;
    }

    public void setTable(TableComponent table) {
        this.table = table;
    }

    public ColumnCategory getCategory() {
        return category;
    }

    public void setCategory(ColumnCategory category) {
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

    public DbModelSourceDataType getDataType() {
        return dbModelSourceDataType;
    }

    public void setDataType(DbModelSourceDataType dbModelSourceDataType) {
        this.dbModelSourceDataType = dbModelSourceDataType;
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

        if (!(o instanceof ColumnComponent that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(isNullable(), that.isNullable())
                                  .append(isAutoIncrement(), that.isAutoIncrement())
                                  .append(getColumnIndex(), that.getColumnIndex())
                                  .append(getTable(), that.getTable())
                                  .append(getCategory(), that.getCategory())
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
                .append(getCategory())
                .append(getName())
                .append(getDescription())
                .append(getDataType())
                .append(isNullable())
                .append(isAutoIncrement())
                .append(getColumnIndex())
                .toHashCode();
    }

    public static ColumnComponent build(String name, String description, DbModelSourceDataType dbModelSourceDataType, ColumnCategory columnCategory, boolean isNullable, boolean isAutoIncrement, int columnIndex) {
        ColumnComponent column = new ColumnComponent();
        column.setName(name);
        column.setDescription(description);
        column.setDataType(dbModelSourceDataType);
        column.setCategory(columnCategory);
        column.setNullable(isNullable);
        column.setAutoIncrement(isAutoIncrement);
        column.setColumnIndex(columnIndex);

        return column;
    }

}
