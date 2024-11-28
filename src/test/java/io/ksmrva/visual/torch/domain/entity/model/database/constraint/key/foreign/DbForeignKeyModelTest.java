package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbForeignKeyModelTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * Since the Foreign Key Model has a field for the Table that it is linked to as well as is contained in
         * the Table that owns it, there is a recursive instantiation from the Equals Verifier and as such, we need
         * to include pre-fab values to allow it to correctly instantiate their mock instances
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        DbTableModel localTableTestA = new DbTableModel();
        localTableTestA.setName("localTableTestA");

        DbTableModel localTableTestB = new DbTableModel();
        localTableTestB.setName("localTableTestB");

        EqualsVerifier.simple()
                      .forClass(DbForeignKeyModel.class)
                      .withPrefabValues(DbTableModel.class, localTableTestA, localTableTestB)
                      .verify();
    }

}
