package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table;

import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.primary.SqlPrimaryKeyDetail;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlTableDetailTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        SqlTableDetail localTableTestA = new SqlTableDetail();
        localTableTestA.setName("localTableTestA");

        SqlTableDetail localTableTestB = new SqlTableDetail();
        localTableTestB.setName("localTableTestB");

        SqlPrimaryKeyDetail primaryKeyTestA = new SqlPrimaryKeyDetail();
        primaryKeyTestA.setName("primaryKeyTestA");

        SqlPrimaryKeyDetail primaryKeyTestB = new SqlPrimaryKeyDetail();
        primaryKeyTestB.setName("primaryKeyTestB");

        EqualsVerifier.simple()
                      .forClass(SqlTableDetail.class)
                      .withPrefabValues(SqlTableDetail.class, localTableTestA, localTableTestB)
                      .withPrefabValues(SqlPrimaryKeyDetail.class, primaryKeyTestA, primaryKeyTestB)
                      .verify();
    }

}
