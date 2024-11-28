package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.foreign.DbForeignKeyModelDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnModel;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "foreign_key_model", schema = "db_model")
public class DbForeignKeyModel extends AbstractBaseEntity<DbForeignKeyModelDto, DbForeignKeyModel> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    public DbTableModel table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_column_id")
    private DbColumnModel localColumn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referenced_table_id")
    private DbTableModel referencedTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referenced_column_id")
    private DbColumnModel referencedColumn;

    @Override
    public DbForeignKeyModelDto convertToDto() {
        DbForeignKeyModelDto dto = super.createDtoWithBaseValues(DbForeignKeyModelDto::new);
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

    public DbTableModel getTable() {
        return table;
    }

    public void setTable(DbTableModel table) {
        this.table = table;
    }

    public DbColumnModel getLocalColumn() {
        return localColumn;
    }

    public void setLocalColumn(DbColumnModel localColumn) {
        this.localColumn = localColumn;
    }

    public DbTableModel getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(DbTableModel referencedTable) {
        this.referencedTable = referencedTable;
    }

    public DbColumnModel getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(DbColumnModel referencedColumn) {
        this.referencedColumn = referencedColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbForeignKeyModel that)) {
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

    public static DbForeignKeyModel build(String name) {
        DbForeignKeyModel foreignKey = new DbForeignKeyModel();
        foreignKey.setName(name);

        foreignKey.setDescription("");

        return foreignKey;
    }

}
