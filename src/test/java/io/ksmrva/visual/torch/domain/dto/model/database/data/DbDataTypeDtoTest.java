package io.ksmrva.visual.torch.domain.dto.model.database.data;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbDataTypeDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbDataTypeDto.class)
                      .verify();
    }

}
