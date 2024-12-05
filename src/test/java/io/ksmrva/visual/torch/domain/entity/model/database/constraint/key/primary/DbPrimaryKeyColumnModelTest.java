package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnModel;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbPrimaryKeyColumnModelTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        DbPrimaryKeyModel primaryKeyTestA = new DbPrimaryKeyModel();
        primaryKeyTestA.setName("primaryKeyTestA");

        DbPrimaryKeyModel primaryKeyTestB = new DbPrimaryKeyModel();
        primaryKeyTestB.setName("primaryKeyTestB");

        DbColumnModel columnTestA = new DbColumnModel();
        columnTestA.setName("columnTestA");

        DbColumnModel columnTestB = new DbColumnModel();
        columnTestB.setName("columnTestB");

        EqualsVerifier.simple()
                      .forClass(DbPrimaryKeyColumnModel.class)
                      .withPrefabValues(DbPrimaryKeyModel.class, primaryKeyTestA, primaryKeyTestB)
                      .withPrefabValues(DbColumnModel.class, columnTestA, columnTestB)
                      .verify();
    }

}
