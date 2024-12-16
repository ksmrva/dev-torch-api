package io.ksmrva.visual.torch.controller.model.database.detail.sql;

import io.ksmrva.visual.torch.api.arg.constant.DevTorchApiConstants;
import io.ksmrva.visual.torch.api.arg.model.database.detail.sql.SqlDatabaseDetailCreateArgs;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.SqlDatabaseDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column.SqlColumnDetailDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.path.SqlDatabaseDetailPathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;
import io.ksmrva.visual.torch.service.model.database.detail.sql.SqlDatabaseModelDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DevTorchApiConstants.DATABASE_MODEL_SQL_DETAIL_BASE_URI_PATH)
public class SqlDatabaseModelDetailController {

    private final SqlDatabaseModelDetailService sqlDatabaseModelDetailService;

    public SqlDatabaseModelDetailController(SqlDatabaseModelDetailService sqlDatabaseModelDetailService) {
        this.sqlDatabaseModelDetailService = sqlDatabaseModelDetailService;
    }

    @PostMapping
    public @ResponseBody SqlDatabaseDetailDto createDatabase(@RequestBody SqlDatabaseDetailCreateArgs databaseCreateArgs) {
        return sqlDatabaseModelDetailService.createDatabase(databaseCreateArgs.getSourceConfigId(),
                                                            databaseCreateArgs.getPath(),
                                                            databaseCreateArgs.getTableCategoryMatchers(),
                                                            databaseCreateArgs.getColumnCategoryMatchers());
    }

    @GetMapping("/{databaseName}/{schemaName}")
    public @ResponseBody SqlDatabaseDetailDto getDatabase(@PathVariable String databaseName, @PathVariable String schemaName) {
        return sqlDatabaseModelDetailService.getDatabase(SqlDatabaseDetailPathDto.build(databaseName, schemaName));
    }

    @GetMapping
    public @ResponseBody List<SqlDatabaseDetailDto> getAllDatabases() {
        return sqlDatabaseModelDetailService.getAllDatabases();
    }


    @GetMapping("/path")
    public @ResponseBody List<SqlDatabaseDetailPathDto> getAllDatabasePaths() {
        return sqlDatabaseModelDetailService.getAllDatabasePaths();
    }

    @PutMapping
    public @ResponseBody SqlDatabaseDetailDto updateDatabase(@RequestBody SqlDatabaseDetailDto databaseToUpdate) {
        return sqlDatabaseModelDetailService.updateDatabase(databaseToUpdate);
    }

    @PostMapping("/table")
    public @ResponseBody SqlTableDetailDto updateTable(@RequestBody SqlTableDetailDto tableToUpdate) {
        return sqlDatabaseModelDetailService.updateTable(tableToUpdate);
    }

    @PutMapping("/column")
    public @ResponseBody SqlColumnDetailDto updateColumn(@RequestBody SqlColumnDetailDto columnToUpdate) {
        return sqlDatabaseModelDetailService.updateColumn(columnToUpdate);
    }

}
