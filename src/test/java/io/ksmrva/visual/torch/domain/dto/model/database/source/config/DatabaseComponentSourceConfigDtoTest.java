package io.ksmrva.visual.torch.domain.dto.model.database.source.config;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DatabaseComponentSourceConfigDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceConfigDto.class)
                      .verify();
    }

}
