package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.SqlDatabaseDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.path.SqlDatabaseDetailPathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.source.config.DbModelSourceConfig;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sql_database", schema = "db_model_detail")
public class SqlDatabaseDetail extends AbstractBaseEntity<SqlDatabaseDetailDto, SqlDatabaseDetail> {

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
    private List<SqlTableDetail> tables;

    @Override
    public SqlDatabaseDetailDto convertToDto() {
        SqlDatabaseDetailDto dto = super.createDtoWithBaseValues(SqlDatabaseDetailDto::new);
        dto.setPath(SqlDatabaseDetailPathDto.build(this.getName(), this.getSchemaName()));
        dto.setDescription(this.getDescription());

        DbModelSourceConfigDto sourceDto = null;
        if (this.getSourceConfig() != null) {
            sourceDto = this.getSourceConfig()
                            .convertToDto();
        }
        dto.setSourceConfig(sourceDto);

        List<SqlTableDetailDto> tableDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.getTables())) {
            for (SqlTableDetail table : this.getTables()) {
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

    public List<SqlTableDetail> getTables() {
        return tables;
    }

    public void setTables(List<SqlTableDetail> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlDatabaseDetail sqlDatabaseDetail)) {
            return false;
        }

        return new EqualsBuilder().appendSuper(super.equals(o))
                                  .append(getSourceConfig(), sqlDatabaseDetail.getSourceConfig())
                                  .append(getName(), sqlDatabaseDetail.getName())
                                  .append(getSchemaName(), sqlDatabaseDetail.getSchemaName())
                                  .append(getDescription(), sqlDatabaseDetail.getDescription())
                                  .append(getTables(), sqlDatabaseDetail.getTables())
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

    public static SqlDatabaseDetail build(DbModelSourceConfig source, String databaseName, String schemaName) {
        SqlDatabaseDetail sqlDatabaseDetail = new SqlDatabaseDetail();
        sqlDatabaseDetail.setSourceConfig(source);
        sqlDatabaseDetail.setName(databaseName);
        sqlDatabaseDetail.setSchemaName(schemaName);

        sqlDatabaseDetail.setDescription("");
        sqlDatabaseDetail.setTables(new ArrayList<>());

        return sqlDatabaseDetail;
    }

}
