package io.ksmrva.visual.torch.domain.dto.model.database.component;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.path.DatabasePathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.entity.model.database.component.DatabaseComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class DbComponentDto extends AbstractBaseDto<DbComponentDto, DatabaseComponent> {

    private DbModelSourceConfigDto sourceConfig;

    private DatabasePathDto name;

    private String description;

    private List<TableComponentDto> tables;

    @Override
    public DatabaseComponent convertToEntity() {
        DatabaseComponent entity = super.createEntityWithBaseValues(DatabaseComponent::new);
        entity.setDescription(this.getDescription());

        if (this.getSourceConfig() != null) {
            entity.setSourceConfig(this.getSourceConfig()
                                       .convertToEntity());
        }

        DatabasePathDto databasePathDto = this.getName();
        if (databasePathDto != null) {
            entity.setName(databasePathDto.getDatabaseName());
            entity.setSchemaName(databasePathDto.getSchemaName());
        }

        List<TableComponent> tables = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getTables())) {
            for (TableComponentDto table : this.getTables()) {
                tables.add(table.convertToEntity());
            }
        }
        entity.setTables(tables);

        return entity;
    }

    public DbModelSourceConfigDto getSourceConfig() {
        return sourceConfig;
    }

    public void setSourceConfig(DbModelSourceConfigDto sourceConfig) {
        this.sourceConfig = sourceConfig;
    }

    public DatabasePathDto getName() {
        return name;
    }

    public void setName(DatabasePathDto name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TableComponentDto> getTables() {
        return tables;
    }

    public void setTables(List<TableComponentDto> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbComponentDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getSourceConfig(), that.getSourceConfig())
                                  .append(getName(), that.getName())
                                  .append(getDescription(), that.getDescription())
                                  .append(getTables(), that.getTables())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getSourceConfig())
                .append(getName())
                .append(getDescription())
                .append(getTables())
                .toHashCode();
    }

    public static DbComponentDto build(DbModelSourceConfigDto source, DatabasePathDto name) {
        DbComponentDto dbComponentDto = new DbComponentDto();
        dbComponentDto.setSourceConfig(source);
        dbComponentDto.setName(name);
        dbComponentDto.setDescription("");
        dbComponentDto.setTables(new ArrayList<>());

        return dbComponentDto;
    }

}
