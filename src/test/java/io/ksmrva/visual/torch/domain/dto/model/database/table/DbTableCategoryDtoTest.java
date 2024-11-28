package io.ksmrva.visual.torch.domain.dto.model.database.table;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbTableCategoryDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbTableCategoryDto.class)
                      .verify();
    }

}
