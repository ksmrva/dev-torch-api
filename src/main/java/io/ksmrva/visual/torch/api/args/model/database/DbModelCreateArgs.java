package io.ksmrva.visual.torch.api.args.model.database;

import io.ksmrva.visual.torch.api.args.misc.RegexMatcher;
import io.ksmrva.visual.torch.domain.dto.model.database.column.DbColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.name.DbModelNameDto;
import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableCategoryDto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DbModelCreateArgs {

    private DbModelNameDto name;

    private BigInteger sourceConfigId;

    private List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers;

    private List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers;

    public DbModelCreateArgs() {
        this.tableNameToCategoryMatchers = new ArrayList<>();
        this.columnNameToCategoryMatchers = new ArrayList<>();
    }

    public DbModelNameDto getName() {
        return name;
    }

    public void setName(DbModelNameDto name) {
        this.name = name;
    }

    public BigInteger getSourceConfigId() {
        return sourceConfigId;
    }

    public void setSourceConfigId(BigInteger sourceConfigId) {
        this.sourceConfigId = sourceConfigId;
    }

    public List<RegexMatcher<DbTableCategoryDto>> getTableCategoryMatchers() {
        return tableNameToCategoryMatchers;
    }

    public void setTableCategoryMatchers(List<RegexMatcher<DbTableCategoryDto>> tableNameToCategoryMatchers) {
        this.tableNameToCategoryMatchers = tableNameToCategoryMatchers;
    }

    public List<RegexMatcher<DbColumnCategoryDto>> getColumnCategoryMatchers() {
        return columnNameToCategoryMatchers;
    }

    public void setColumnCategoryMatchers(List<RegexMatcher<DbColumnCategoryDto>> columnNameToCategoryMatchers) {
        this.columnNameToCategoryMatchers = columnNameToCategoryMatchers;
    }

}
