package io.ksmrva.visual.torch.db.dao.model.database;

import io.ksmrva.visual.torch.api.args.misc.RegexMatcher;
import io.ksmrva.visual.torch.domain.dto.model.database.DbModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.data.DbDataTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.database.name.DbModelNameDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;

import java.math.BigInteger;
import java.util.List;

public interface DatabaseModelDao {

    /**
     * Create
     **/
    DbModelDto createDbModel(BigInteger sourceConfigId, DbModelNameDto dbModelNameDto, List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers);

    List<DbDataTypeDto> createAllDataTypes();

    /**
     * Read
     **/
    DbModelDto getDbModel(DbModelNameDto dbModelNameDto);

    List<DbModelDto> getAllDbModels();

    List<DbDataTypeDto> getOrCreateAllDataTypes();

    List<DbDataTypeDto> getAllDataTypes();

    List<DbTableCategoryDto> getAllTableCategories();

    List<DbColumnCategoryDto> getAllColumnCategories();

    /**
     * Update
     **/
    DbModelDto updateDbModel(DbModelDto dbModelDtoToUpdate);

    DbTableModelDto updateTableModel(DbTableModelDto tableDtoToUpdate);

    DbColumnModelDto updateColumnModel(DbColumnModelDto columnDtoToUpdate);

    /**
     * Delete
     * **/

}
