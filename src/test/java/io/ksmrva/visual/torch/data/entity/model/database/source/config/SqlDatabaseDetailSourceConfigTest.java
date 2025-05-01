package io.ksmrva.visual.torch.data.entity.model.database.source.config;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlDatabaseDetailSourceConfigTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceConfig.class)
                      .verify();
    }

}
