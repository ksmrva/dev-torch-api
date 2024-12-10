package io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.foreign.ForeignKeyComponentDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "foreign_key", schema = "db_model_component")
public class ForeignKeyComponent extends AbstractBaseEntity<ForeignKeyComponentDto, ForeignKeyComponent> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    public TableComponent table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_column_id")
    private ColumnComponent localColumn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referenced_table_id")
    private TableComponent referencedTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referenced_column_id")
    private ColumnComponent referencedColumn;

    @Override
    public ForeignKeyComponentDto convertToDto() {
        ForeignKeyComponentDto dto = super.createDtoWithBaseValues(ForeignKeyComponentDto::new);
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        if (this.getLocalColumn() != null) {
            dto.setLocalColumn(this.getLocalColumn()
                                   .convertToDto());
        }

        if (this.getReferencedTable() != null) {
            dto.setReferencedTable(this.getReferencedTable()
                                       .convertToDto());
        }

        if (this.getReferencedColumn() != null) {
            dto.setReferencedColumn(this.getReferencedColumn()
                                        .convertToDto());
        }

        return dto;
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

    public TableComponent getTable() {
        return table;
    }

    public void setTable(TableComponent table) {
        this.table = table;
    }

    public ColumnComponent getLocalColumn() {
        return localColumn;
    }

    public void setLocalColumn(ColumnComponent localColumn) {
        this.localColumn = localColumn;
    }

    public TableComponent getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(TableComponent referencedTable) {
        this.referencedTable = referencedTable;
    }

    public ColumnComponent getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(ColumnComponent referencedColumn) {
        this.referencedColumn = referencedColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ForeignKeyComponent that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getTable(), that.getTable())
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
                .append(getTable())
                .append(getLocalColumn())
                .append(getReferencedTable())
                .append(getReferencedColumn())
                .toHashCode();
    }

    public static ForeignKeyComponent build(String name) {
        ForeignKeyComponent foreignKey = new ForeignKeyComponent();
        foreignKey.setName(name);
        foreignKey.setDescription("");

        return foreignKey;
    }

}
