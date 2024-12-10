package io.ksmrva.visual.torch.db.dao.metadata;

import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.db.dao.metadata.pojo.sql.DatabaseSqlMetadata;

public interface DatabaseMetadataDao {

    DatabaseSqlMetadata getDatabaseMetadata(DbModelSourceConfigDto dataSource, String databaseName, String schemaName);

}
