package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.constraint.key.primary.PrimaryKeyComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class PrimaryKeyComponentTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        TableComponent tableTestA = new TableComponent();
        tableTestA.setName("tableTestA");

        TableComponent tableTestB = new TableComponent();
        tableTestB.setName("tableTestB");

        ColumnComponent columnTestA = new ColumnComponent();
        columnTestA.setName("columnTestA");
        PrimaryKeyColumnComponent primaryKeyColumnTestA = new PrimaryKeyColumnComponent();
        primaryKeyColumnTestA.setColumn(columnTestA);

        ColumnComponent columnTestB = new ColumnComponent();
        columnTestB.setName("columnTestB");
        PrimaryKeyColumnComponent primaryKeyColumnTestB = new PrimaryKeyColumnComponent();
        primaryKeyColumnTestB.setColumn(columnTestB);

        EqualsVerifier.simple()
                      .forClass(PrimaryKeyComponent.class)
                      .withPrefabValues(TableComponent.class, tableTestA, tableTestB)
                      .withPrefabValues(PrimaryKeyColumnComponent.class, primaryKeyColumnTestA, primaryKeyColumnTestB)
                      .verify();
    }

}
