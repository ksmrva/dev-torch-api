package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.primary;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlPrimaryKeyColumnDetailDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(SqlPrimaryKeyColumnDetailDto.class)
                      .verify();
    }

}
