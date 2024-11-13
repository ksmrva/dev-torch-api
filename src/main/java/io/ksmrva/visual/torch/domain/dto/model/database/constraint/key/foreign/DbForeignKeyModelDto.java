package io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.foreign.DbForeignKeyModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DbForeignKeyModelDto extends AbstractBaseDto<DbForeignKeyModelDto, DbForeignKeyModel> {

    private String name;

    private String description;

    private DbColumnModelDto localColumn;

    private DbTableModelDto referencedTable;

    private DbColumnModelDto referencedColumn;

    @Override
    public DbForeignKeyModel convertToEntity() {
        DbForeignKeyModel entity = super.createEntityWithBaseValues(DbForeignKeyModel::new);

        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        if (this.getLocalColumn() != null) {
            entity.setLocalColumn(this.getLocalColumn()
                                      .convertToEntity());
        }

        if (this.getReferencedTable() != null) {
            entity.setReferencedTable(this.getReferencedTable()
                                          .convertToEntity());
        }

        if (this.getReferencedColumn() != null) {
            entity.setReferencedColumn(this.getReferencedColumn()
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

    public DbColumnModelDto getLocalColumn() {
        return localColumn;
    }

    public void setLocalColumn(DbColumnModelDto localColumn) {
        this.localColumn = localColumn;
    }

    public DbTableModelDto getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(DbTableModelDto referencedTable) {
        this.referencedTable = referencedTable;
    }

    public DbColumnModelDto getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(DbColumnModelDto referencedColumn) {
        this.referencedColumn = referencedColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbForeignKeyModelDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getLocalColumn(), that.getLocalColumn())
                                  .append(getReferencedTable(), that.getReferencedTable())
                                  .append(getReferencedColumn(), that.getReferencedColumn())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getLocalColumn())
                .append(getReferencedTable())
                .append(getReferencedColumn())
                .toHashCode();
    }

    public static DbForeignKeyModelDto build(String name, DbColumnModelDto localColumn, DbTableModelDto referencedTable, DbColumnModelDto referencedColumn) {
        DbForeignKeyModelDto foreignKeyDto = new DbForeignKeyModelDto();
        foreignKeyDto.setName(name);
        foreignKeyDto.setLocalColumn(localColumn);
        foreignKeyDto.setReferencedTable(referencedTable);
        foreignKeyDto.setReferencedColumn(referencedColumn);

        foreignKeyDto.setDescription("");

        return foreignKeyDto;
    }

}
