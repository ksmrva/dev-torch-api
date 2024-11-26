package io.ksmrva.visual.torch.domain.entity.model.database;

import io.ksmrva.visual.torch.domain.dto.model.database.DbModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.name.DbModelNameDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
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
@Table(name = "db_model", schema = "db_model")
public class DbModel extends AbstractBaseEntity<DbModelDto, DbModel> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_config_id")
    private DbModelSourceConfig sourceConfig;

    @Column(name = "database_name")
    private String databaseName;

    @Column(name = "schema_name")
    private String schemaName;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "database",
               cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DbTableModel> tables;

    @Override
    public DbModelDto convertToDto() {
        DbModelDto dto = super.createDtoWithBaseValues(DbModelDto::new);

        DbModelSourceConfigDto sourceDto = null;
        if (this.getSourceConfig() != null) {
            sourceDto = this.getSourceConfig()
                            .convertToDto();
        }
        dto.setSourceConfig(sourceDto);

        dto.setName(DbModelNameDto.build(this.getDatabaseName(), this.getSchemaName()));
        dto.setDescription(this.getDescription());

        List<DbTableModelDto> tableDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getTables())) {
            for (DbTableModel table : this.getTables()) {
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

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
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

    public List<DbTableModel> getTables() {
        return tables;
    }

    public void setTables(List<DbTableModel> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModel dbModel)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getSourceConfig(), dbModel.getSourceConfig())
                                  .append(getDatabaseName(), dbModel.getDatabaseName())
                                  .append(getSchemaName(), dbModel.getSchemaName())
                                  .append(getDescription(), dbModel.getDescription())
                                  .append(getTables(), dbModel.getTables())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getSourceConfig())
                .append(getDatabaseName())
                .append(getSchemaName())
                .append(getDescription())
                .append(getTables())
                .toHashCode();
    }

    public static DbModel build(DbModelSourceConfig source, String databaseName, String schemaName) {
        DbModel dbModel = new DbModel();
        dbModel.setSourceConfig(source);
        dbModel.setDatabaseName(databaseName);
        dbModel.setSchemaName(schemaName);

        dbModel.setDescription("");
        dbModel.setTables(new ArrayList<>());

        return dbModel;
    }

}
