package io.ksmrva.visual.torch.domain.entity.model.database.component;

import io.ksmrva.visual.torch.domain.dto.model.database.component.DbComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.path.DatabasePathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "database", schema = "db_model_component")
public class DatabaseComponent extends AbstractBaseEntity<DbComponentDto, DatabaseComponent> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_config_id")
    private DbModelSourceConfig sourceConfig;

    @Column(name = "name")
    private String name;

    @Column(name = "schema_name")
    private String schemaName;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "database",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<TableComponent> tables;

    @Override
    public DbComponentDto convertToDto() {
        DbComponentDto dto = super.createDtoWithBaseValues(DbComponentDto::new);
        dto.setName(DatabasePathDto.build(this.getName(), this.getSchemaName()));
        dto.setDescription(this.getDescription());

        DbModelSourceConfigDto sourceDto = null;
        if (this.getSourceConfig() != null) {
            sourceDto = this.getSourceConfig()
                            .convertToDto();
        }
        dto.setSourceConfig(sourceDto);

        List<TableComponentDto> tableDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getTables())) {
            for (TableComponent table : this.getTables()) {
                tableDtos.add(table.convertToDto());
            }
        }
        dto.setTables(tableDtos);

        return dto;
    }

    public DbModelSourceConfig getSourceConfig() {
        return sourceConfig;
    }

    public void setSourceConfig(DbModelSourceConfig sourceConfig) {
        this.sourceConfig = sourceConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String databaseName) {
        this.name = databaseName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TableComponent> getTables() {
        return tables;
    }

    public void setTables(List<TableComponent> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DatabaseComponent databaseComponent)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getSourceConfig(), databaseComponent.getSourceConfig())
                                  .append(getName(), databaseComponent.getName())
                                  .append(getSchemaName(), databaseComponent.getSchemaName())
                                  .append(getDescription(), databaseComponent.getDescription())
                                  .append(getTables(), databaseComponent.getTables())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getSourceConfig())
                .append(getName())
                .append(getSchemaName())
                .append(getDescription())
                .append(getTables())
                .toHashCode();
    }

    public static DatabaseComponent build(DbModelSourceConfig source, String databaseName, String schemaName) {
        DatabaseComponent databaseComponent = new DatabaseComponent();
        databaseComponent.setSourceConfig(source);
        databaseComponent.setName(databaseName);
        databaseComponent.setSchemaName(schemaName);

        databaseComponent.setDescription("");
        databaseComponent.setTables(new ArrayList<>());

        return databaseComponent;
    }

}
