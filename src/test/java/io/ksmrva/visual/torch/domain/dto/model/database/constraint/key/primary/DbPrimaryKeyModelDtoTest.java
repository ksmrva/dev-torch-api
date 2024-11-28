package io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbPrimaryKeyModelDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbPrimaryKeyModelDto.class)
                      .verify();
    }

}
