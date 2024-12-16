package io.ksmrva.visual.torch.domain.dto.model.database.source.url;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class SqlDatabaseDetailSourceUrlDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceUrlDto.class)
                      .verify();
    }

}
