package io.ksmrva.visual.torch.controller.model.database.component;

import io.ksmrva.visual.torch.api.arg.constant.DevTorchApiConstants;
import io.ksmrva.visual.torch.api.arg.model.database.DatabaseComponentCreateArgs;
import io.ksmrva.visual.torch.domain.dto.model.database.component.DbComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.path.DatabasePathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;
import io.ksmrva.visual.torch.service.model.database.component.DatabaseModelComponentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DevTorchApiConstants.DATABASE_MODEL_COMPONENT_BASE_URI_PATH)
public class DatabaseModelComponentController {

    private final DatabaseModelComponentService databaseModelComponentService;

    public DatabaseModelComponentController(DatabaseModelComponentService databaseModelComponentService) {
        this.databaseModelComponentService = databaseModelComponentService;
    }

    @PostMapping
    public @ResponseBody DbComponentDto createDatabase(@RequestBody DatabaseComponentCreateArgs databaseCreateArgs) {
        return databaseModelComponentService.createDatabase(databaseCreateArgs.getSourceConfigId(),
                                                            databaseCreateArgs.getPath(),
                                                            databaseCreateArgs.getTableCategoryMatchers(),
                                                            databaseCreateArgs.getColumnCategoryMatchers());
    }

    @GetMapping("/{databaseName}/{schemaName}")
    public @ResponseBody DbComponentDto getDatabase(@PathVariable String databaseName, @PathVariable String schemaName) {
        return databaseModelComponentService.getDatabase(DatabasePathDto.build(databaseName, schemaName));
    }

    @GetMapping
    public @ResponseBody List<DbComponentDto> getAllDatabases() {
        return databaseModelComponentService.getAllDatabases();
    }

    @GetMapping("/table/category")
    public @ResponseBody List<TableCategoryDto> getAllTableCategories() {
        return databaseModelComponentService.getAllTableCategories();
    }

    @GetMapping("/column/category")
    public @ResponseBody List<ColumnCategoryDto> getAllColumnCategories() {
        return databaseModelComponentService.getAllColumnCategories();
    }

    @GetMapping("/name")
    public @ResponseBody List<DatabasePathDto> getAllDatabasePaths() {
        return databaseModelComponentService.getAllDatabasePaths();
    }

    @PutMapping
    public @ResponseBody DbComponentDto updateDatabase(@RequestBody DbComponentDto databaseToUpdate) {
        return databaseModelComponentService.updateDatabase(databaseToUpdate);
    }

    @PostMapping("/table")
    public @ResponseBody TableComponentDto updateTable(@RequestBody TableComponentDto tableToUpdate) {
        return databaseModelComponentService.updateTable(tableToUpdate);
    }

    @PutMapping("/column")
    public @ResponseBody ColumnComponentDto updateColumn(@RequestBody ColumnComponentDto columnToUpdate) {
        return databaseModelComponentService.updateColumn(columnToUpdate);
    }

}
