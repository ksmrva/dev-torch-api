package io.ksmrva.visual.torch.domain.entity.model.database.source.url.provider;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlDatabaseDetailSourceUrlSupportedProviderTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceUrlSupportedProvider.class)
                      .verify();
    }

}
