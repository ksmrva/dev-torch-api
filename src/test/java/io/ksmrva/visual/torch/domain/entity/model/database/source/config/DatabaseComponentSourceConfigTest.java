package io.ksmrva.visual.torch.domain.entity.model.database.source.config;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DatabaseComponentSourceConfigTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceConfig.class)
                      .verify();
    }

}
