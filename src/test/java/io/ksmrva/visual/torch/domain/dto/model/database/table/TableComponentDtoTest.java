package io.ksmrva.visual.torch.domain.dto.model.database.table;

import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableComponentDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class TableComponentDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        TableComponentDto localTableDtoTestA = new TableComponentDto();
        localTableDtoTestA.setName("localTableDtoTestA");

        TableComponentDto localTableDtoTestB = new TableComponentDto();
        localTableDtoTestB.setName("localTableDtoTestB");

        EqualsVerifier.simple()
                      .forClass(TableComponentDto.class)
                      .withPrefabValues(TableComponentDto.class, localTableDtoTestA, localTableDtoTestB)
                      .verify();
    }

}
