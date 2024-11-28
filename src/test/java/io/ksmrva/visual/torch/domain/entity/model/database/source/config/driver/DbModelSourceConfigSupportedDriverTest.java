package io.ksmrva.visual.torch.domain.entity.model.database.source.config.driver;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelSourceConfigSupportedDriverTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceConfigSupportedDriver.class)
                      .verify();
    }

}
