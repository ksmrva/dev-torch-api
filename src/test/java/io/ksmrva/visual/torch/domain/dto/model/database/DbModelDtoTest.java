package io.ksmrva.visual.torch.domain.dto.model.database;

import io.ksmrva.visual.torch.domain.dto.model.database.table.DbTableModelDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        DbTableModelDto localTableDtoTestA = new DbTableModelDto();
        localTableDtoTestA.setName("localTableDtoTestA");

        DbTableModelDto localTableDtoTestB = new DbTableModelDto();
        localTableDtoTestB.setName("localTableDtoTestB");

        EqualsVerifier.simple()
                      .forClass(DbModelDto.class)
                      .withPrefabValues(DbTableModelDto.class, localTableDtoTestA, localTableDtoTestB)
                      .verify();
    }

}
