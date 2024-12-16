package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.foreign.SqlForeignKeyDetail;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SqlForeignKeyDetailDto extends AbstractBaseDto<SqlForeignKeyDetailDto, SqlForeignKeyDetail> {

    private String name;

    private String description;

    private SqlColumnDetailDto localColumn;

    private SqlTableDetailDto referencedTable;

    private SqlColumnDetailDto referencedColumn;

    @Override
    public SqlForeignKeyDetail convertToEntity() {
        SqlForeignKeyDetail entity = super.createEntityWithBaseValues(SqlForeignKeyDetail::new);

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

    public SqlColumnDetailDto getLocalColumn() {
        return localColumn;
    }

    public void setLocalColumn(SqlColumnDetailDto localColumn) {
        this.localColumn = localColumn;
    }

    public SqlTableDetailDto getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(SqlTableDetailDto referencedTable) {
        this.referencedTable = referencedTable;
    }

    public SqlColumnDetailDto getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(SqlColumnDetailDto referencedColumn) {
        this.referencedColumn = referencedColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlForeignKeyDetailDto that)) {
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

    public static SqlForeignKeyDetailDto build(String name, SqlColumnDetailDto localColumn, SqlTableDetailDto referencedTable, SqlColumnDetailDto referencedColumn) {
        SqlForeignKeyDetailDto foreignKeyDto = new SqlForeignKeyDetailDto();
        foreignKeyDto.setName(name);
        foreignKeyDto.setLocalColumn(localColumn);
        foreignKeyDto.setReferencedTable(referencedTable);
        foreignKeyDto.setReferencedColumn(referencedColumn);

        foreignKeyDto.setDescription("");

        return foreignKeyDto;
    }

}
