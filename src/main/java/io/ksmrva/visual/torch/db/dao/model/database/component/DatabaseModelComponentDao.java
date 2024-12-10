package io.ksmrva.visual.torch.db.dao.model.database.component;

import io.ksmrva.visual.torch.api.arg.misc.RegexMatcher;
import io.ksmrva.visual.torch.domain.dto.model.database.component.DbComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.path.DatabasePathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;

import java.math.BigInteger;
import java.util.List;

public interface DatabaseModelComponentDao {

    /**
     * Create
     **/
    DbComponentDto createDatabase(BigInteger sourceConfigId, DatabasePathDto databasePathDto, List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers);

    /**
     * Read
     **/
    DbComponentDto getDatabase(DatabasePathDto databasePathDto);

    List<DbComponentDto> getAllDatabases();

    List<TableCategoryDto> getAllTableCategories();

    List<ColumnCategoryDto> getAllColumnCategories();

    /**
     * Update
     **/
    DbComponentDto updateDatabase(DbComponentDto databaseDtoToUpdate);

    TableComponentDto updateTable(TableComponentDto tableDtoToUpdate);

    ColumnComponentDto updateColumn(ColumnComponentDto columnDtoToUpdate);

    /**
     * Delete
     * **/

}
