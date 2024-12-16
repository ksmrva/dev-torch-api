package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.column;

import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlColumnDetailTest {

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

        EqualsVerifier.simple()
                      .forClass(SqlColumnDetail.class)
                      .withPrefabValues(SqlTableDetail.class, localTableTestA, localTableTestB)
                      .verify();
    }

}
