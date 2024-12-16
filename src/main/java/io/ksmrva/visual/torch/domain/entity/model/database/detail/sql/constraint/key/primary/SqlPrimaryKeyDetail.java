package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyColumnDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyDetailDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "sql_primary_key", schema = "db_model_detail")
public class SqlPrimaryKeyDetail extends AbstractBaseEntity<SqlPrimaryKeyDetailDto, SqlPrimaryKeyDetail> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "table_id")
    private SqlTableDetail table;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<SqlPrimaryKeyColumnDetail> columns;

    @Override
    public SqlPrimaryKeyDetailDto convertToDto() {
        SqlPrimaryKeyDetailDto dto = super.createDtoWithBaseValues(SqlPrimaryKeyDetailDto::new);
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        List<SqlPrimaryKeyColumnDetailDto> primaryKeyColumnDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getColumns())) {
            for (SqlPrimaryKeyColumnDetail primaryKeyColumn : this.getColumns()) {
                primaryKeyColumnDtos.add(primaryKeyColumn.convertToDto());
            }
        }
        dto.setColumns(primaryKeyColumnDtos);

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

    public List<SqlPrimaryKeyColumnDetail> getColumns() {
        return columns;
    }

    public void setColumns(List<SqlPrimaryKeyColumnDetail> primaryKeyColumns) {
        this.columns = primaryKeyColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlPrimaryKeyDetail that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getTable(), that.getTable())
                                  .append(getColumns(), that.getColumns())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getTable())
                .append(getColumns())
                .toHashCode();
    }

    public static SqlPrimaryKeyDetail build(String name) {
        SqlPrimaryKeyDetail primaryKey = new SqlPrimaryKeyDetail();
        primaryKey.setName(name);
        primaryKey.setDescription("");

        return primaryKey;
    }

}
