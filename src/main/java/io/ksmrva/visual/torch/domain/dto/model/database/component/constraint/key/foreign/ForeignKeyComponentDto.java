package io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.foreign.ForeignKeyComponent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ForeignKeyComponentDto extends AbstractBaseDto<ForeignKeyComponentDto, ForeignKeyComponent> {

    private String name;

    private String description;

    private ColumnComponentDto localColumn;

    private TableComponentDto referencedTable;

    private ColumnComponentDto referencedColumn;

    @Override
    public ForeignKeyComponent convertToEntity() {
        ForeignKeyComponent entity = super.createEntityWithBaseValues(ForeignKeyComponent::new);

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

    public ColumnComponentDto getLocalColumn() {
        return localColumn;
    }

    public void setLocalColumn(ColumnComponentDto localColumn) {
        this.localColumn = localColumn;
    }

    public TableComponentDto getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(TableComponentDto referencedTable) {
        this.referencedTable = referencedTable;
    }

    public ColumnComponentDto getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(ColumnComponentDto referencedColumn) {
        this.referencedColumn = referencedColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ForeignKeyComponentDto that)) {
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

    public static ForeignKeyComponentDto build(String name, ColumnComponentDto localColumn, TableComponentDto referencedTable, ColumnComponentDto referencedColumn) {
        ForeignKeyComponentDto foreignKeyDto = new ForeignKeyComponentDto();
        foreignKeyDto.setName(name);
        foreignKeyDto.setLocalColumn(localColumn);
        foreignKeyDto.setReferencedTable(referencedTable);
        foreignKeyDto.setReferencedColumn(referencedColumn);

        foreignKeyDto.setDescription("");

        return foreignKeyDto;
    }

}
