package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary;

import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column.SqlColumnDetail;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlPrimaryKeyDetailTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        SqlTableDetail tableTestA = new SqlTableDetail();
        tableTestA.setName("tableTestA");

        SqlTableDetail tableTestB = new SqlTableDetail();
        tableTestB.setName("tableTestB");

        SqlColumnDetail columnTestA = new SqlColumnDetail();
        columnTestA.setName("columnTestA");
        SqlPrimaryKeyColumnDetail primaryKeyColumnTestA = new SqlPrimaryKeyColumnDetail();
        primaryKeyColumnTestA.setColumn(columnTestA);

        SqlColumnDetail columnTestB = new SqlColumnDetail();
        columnTestB.setName("columnTestB");
        SqlPrimaryKeyColumnDetail primaryKeyColumnTestB = new SqlPrimaryKeyColumnDetail();
        primaryKeyColumnTestB.setColumn(columnTestB);

        EqualsVerifier.simple()
                      .forClass(SqlPrimaryKeyDetail.class)
                      .withPrefabValues(SqlTableDetail.class, tableTestA, tableTestB)
                      .withPrefabValues(SqlPrimaryKeyColumnDetail.class, primaryKeyColumnTestA, primaryKeyColumnTestB)
                      .verify();
    }

}
