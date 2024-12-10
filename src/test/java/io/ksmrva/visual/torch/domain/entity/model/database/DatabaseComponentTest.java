package io.ksmrva.visual.torch.domain.entity.model.database;

import io.ksmrva.visual.torch.domain.entity.model.database.component.DatabaseComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DatabaseComponentTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        TableComponent referencedTableTestA = new TableComponent();
        referencedTableTestA.setName("referencedTableTestA");

        TableComponent referencedTableTestB = new TableComponent();
        referencedTableTestB.setName("referencedTableTestB");

        EqualsVerifier.simple()
                      .forClass(DatabaseComponent.class)
                      .withPrefabValues(TableComponent.class, referencedTableTestA, referencedTableTestB)
                      .verify();
    }

}
