package io.ksmrva.visual.torch.data.dto.model.database.source.config;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlDatabaseDetailSourceConfigDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceConfigDto.class)
                      .verify();
    }

}
