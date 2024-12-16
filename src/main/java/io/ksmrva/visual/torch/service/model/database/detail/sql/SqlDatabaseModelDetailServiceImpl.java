package io.ksmrva.visual.torch.service.model.database.detail.sql;

import io.ksmrva.visual.torch.api.arg.misc.RegexMatcher;
import io.ksmrva.visual.torch.db.dao.model.database.detail.sql.SqlDatabaseModelDetailDao;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.SqlDatabaseDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.path.SqlDatabaseDetailPathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class SqlDatabaseModelDetailServiceImpl implements SqlDatabaseModelDetailService {

    private final SqlDatabaseModelDetailDao sqlDatabaseModelDetailDao;

    public SqlDatabaseModelDetailServiceImpl(SqlDatabaseModelDetailDao sqlDatabaseModelDetailDao) {
        this.sqlDatabaseModelDetailDao = sqlDatabaseModelDetailDao;
    }

    @Override
    public SqlDatabaseDetailDto createDatabase(BigInteger sourceConfigId, SqlDatabaseDetailPathDto sqlDatabaseDetailPathDto, List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers, List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        return sqlDatabaseModelDetailDao.createDatabase(sourceConfigId, sqlDatabaseDetailPathDto, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
    }

    @Override
    public SqlDatabaseDetailDto getDatabase(SqlDatabaseDetailPathDto sqlDatabaseDetailPathDto) {
        return sqlDatabaseModelDetailDao.getDatabase(sqlDatabaseDetailPathDto);
    }

    @Override
    public List<SqlDatabaseDetailDto> getAllDatabases() {
        return sqlDatabaseModelDetailDao.getAllDatabases();
    }

    @Override
    public List<SqlDatabaseDetailPathDto> getAllDatabasePaths() {
        List<SqlDatabaseDetailPathDto> sqlDatabaseDetailPathDtos = new ArrayList<>();
        List<SqlDatabaseDetailDto> databases = this.getAllDatabases();
        for (SqlDatabaseDetailDto database : databases) {
            SqlDatabaseDetailPathDto sqlDatabaseDetailPathDto = database.getPath();
            Assert.notNull(sqlDatabaseDetailPathDto, "Database Detail contained a null Database Path");

            sqlDatabaseDetailPathDtos.add(sqlDatabaseDetailPathDto);
        }
        return sqlDatabaseDetailPathDtos;
    }

    @Override
    public SqlDatabaseDetailDto updateDatabase(SqlDatabaseDetailDto databaseToUpdate) {
        return sqlDatabaseModelDetailDao.updateDatabase(databaseToUpdate);
    }

    @Override
    public SqlTableDetailDto updateTable(SqlTableDetailDto tableToUpdate) {
        return sqlDatabaseModelDetailDao.updateTable(tableToUpdate);
    }

    @Override
    public SqlColumnDetailDto updateColumn(SqlColumnDetailDto columnToUpdate) {
        return sqlDatabaseModelDetailDao.updateColumn(columnToUpdate);
    }

}
