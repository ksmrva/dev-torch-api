package io.ksmrva.visual.torch.domain.entity.model.database;

import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
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
