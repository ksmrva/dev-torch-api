package io.ksmrva.visual.torch.domain.dto.model.database.detail.sql.column;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlColumnDetailDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(SqlColumnDetailDto.class)
                      .verify();
    }

}
