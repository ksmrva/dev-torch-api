package io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class FieldCategoryDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(FieldCategoryDto.class)
                      .verify();
    }

}
