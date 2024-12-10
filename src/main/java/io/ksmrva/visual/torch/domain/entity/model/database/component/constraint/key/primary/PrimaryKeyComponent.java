package io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.primary.PrimaryKeyColumnComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.primary.PrimaryKeyComponentDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "primary_key", schema = "db_model_component")
public class PrimaryKeyComponent extends AbstractBaseEntity<PrimaryKeyComponentDto, PrimaryKeyComponent> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "table_id")
    private TableComponent table;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PrimaryKeyColumnComponent> columns;

    @Override
    public PrimaryKeyComponentDto convertToDto() {
        PrimaryKeyComponentDto dto = super.createDtoWithBaseValues(PrimaryKeyComponentDto::new);
        dto.setName(this.getName());
        dto.setDescription(this.getDescription());

        List<PrimaryKeyColumnComponentDto> primaryKeyColumnDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getColumns())) {
            for (PrimaryKeyColumnComponent primaryKeyColumn : this.getColumns()) {
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

    public TableComponent getTable() {
        return table;
    }

    public void setTable(TableComponent table) {
        this.table = table;
    }

    public List<PrimaryKeyColumnComponent> getColumns() {
        return columns;
    }

    public void setColumns(List<PrimaryKeyColumnComponent> primaryKeyColumns) {
        this.columns = primaryKeyColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PrimaryKeyComponent that)) {
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

    public static PrimaryKeyComponent build(String name) {
        PrimaryKeyComponent primaryKey = new PrimaryKeyComponent();
        primaryKey.setName(name);
        primaryKey.setDescription("");

        return primaryKey;
    }

}
