package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.foreign;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.table.SqlTableDetailDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlForeignKeyDetailDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        SqlTableDetailDto localTableDtoTestA = new SqlTableDetailDto();
        localTableDtoTestA.setName("localTableDtoTestA");

        SqlTableDetailDto localTableDtoTestB = new SqlTableDetailDto();
        localTableDtoTestB.setName("localTableDtoTestB");

        EqualsVerifier.simple()
                      .forClass(SqlForeignKeyDetailDto.class)
                      .withPrefabValues(SqlTableDetailDto.class, localTableDtoTestA, localTableDtoTestB)
                      .verify();
    }

}
