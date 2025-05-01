package io.ksmrva.visual.torch.data.entity.model.database.source.url.scheme;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlDatabaseDetailSourceUrlSupportedSchemeTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceUrlSupportedScheme.class)
                      .verify();
    }

}
