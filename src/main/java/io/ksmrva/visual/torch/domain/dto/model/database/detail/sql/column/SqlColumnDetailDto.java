package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column.SqlColumnDetail;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SqlColumnDetailDto extends AbstractBaseDto<SqlColumnDetailDto, SqlColumnDetail> {

    private String name;

    private String description;

    private DbModelSourceDataTypeDto dataType;

    private FieldCategoryDto category;

    public boolean isNullable;

    public boolean isAutoIncrement;

    public int columnIndex;

    @Override
    public SqlColumnDetail convertToEntity() {
        SqlColumnDetail entity = super.createEntityWithBaseValues(SqlColumnDetail::new);

        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        if (this.getDataType() != null) {
            entity.setDataType(this.getDataType()
                                   .convertToEntity());
        }

        if (this.getCategory() != null) {
            entity.setCategory(this.getCategory()
                                   .convertToEntity());
        }

        entity.setAutoIncrement(this.isAutoIncrement());
        entity.setNullable(this.isNullable());
        entity.setColumnIndex(this.getColumnIndex());

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

    public DbModelSourceDataTypeDto getDataType() {
        return dataType;
    }

    public void setDataType(DbModelSourceDataTypeDto dataType) {
        this.dataType = dataType;
    }

    public FieldCategoryDto getCategory() {
        return category;
    }

    public void setCategory(FieldCategoryDto category) {
        this.category = category;
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

        if (!(o instanceof SqlColumnDetailDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(isNullable(), that.isNullable())
                                  .append(isAutoIncrement(), that.isAutoIncrement())
                                  .append(getColumnIndex(), that.getColumnIndex())
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getDataType(), that.getDataType())
                                  .append(getCategory(), that.getCategory())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getDataType())
                .append(getCategory())
                .append(isNullable())
                .append(isAutoIncrement())
                .append(getColumnIndex())
                .toHashCode();
    }

    public static SqlColumnDetailDto build(String name) {
        SqlColumnDetailDto columnModelDto = new SqlColumnDetailDto();
        columnModelDto.setName(name);

        columnModelDto.setDescription("");
        columnModelDto.setDataType(null);
        columnModelDto.setCategory(null);
        columnModelDto.setNullable(false);
        columnModelDto.setAutoIncrement(false);
        columnModelDto.setColumnIndex(0);

        return columnModelDto;
    }

}
