package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.entity.model.database.column.DbColumnModel;
import io.ksmrva.visual.torch.domain.entity.model.database.table.DbTableModel;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbPrimaryKeyModelTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        DbTableModel tableTestA = new DbTableModel();
        tableTestA.setName("tableTestA");

        DbTableModel tableTestB = new DbTableModel();
        tableTestB.setName("tableTestB");

        DbColumnModel columnTestA = new DbColumnModel();
        columnTestA.setName("columnTestA");
        DbPrimaryKeyColumnModel primaryKeyColumnTestA = new DbPrimaryKeyColumnModel();
        primaryKeyColumnTestA.setColumn(columnTestA);

        DbColumnModel columnTestB = new DbColumnModel();
        columnTestB.setName("columnTestB");
        DbPrimaryKeyColumnModel primaryKeyColumnTestB = new DbPrimaryKeyColumnModel();
        primaryKeyColumnTestB.setColumn(columnTestB);

        EqualsVerifier.simple()
                      .forClass(DbPrimaryKeyModel.class)
                      .withPrefabValues(DbTableModel.class, tableTestA, tableTestB)
                      .withPrefabValues(DbPrimaryKeyColumnModel.class, primaryKeyColumnTestA, primaryKeyColumnTestB)
                      .verify();
    }

}
