package io.ksmrva.visual.torch.domain.entity.model.database.detail.category.field;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class FieldCategoryTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(FieldCategory.class)
                      .verify();
    }

}
