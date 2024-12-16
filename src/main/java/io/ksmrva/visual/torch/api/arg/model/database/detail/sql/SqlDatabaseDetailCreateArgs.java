package io.ksmrva.visual.torch.api.arg.model.database.detail.sql;

import io.ksmrva.visual.torch.api.arg.misc.RegexMatcher;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.path.SqlDatabaseDetailPathDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabaseDetailCreateArgs {

    private SqlDatabaseDetailPathDto path;

    private BigInteger sourceConfigId;

    private List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers;

    private List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers;

    public SqlDatabaseDetailCreateArgs() {
        this.tableNameToCategoryMatchers = new ArrayList<>();
        this.columnNameToCategoryMatchers = new ArrayList<>();
    }

    public SqlDatabaseDetailPathDto getPath() {
        return path;
    }

    public void setPath(SqlDatabaseDetailPathDto path) {
        this.path = path;
    }

    public BigInteger getSourceConfigId() {
        return sourceConfigId;
    }

    public void setSourceConfigId(BigInteger sourceConfigId) {
        this.sourceConfigId = sourceConfigId;
    }

    public List<RegexMatcher<CollectionCategoryDto>> getTableCategoryMatchers() {
        return tableNameToCategoryMatchers;
    }

    public void setTableCategoryMatchers(List<RegexMatcher<CollectionCategoryDto>> tableNameToCategoryMatchers) {
        this.tableNameToCategoryMatchers = tableNameToCategoryMatchers;
    }

    public List<RegexMatcher<FieldCategoryDto>> getColumnCategoryMatchers() {
        return columnNameToCategoryMatchers;
    }

    public void setColumnCategoryMatchers(List<RegexMatcher<FieldCategoryDto>> columnNameToCategoryMatchers) {
        this.columnNameToCategoryMatchers = columnNameToCategoryMatchers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SqlDatabaseDetailCreateArgs that)) {
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
