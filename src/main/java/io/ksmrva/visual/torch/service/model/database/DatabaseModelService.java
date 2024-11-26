package io.ksmrva.visual.torch.service.model.database;

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

public interface DatabaseModelService {

    DbModelDto createDbModel(BigInteger sourceConfigId, DbModelNameDto dbModelName, List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers);

    DbModelDto getDbModel(DbModelNameDto dbModelName);

    List<DbModelDto> getAllDbModels();

    List<DbDataTypeDto> getOrCreateAllDataTypes();

    List<DbTableCategoryDto> getAllTableCategories();

    List<DbColumnCategoryDto> getAllColumnCategories();

    List<DbModelNameDto> getAllDbModelNames();

    DbModelDto updateDbModel(DbModelDto databaseModelToUpdate);

    DbTableModelDto updateTableModel(DbTableModelDto tableModelToUpdate);

    DbColumnModelDto updateColumnModel(DbColumnModelDto columnModelToUpdate);

}
