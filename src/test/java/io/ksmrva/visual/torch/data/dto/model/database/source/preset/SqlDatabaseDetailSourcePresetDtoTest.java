package io.ksmrva.visual.torch.data.dto.model.database.source.preset;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlDatabaseDetailSourcePresetDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourcePresetDto.class)
                      .verify();
    }

}
