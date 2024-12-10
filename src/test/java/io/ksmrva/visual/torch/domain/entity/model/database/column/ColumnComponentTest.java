package io.ksmrva.visual.torch.domain.entity.model.database.column;

import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnComponent;
import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableComponent;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class ColumnComponentTest {

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

        EqualsVerifier.simple()
                      .forClass(ColumnComponent.class)
                      .withPrefabValues(TableComponent.class, localTableTestA, localTableTestB)
                      .verify();
    }

}
