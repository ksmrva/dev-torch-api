package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyComponent;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class PrimaryKeyColumnComponentTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        PrimaryKeyComponent primaryKeyTestA = new PrimaryKeyComponent();
        primaryKeyTestA.setName("primaryKeyTestA");

        PrimaryKeyComponent primaryKeyTestB = new PrimaryKeyComponent();
        primaryKeyTestB.setName("primaryKeyTestB");

        ColumnComponent columnTestA = new ColumnComponent();
        columnTestA.setName("columnTestA");

        ColumnComponent columnTestB = new ColumnComponent();
        columnTestB.setName("columnTestB");

        EqualsVerifier.simple()
                      .forClass(PrimaryKeyColumnComponent.class)
                      .withPrefabValues(PrimaryKeyComponent.class, primaryKeyTestA, primaryKeyTestB)
                      .withPrefabValues(ColumnComponent.class, columnTestA, columnTestB)
                      .verify();
    }

}
