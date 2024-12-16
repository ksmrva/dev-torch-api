package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary;

import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column.SqlColumnDetail;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlPrimaryKeyColumnDetailTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        SqlPrimaryKeyDetail primaryKeyTestA = new SqlPrimaryKeyDetail();
        primaryKeyTestA.setName("primaryKeyTestA");

        SqlPrimaryKeyDetail primaryKeyTestB = new SqlPrimaryKeyDetail();
        primaryKeyTestB.setName("primaryKeyTestB");

        SqlColumnDetail columnTestA = new SqlColumnDetail();
        columnTestA.setName("columnTestA");

        SqlColumnDetail columnTestB = new SqlColumnDetail();
        columnTestB.setName("columnTestB");

        EqualsVerifier.simple()
                      .forClass(SqlPrimaryKeyColumnDetail.class)
                      .withPrefabValues(SqlPrimaryKeyDetail.class, primaryKeyTestA, primaryKeyTestB)
                      .withPrefabValues(SqlColumnDetail.class, columnTestA, columnTestB)
                      .verify();
    }

}
