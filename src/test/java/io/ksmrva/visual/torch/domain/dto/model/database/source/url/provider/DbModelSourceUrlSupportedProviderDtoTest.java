package io.ksmrva.visual.torch.domain.dto.model.database.source.url.provider;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelSourceUrlSupportedProviderDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceUrlSupportedProviderDto.class)
                      .verify();
    }

}
