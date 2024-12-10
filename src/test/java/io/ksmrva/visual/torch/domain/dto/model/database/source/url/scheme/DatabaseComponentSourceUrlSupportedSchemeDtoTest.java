package io.ksmrva.visual.torch.domain.dto.model.database.source.url.scheme;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DatabaseComponentSourceUrlSupportedSchemeDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceUrlSupportedSchemeDto.class)
                      .verify();
    }

}
