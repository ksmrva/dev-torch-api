package io.ksmrva.visual.torch.domain.dto.model.database.column;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbColumnModelDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbColumnModelDto.class)
                      .verify();
    }

}
