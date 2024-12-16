package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.category.field.FieldCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.source.data.DbModelSourceDataType;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "sql_column", schema = "db_model_detail")
public class SqlColumnDetail extends AbstractBaseEntity<SqlColumnDetailDto, SqlColumnDetail> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private SqlTableDetail table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_category_id")
    private FieldCategory category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_type_id")
    private DbModelSourceDataType dataType;

    @Column(name = "is_nullable")
    private boolean isNullable;

    @Column(name = "is_auto_increment")
    private boolean isAutoIncrement;

    @Column(name = "column_index")
    private int columnIndex;

    @Override
    public SqlColumnDetailDto convertToDto() {
        SqlColumnDetailDto dto = super.createDtoWithBaseValues(SqlColumnDetailDto::new);
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

    public SqlTableDetail getTable() {
        return table;
    }

    public void setTable(SqlTableDetail table) {
        this.table = table;
    }

    public FieldCategory getCategory() {
        return category;
    }

    public void setCategory(FieldCategory category) {
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
        return dataType;
    }

    public void setDataType(DbModelSourceDataType dataType) {
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

        if (!(o instanceof SqlColumnDetail that)) {
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

    public static SqlColumnDetail build(String name, String description, DbModelSourceDataType dbModelSourceDataType, FieldCategory fieldCategory, boolean isNullable, boolean isAutoIncrement, int columnIndex) {
        SqlColumnDetail column = new SqlColumnDetail();
        column.setName(name);
        column.setDescription(description);
        column.setDataType(dbModelSourceDataType);
        column.setCategory(fieldCategory);
        column.setNullable(isNullable);
        column.setAutoIncrement(isAutoIncrement);
        column.setColumnIndex(columnIndex);

        return column;
    }

}
