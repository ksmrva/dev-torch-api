package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql;

import io.ksmrva.visual.torch.domain.dto.AbstractBaseDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.path.SqlDatabaseDetailPathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.SqlDatabaseDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class SqlDatabaseDetailDto extends AbstractBaseDto<SqlDatabaseDetailDto, SqlDatabaseDetail> {

    private DbModelSourceConfigDto sourceConfig;

    private SqlDatabaseDetailPathDto path;

    private String description;

    private List<SqlTableDetailDto> tables;

    @Override
    public SqlDatabaseDetail convertToEntity() {
        SqlDatabaseDetail entity = super.createEntityWithBaseValues(SqlDatabaseDetail::new);
        entity.setDescription(this.getDescription());

        if (this.getSourceConfig() != null) {
            entity.setSourceConfig(this.getSourceConfig()
                                       .convertToEntity());
        }

        SqlDatabaseDetailPathDto sqlDatabaseDetailPathDto = this.getPath();
        if (sqlDatabaseDetailPathDto != null) {
            entity.setName(sqlDatabaseDetailPathDto.getDatabaseName());
            entity.setSchemaName(sqlDatabaseDetailPathDto.getSchemaName());
        }

        List<SqlTableDetail> tables = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getTables())) {
            for (SqlTableDetailDto table : this.getTables()) {
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

    public SqlDatabaseDetailPathDto getPath() {
        return path;
    }

    public void setPath(SqlDatabaseDetailPathDto path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SqlTableDetailDto> getTables() {
        return tables;
    }

    public void setTables(List<SqlTableDetailDto> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlDatabaseDetailDto that)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getSourceConfig(), that.getSourceConfig())
                                  .append(getPath(), that.getPath())
                                  .append(getDescription(), that.getDescription())
                                  .append(getTables(), that.getTables())
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getSourceConfig())
                .append(getPath())
                .append(getDescription())
                .append(getTables())
                .toHashCode();
    }

    public static SqlDatabaseDetailDto build(DbModelSourceConfigDto source, SqlDatabaseDetailPathDto name) {
        SqlDatabaseDetailDto sqlDatabaseDetailDto = new SqlDatabaseDetailDto();
        sqlDatabaseDetailDto.setSourceConfig(source);
        sqlDatabaseDetailDto.setPath(name);
        sqlDatabaseDetailDto.setDescription("");
        sqlDatabaseDetailDto.setTables(new ArrayList<>());

        return sqlDatabaseDetailDto;
    }

}
