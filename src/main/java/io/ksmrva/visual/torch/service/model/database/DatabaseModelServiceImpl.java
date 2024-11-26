package io.ksmrva.visual.torch.service.model.database;

import io.ksmrva.visual.torch.api.args.misc.RegexMatcher;
import io.ksmrva.visual.torch.db.dao.model.database.DatabaseModelDao;
import io.ksmrva.visual.torch.domain.dto.model.database.DbModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.name.DbModelNameDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.data.DbDataTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseModelServiceImpl implements DatabaseModelService {

    private final DatabaseModelDao databaseModelDao;

    public DatabaseModelServiceImpl(DatabaseModelDao databaseModelDao) {
        this.databaseModelDao = databaseModelDao;
    }

    @Override
    public DbModelDto createDbModel(BigInteger sourceConfigId, DbModelNameDto dbModelName, List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        return databaseModelDao.createDbModel(sourceConfigId, dbModelName, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
    }

    @Override
    public DbModelDto getDbModel(DbModelNameDto dbModelName) {
        return databaseModelDao.getDbModel(dbModelName);
    }

    @Override
    public List<DbModelDto> getAllDbModels() {
        return databaseModelDao.getAllDbModels();
    }

    @Override
    public List<DbDataTypeDto> getOrCreateAllDataTypes() {
        return databaseModelDao.getOrCreateAllDataTypes();
    }

    @Override
    public List<DbTableCategoryDto> getAllTableCategories() {
        return databaseModelDao.getAllTableCategories();
    }

    @Override
    public List<DbColumnCategoryDto> getAllColumnCategories() {
        return databaseModelDao.getAllColumnCategories();
    }

    @Override
    public List<DbModelNameDto> getAllDbModelNames() {
        List<DbModelNameDto> dbModelNameDtos = new ArrayList<>();
        List<DbModelDto> dbModelDtos = this.getAllDbModels();
        for (DbModelDto dbModelDto : dbModelDtos) {
            DbModelNameDto dbModelNameDto = dbModelDto.getName();
            Assert.notNull(dbModelNameDto, "Database Model contained a null Model Name");

            dbModelNameDtos.add(dbModelNameDto);
        }
        return dbModelNameDtos;
    }

    @Override
    public DbModelDto updateDbModel(DbModelDto databaseModelToUpdate) {
        return databaseModelDao.updateDbModel(databaseModelToUpdate);
    }

    @Override
    public DbTableModelDto updateTableModel(DbTableModelDto tableModelToUpdate) {
        return databaseModelDao.updateTableModel(tableModelToUpdate);
    }

    @Override
    public DbColumnModelDto updateColumnModel(DbColumnModelDto columnModelToUpdate) {
        return databaseModelDao.updateColumnModel(columnModelToUpdate);
    }

}
