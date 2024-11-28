package io.ksmrva.visual.torch.domain.entity.model.database;

import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * Since the Foreign Key Model has a field for the Table that it is linked to as well as is contained in
         * the Table that owns it, there is a recursive instantiation from the Equals Verifier and as such, we need
         * to include pre-fab values to allow it to correctly instantiate their mock instances
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        DbTableModel referencedTableTestA = new DbTableModel();
        referencedTableTestA.setName("referencedTableTestA");

        DbTableModel referencedTableTestB = new DbTableModel();
        referencedTableTestB.setName("referencedTableTestB");

        EqualsVerifier.simple()
                      .forClass(DbModel.class)
                      .withPrefabValues(DbTableModel.class, referencedTableTestA, referencedTableTestB)
                      .verify();
    }

}
