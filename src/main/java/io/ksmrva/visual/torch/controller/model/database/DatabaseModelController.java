package io.ksmrva.visual.torch.controller.model.database;

import io.ksmrva.visual.torch.controller.AbstractApiController;
import io.ksmrva.visual.torch.controller.args.misc.RegexMatcher;
import io.ksmrva.visual.torch.controller.args.model.database.DbModelCreateArgs;
import io.ksmrva.visual.torch.domain.dto.model.database.DbModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnModelDto;
import io.ksmrva.visual.torch.domain.dto.model.database.data.DbDataTypeDto;
import io.ksmrva.visual.torch.domain.dto.model.database.name.DbModelNameDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import io.ksmrva.visual.torch.service.model.database.DatabaseModelService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(AbstractApiController.BASE_URI + "/model/db")
public class DatabaseModelController extends AbstractApiController {

    private final DatabaseModelService databaseModelService;

    public DatabaseModelController(DatabaseModelService databaseModelService) {
        this.databaseModelService = databaseModelService;
    }

    @PostMapping
    public @ResponseBody DbModelDto createDatabaseModel(@RequestBody DbModelCreateArgs dbModelCreateArgs) {
        BigInteger sourceConfigId = dbModelCreateArgs.getSourceConfigId();
        DbModelNameDto dbModelName = DbModelNameDto.build(dbModelCreateArgs.getName()
                                                                           .getDatabaseName(), dbModelCreateArgs.getName()
                                                                                                                .getSchemaName());
        List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers = dbModelCreateArgs.getTableCategoryMatchers();
        List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers = dbModelCreateArgs.getColumnCategoryMatchers();

        return databaseModelService.createDbModel(sourceConfigId, dbModelName, tableNameToCategoryMatchers, columnNameToCategoryMatchers);
    }

    @GetMapping("/{databaseName}/{schemaName}")
    public @ResponseBody DbModelDto getDatabaseModel(@PathVariable String databaseName, @PathVariable String schemaName) {
        DbModelDto databaseModel = databaseModelService.getDbModel(DbModelNameDto.build(databaseName, schemaName));
        if (databaseModel == null) {
            throw new RuntimeException("Database Model does not exist; database name [" + databaseName + "], schema [" + schemaName + "]");
        }
        return databaseModel;
    }

    @GetMapping
    public @ResponseBody List<DbModelDto> getAllDatabaseModels() {
        List<DbModelDto> databaseModels = databaseModelService.getAllDbModels();
        if (databaseModels == null) {
            throw new RuntimeException("No Database Models were found");
        }
        return databaseModels;
    }

    @GetMapping("/data/type")
    public @ResponseBody List<DbDataTypeDto> getOrCreateAllDataTypes() {
        return databaseModelService.getOrCreateAllDataTypes();
    }

    @GetMapping("/table/category")
    public @ResponseBody List<DbTableCategoryDto> getAllTableCategories() {
        return databaseModelService.getAllTableCategories();
    }

    @GetMapping("/column/category")
    public @ResponseBody List<DbColumnCategoryDto> getAllColumnCategories() {
        return databaseModelService.getAllColumnCategories();
    }

    @GetMapping("/name")
    public @ResponseBody List<DbModelNameDto> getAllDatabaseModelNames() {
        return databaseModelService.getAllDbModelNames();
    }

    @PutMapping
    public @ResponseBody DbModelDto updateDatabaseModel(@RequestBody DbModelDto databaseModelToUpdate) {
        return databaseModelService.updateDbModel(databaseModelToUpdate);
    }

    @PostMapping("/table")
    public @ResponseBody DbTableModelDto updateTableModel(@RequestBody DbTableModelDto tableModelToUpdate) {
        return databaseModelService.updateTableModel(tableModelToUpdate);
    }

    @PutMapping("/column")
    public @ResponseBody DbColumnModelDto updateColumnModel(@RequestBody DbColumnModelDto columnModelToUpdate) {
        return databaseModelService.updateColumnModel(columnModelToUpdate);
    }

}
