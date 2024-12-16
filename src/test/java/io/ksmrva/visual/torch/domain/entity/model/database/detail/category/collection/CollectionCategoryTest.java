package io.ksmrva.visual.torch.domain.entity.model.database.detail.category.collection;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CollectionCategoryTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CollectionCategory.class)
                      .verify();
    }

}
