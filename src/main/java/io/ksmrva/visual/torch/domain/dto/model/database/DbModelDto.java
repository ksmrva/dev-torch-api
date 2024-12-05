package io.ksmrva.visual.torch.domain.dto.model.database;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.name.DbModelNameDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import io.ksmrva.visual.torch.domain.entity.model.database.DbModel;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class DbModelDto extends AbstractBaseDto<DbModelDto, DbModel> {

    private DbModelSourceConfigDto sourceConfig;

    private DbModelNameDto name;

    private String description;

    private List<DbTableModelDto> tables;

    @Override
    public DbModel convertToEntity() {
        DbModel entity = super.createEntityWithBaseValues(DbModel::new);

        if (this.getSourceConfig() != null) {
            entity.setSourceConfig(this.getSourceConfig()
                                       .convertToEntity());
        }

        DbModelNameDto dbModelNameDto = this.getName();
        if (dbModelNameDto != null) {
            entity.setDatabaseName(dbModelNameDto.getDatabaseName());
            entity.setSchemaName(dbModelNameDto.getSchemaName());
        }

        List<DbTableModel> tables = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getTables())) {
            for (DbTableModelDto table : this.getTables()) {
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

    public DbModelNameDto getName() {
        return name;
    }

    public void setName(DbModelNameDto name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DbTableModelDto> getTables() {
        return tables;
    }

    public void setTables(List<DbTableModelDto> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DbModelDto that)) {
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

    public static DbModelDto build(DbModelSourceConfigDto source, DbModelNameDto name) {
        DbModelDto dbModelDto = new DbModelDto();
        dbModelDto.setSourceConfig(source);
        dbModelDto.setName(name);

        dbModelDto.setDescription("");
        dbModelDto.setTables(new ArrayList<>());

        return dbModelDto;
    }

}
