package io.ksmrva.visual.torch.api.arg.model.database;

import io.ksmrva.visual.torch.api.arg.misc.RegexMatcher;
import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.path.DatabasePathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableCategoryDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DatabaseComponentCreateArgs {

    private DatabasePathDto path;

    private BigInteger sourceConfigId;

    private List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers;

    private List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers;

    public DatabaseComponentCreateArgs() {
        this.tableNameToCategoryMatchers = new ArrayList<>();
        this.columnNameToCategoryMatchers = new ArrayList<>();
    }

    public DatabasePathDto getPath() {
        return path;
    }

    public void setPath(DatabasePathDto path) {
        this.path = path;
    }

    public BigInteger getSourceConfigId() {
        return sourceConfigId;
    }

    public void setSourceConfigId(BigInteger sourceConfigId) {
        this.sourceConfigId = sourceConfigId;
    }

    public List<RegexMatcher<TableCategoryDto>> getTableCategoryMatchers() {
        return tableNameToCategoryMatchers;
    }

    public void setTableCategoryMatchers(List<RegexMatcher<TableCategoryDto>> tableNameToCategoryMatchers) {
        this.tableNameToCategoryMatchers = tableNameToCategoryMatchers;
    }

    public List<RegexMatcher<ColumnCategoryDto>> getColumnCategoryMatchers() {
        return columnNameToCategoryMatchers;
    }

    public void setColumnCategoryMatchers(List<RegexMatcher<ColumnCategoryDto>> columnNameToCategoryMatchers) {
        this.columnNameToCategoryMatchers = columnNameToCategoryMatchers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DatabaseComponentCreateArgs that)) {
            return false;
        }

        return new EqualsBuilder().append(getPath(), that.getPath())
                                  .append(getSourceConfigId(), that.getSourceConfigId())
                                  .append(tableNameToCategoryMatchers, that.tableNameToCategoryMatchers)
                                  .append(columnNameToCategoryMatchers, that.columnNameToCategoryMatchers)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPath())
                .append(getSourceConfigId())
                .append(tableNameToCategoryMatchers)
                .append(columnNameToCategoryMatchers)
                .toHashCode();
    }

}
