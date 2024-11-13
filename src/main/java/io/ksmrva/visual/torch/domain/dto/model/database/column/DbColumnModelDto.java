package io.ksmrva.visual.torch.domain.dto.model.database.column;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.data.DbDataTypeDto;
import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbColumnModelDto extends AbstractBaseDto<DbColumnModelDto, DbColumnModel> {

    private String name;

    private String description;

    private DbDataTypeDto dataType;

    private DbColumnCategoryDto columnCategory;

    public boolean isNullable;

    public boolean isAutoIncrement;

    public int columnIndex;

    @Override
    public DbColumnModel convertToEntity() {
        DbColumnModel entity = super.createEntityWithBaseValues(DbColumnModel::new);

        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        if (this.getDataType() != null) {
            entity.setDataType(this.getDataType()
                                   .convertToEntity());
        }

        if (this.getColumnCategory() != null) {
            entity.setColumnCategory(this.getColumnCategory()
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

    public DbDataTypeDto getDataType() {
        return dataType;
    }

    public void setDataType(DbDataTypeDto dataType) {
        this.dataType = dataType;
    }

    public DbColumnCategoryDto getColumnCategory() {
        return columnCategory;
    }

    public void setColumnCategory(DbColumnCategoryDto columnCategory) {
        this.columnCategory = columnCategory;
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

        if (!(o instanceof DbColumnModelDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(isNullable(), that.isNullable())
                                  .append(isAutoIncrement(), that.isAutoIncrement())
                                  .append(getColumnIndex(), that.getColumnIndex())
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getDataType(), that.getDataType())
                                  .append(getColumnCategory(), that.getColumnCategory())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getDataType())
                .append(getColumnCategory())
                .append(isNullable())
                .append(isAutoIncrement())
                .append(getColumnIndex())
                .toHashCode();
    }

    public static DbColumnModelDto build(String name) {
        DbColumnModelDto columnModelDto = new DbColumnModelDto();
        columnModelDto.setName(name);

        columnModelDto.setDescription("");
        columnModelDto.setDataType(null);
        columnModelDto.setColumnCategory(null);
        columnModelDto.setNullable(false);
        columnModelDto.setAutoIncrement(false);
        columnModelDto.setColumnIndex(0);

        return columnModelDto;
    }

}
