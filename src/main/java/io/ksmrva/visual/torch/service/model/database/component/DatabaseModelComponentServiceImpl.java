package io.ksmrva.visual.torch.service.model.database.component;

import io.ksmrva.visual.torch.api.arg.misc.RegexMatcher;
import io.ksmrva.visual.torch.db.dao.model.database.component.DatabaseModelComponentDao;
import io.ksmrva.visual.torch.domain.dto.model.database.component.DbComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.path.DatabasePathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseModelComponentServiceImpl implements DatabaseModelComponentService {

    private final DatabaseModelComponentDao databaseModelComponentDao;

    public DatabaseModelComponentServiceImpl(DatabaseModelComponentDao databaseModelComponentDao) {
        this.databaseModelComponentDao = databaseModelComponentDao;
    }

    @Override
    public DbComponentDto createDatabase(BigInteger sourceConfigId, DatabasePathDto databasePathDto, List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        return databaseModelComponentDao.createDatabase(sourceConfigId, databasePathDto, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
    }

    @Override
    public DbComponentDto getDatabase(DatabasePathDto databasePathDto) {
        return databaseModelComponentDao.getDatabase(databasePathDto);
    }

    @Override
    public List<DbComponentDto> getAllDatabases() {
        return databaseModelComponentDao.getAllDatabases();
    }

    @Override
    public List<TableCategoryDto> getAllTableCategories() {
        return databaseModelComponentDao.getAllTableCategories();
    }

    @Override
    public List<ColumnCategoryDto> getAllColumnCategories() {
        return databaseModelComponentDao.getAllColumnCategories();
    }

    @Override
    public List<DatabasePathDto> getAllDatabasePaths() {
        List<DatabasePathDto> databasePathDtos = new ArrayList<>();
        List<DbComponentDto> databases = this.getAllDatabases();
        for (DbComponentDto database : databases) {
            DatabasePathDto databasePathDto = database.getName();
            Assert.notNull(databasePathDto, "Database Component contained a null Database Path");

            databasePathDtos.add(databasePathDto);
        }
        return databasePathDtos;
    }

    @Override
    public DbComponentDto updateDatabase(DbComponentDto databaseToUpdate) {
        return databaseModelComponentDao.updateDatabase(databaseToUpdate);
    }

    @Override
    public TableComponentDto updateTable(TableComponentDto tableToUpdate) {
        return databaseModelComponentDao.updateTable(tableToUpdate);
    }

    @Override
    public ColumnComponentDto updateColumn(ColumnComponentDto columnToUpdate) {
        return databaseModelComponentDao.updateColumn(columnToUpdate);
    }

}
