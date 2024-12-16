package io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.entity.model.database.detail.sql.table.SqlTableDetail;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlForeignKeyDetailTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * Since the Foreign Key Model has a field for the Table that it is linked to as well as is contained in
         * the Table that owns it, there is a recursive instantiation from the Equals Verifier and as such, we need
         * to include pre-fab values to allow it to correctly instantiate their mock instances
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        SqlTableDetail localTableTestA = new SqlTableDetail();
        localTableTestA.setName("localTableTestA");

        SqlTableDetail localTableTestB = new SqlTableDetail();
        localTableTestB.setName("localTableTestB");

        EqualsVerifier.simple()
                      .forClass(SqlForeignKeyDetail.class)
                      .withPrefabValues(SqlTableDetail.class, localTableTestA, localTableTestB)
                      .verify();
    }

}
