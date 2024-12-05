package io.ksmrva.visual.torch.domain.dto.model.database.table;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbTableModelDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        DbTableModelDto localTableDtoTestA = new DbTableModelDto();
        localTableDtoTestA.setName("localTableDtoTestA");

        DbTableModelDto localTableDtoTestB = new DbTableModelDto();
        localTableDtoTestB.setName("localTableDtoTestB");

        EqualsVerifier.simple()
                      .forClass(DbTableModelDto.class)
                      .withPrefabValues(DbTableModelDto.class, localTableDtoTestA, localTableDtoTestB)
                      .verify();
    }

}
