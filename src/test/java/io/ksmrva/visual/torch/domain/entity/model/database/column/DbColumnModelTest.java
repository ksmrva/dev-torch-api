package io.ksmrva.visual.torch.domain.entity.model.database.column;

import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbColumnModelTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        DbTableModel localTableTestA = new DbTableModel();
        localTableTestA.setName("localTableTestA");

        DbTableModel localTableTestB = new DbTableModel();
        localTableTestB.setName("localTableTestB");

        EqualsVerifier.simple()
                      .forClass(DbColumnModel.class)
                      .withPrefabValues(DbTableModel.class, localTableTestA, localTableTestB)
                      .verify();
    }

}
