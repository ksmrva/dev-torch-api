package io.ksmrva.visual.torch.domain.dto.model.database.source.config.driver;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelSourceConfigSupportedDriverDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceConfigSupportedDriverDto.class)
                      .verify();
    }

}
