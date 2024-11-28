package io.ksmrva.visual.torch.domain.dto.model.database.column;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbColumnCategoryDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbColumnCategoryDto.class)
                      .verify();
    }

}
