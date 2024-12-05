package io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbForeignKeyModelDtoTest {

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
                      .forClass(DbForeignKeyModelDto.class)
                      .withPrefabValues(DbTableModelDto.class, localTableDtoTestA, localTableDtoTestB)
                      .verify();
    }

}
