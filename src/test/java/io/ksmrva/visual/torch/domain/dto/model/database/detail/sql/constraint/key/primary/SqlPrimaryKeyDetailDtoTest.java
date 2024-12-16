package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.constraint.key.primary;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlPrimaryKeyDetailDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(SqlPrimaryKeyDetailDto.class)
                      .verify();
    }

}
