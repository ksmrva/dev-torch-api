package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql;

import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlDatabaseDetailTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        SqlTableDetail referencedTableTestA = new SqlTableDetail();
        referencedTableTestA.setName("referencedTableTestA");

        SqlTableDetail referencedTableTestB = new SqlTableDetail();
        referencedTableTestB.setName("referencedTableTestB");

        EqualsVerifier.simple()
                      .forClass(SqlDatabaseDetail.class)
                      .withPrefabValues(SqlTableDetail.class, referencedTableTestA, referencedTableTestB)
                      .verify();
    }

}
