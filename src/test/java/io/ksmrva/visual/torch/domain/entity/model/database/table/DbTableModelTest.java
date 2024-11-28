package io.ksmrva.visual.torch.domain.entity.model.database.table;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbTableModelTest {

    @Test
    public void equalsHashCodeContracts() {
        DbTableModel localTableTestA = new DbTableModel();
        localTableTestA.setName("localTableTestA");

        DbTableModel localTableTestB = new DbTableModel();
        localTableTestB.setName("localTableTestB");

        EqualsVerifier.simple()
                      .forClass(DbTableModel.class)
                      .withPrefabValues(DbTableModel.class, localTableTestA, localTableTestB)
                      .verify();
    }

}
