package io.ksmrva.visual.torch.db.dao.model.database.metadata.sql;

import io.ksmrva.visual.torch.db.dao.model.database.metadata.sql.pojo.SqlDatabaseMetadata;
import io.ksmrva.visual.torch.domain.dto.model.database.source.config.DbModelSourceConfigDto;

public interface SqlDatabaseMetadataDao {

    SqlDatabaseMetadata getSqlDatabaseMetadata(DbModelSourceConfigDto dataSource, String databaseName, String schemaName);

}
