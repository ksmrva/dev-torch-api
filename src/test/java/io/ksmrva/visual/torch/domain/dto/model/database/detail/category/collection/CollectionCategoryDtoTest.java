package io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CollectionCategoryDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CollectionCategoryDto.class)
                      .verify();
    }

}
