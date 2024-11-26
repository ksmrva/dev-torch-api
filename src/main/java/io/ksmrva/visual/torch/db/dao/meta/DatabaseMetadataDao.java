package io.ksmrva.visual.torch.db.dao.meta;

import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;
import io.ksmrva.visual.torch.db.dao.meta.helper.sql.DatabaseSqlMetadata;

public interface DatabaseMetadataDao {

    DatabaseSqlMetadata getDatabaseMetadata(DbModelSourceConfigDto dataSource, String databaseName, String schemaName);

}
