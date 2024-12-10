package io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyComponent;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class PrimaryKeyComponentDto extends AbstractBaseDto<PrimaryKeyComponentDto, PrimaryKeyComponent> {

    private String name;

    private String description;

    private List<PrimaryKeyColumnComponentDto> columns;

    @Override
    public PrimaryKeyComponent convertToEntity() {
        PrimaryKeyComponent entity = super.createEntityWithBaseValues(PrimaryKeyComponent::new);
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());

        List<PrimaryKeyColumnComponent> primaryKeyColumns = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getColumns())) {
            for (PrimaryKeyColumnComponentDto primaryKeyColumnDto : this.getColumns()) {
                primaryKeyColumns.add(primaryKeyColumnDto.convertToEntity());
            }
        }
        entity.setColumns(primaryKeyColumns);

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

    public List<PrimaryKeyColumnComponentDto> getColumns() {
        return columns;
    }

    public void setColumns(List<PrimaryKeyColumnComponentDto> columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PrimaryKeyComponentDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getColumns(), that.getColumns())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getColumns())
                .toHashCode();
    }

    public static PrimaryKeyComponentDto build(String name) {
        PrimaryKeyComponentDto primaryKeyModelDto = new PrimaryKeyComponentDto();
        primaryKeyModelDto.setName(name);
        primaryKeyModelDto.setDescription("");
        primaryKeyModelDto.setColumns(new ArrayList<>());

        return primaryKeyModelDto;
    }

}
