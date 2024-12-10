package io.ksmrva.visual.torch.domain.entity.model.database.table;

import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class TableComponentTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        TableComponent localTableTestA = new TableComponent();
        localTableTestA.setName("localTableTestA");

        TableComponent localTableTestB = new TableComponent();
        localTableTestB.setName("localTableTestB");

        PrimaryKeyComponent primaryKeyTestA = new PrimaryKeyComponent();
        primaryKeyTestA.setName("primaryKeyTestA");

        PrimaryKeyComponent primaryKeyTestB = new PrimaryKeyComponent();
        primaryKeyTestB.setName("primaryKeyTestB");

        EqualsVerifier.simple()
                      .forClass(TableComponent.class)
                      .withPrefabValues(TableComponent.class, localTableTestA, localTableTestB)
                      .withPrefabValues(PrimaryKeyComponent.class, primaryKeyTestA, primaryKeyTestB)
                      .verify();
    }

}
