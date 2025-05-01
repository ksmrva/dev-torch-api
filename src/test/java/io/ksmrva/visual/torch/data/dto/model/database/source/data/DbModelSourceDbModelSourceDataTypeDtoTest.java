package io.ksmrva.visual.torch.data.dto.model.database.source.data;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelSourceDbModelSourceDataTypeDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceDataTypeDto.class)
                      .verify();
    }

}
