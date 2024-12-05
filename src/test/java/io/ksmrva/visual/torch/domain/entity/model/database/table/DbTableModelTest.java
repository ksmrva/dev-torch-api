package io.ksmrva.visual.torch.domain.entity.model.database.table;

import io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary.DbPrimaryKeyModel;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbTableModelTest {

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

        DbPrimaryKeyModel primaryKeyTestA = new DbPrimaryKeyModel();
        primaryKeyTestA.setName("primaryKeyTestA");

        DbPrimaryKeyModel primaryKeyTestB = new DbPrimaryKeyModel();
        primaryKeyTestB.setName("primaryKeyTestB");

        EqualsVerifier.simple()
                      .forClass(DbTableModel.class)
                      .withPrefabValues(DbTableModel.class, localTableTestA, localTableTestB)
                      .withPrefabValues(DbPrimaryKeyModel.class, primaryKeyTestA, primaryKeyTestB)
                      .verify();
    }

}
