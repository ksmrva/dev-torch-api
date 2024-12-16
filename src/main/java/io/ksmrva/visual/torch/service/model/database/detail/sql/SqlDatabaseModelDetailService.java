package io.ksmrva.visual.torch.service.model.database.detail.sql;

import io.ksmrva.visual.torch.api.arg.misc.RegexMatcher;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.SqlDatabaseDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.path.SqlDatabaseDetailPathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;

import java.math.BigInteger;
import java.util.List;

public interface SqlDatabaseModelDetailService {

    SqlDatabaseDetailDto createDatabase(BigInteger sourceConfigId, SqlDatabaseDetailPathDto sqlDatabaseDetailPathDto, List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers);

    SqlDatabaseDetailDto getDatabase(SqlDatabaseDetailPathDto sqlDatabaseDetailPathDto);

    List<SqlDatabaseDetailDto> getAllDatabases();

    List<SqlDatabaseDetailPathDto> getAllDatabasePaths();

    SqlDatabaseDetailDto updateDatabase(SqlDatabaseDetailDto databaseToUpdate);

    SqlTableDetailDto updateTable(SqlTableDetailDto tableToUpdate);

    SqlColumnDetailDto updateColumn(SqlColumnDetailDto columnToUpdate);

}
