package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary.DbPrimaryKeyColumnModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary.DbPrimaryKeyModelDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "db_primary_key_model", schema = "model")
public class DbPrimaryKeyModel extends AbstractBaseEntity<DbPrimaryKeyModelDto, DbPrimaryKeyModel> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private DbTableModel table;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DbPrimaryKeyColumnModel> primaryKeyColumns;

    @Override
    public DbPrimaryKeyModelDto convertToDto() {
        DbPrimaryKeyModelDto dto = super.createDtoWithBaseValues(DbPrimaryKeyModelDto::new);
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        List<DbPrimaryKeyColumnModelDto> primaryKeyColumnDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getPrimaryKeyColumns())) {
            for (DbPrimaryKeyColumnModel primaryKeyColumn : this.getPrimaryKeyColumns()) {
                primaryKeyColumnDtos.add(primaryKeyColumn.convertToDto());
            }
        }
        dto.setPrimaryKeyModelColumns(primaryKeyColumnDtos);

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

    public DbTableModel getTableModel() {
        return table;
    }

    public void setTableModel(DbTableModel dbTableModel) {
        this.table = dbTableModel;
    }

    public List<DbPrimaryKeyColumnModel> getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }

    public void setPrimaryKeyColumns(List<DbPrimaryKeyColumnModel> primaryKeyColumns) {
        this.primaryKeyColumns = primaryKeyColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbPrimaryKeyModel that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(table, that.table)
                                  .append(getPrimaryKeyColumns(), that.getPrimaryKeyColumns())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(table)
                .append(getPrimaryKeyColumns())
                .toHashCode();
    }

    public static DbPrimaryKeyModel build(String name) {
        DbPrimaryKeyModel primaryKey = new DbPrimaryKeyModel();
        primaryKey.setName(name);

        primaryKey.setDescription("");

        return primaryKey;
    }

}
