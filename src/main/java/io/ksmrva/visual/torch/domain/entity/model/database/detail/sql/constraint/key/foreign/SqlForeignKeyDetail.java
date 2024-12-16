package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.foreign.SqlForeignKeyDetailDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column.SqlColumnDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "sql_foreign_key", schema = "db_model_detail")
public class SqlForeignKeyDetail extends AbstractBaseEntity<SqlForeignKeyDetailDto, SqlForeignKeyDetail> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    public SqlTableDetail table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_column_id")
    private SqlColumnDetail localColumn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referenced_table_id")
    private SqlTableDetail referencedTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referenced_column_id")
    private SqlColumnDetail referencedColumn;

    @Override
    public SqlForeignKeyDetailDto convertToDto() {
        SqlForeignKeyDetailDto dto = super.createDtoWithBaseValues(SqlForeignKeyDetailDto::new);
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

    public SqlTableDetail getTable() {
        return table;
    }

    public void setTable(SqlTableDetail table) {
        this.table = table;
    }

    public SqlColumnDetail getLocalColumn() {
        return localColumn;
    }

    public void setLocalColumn(SqlColumnDetail localColumn) {
        this.localColumn = localColumn;
    }

    public SqlTableDetail getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(SqlTableDetail referencedTable) {
        this.referencedTable = referencedTable;
    }

    public SqlColumnDetail getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(SqlColumnDetail referencedColumn) {
        this.referencedColumn = referencedColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlForeignKeyDetail that)) {
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

    public static SqlForeignKeyDetail build(String name) {
        SqlForeignKeyDetail foreignKey = new SqlForeignKeyDetail();
        foreignKey.setName(name);
        foreignKey.setDescription("");

        return foreignKey;
    }

}
