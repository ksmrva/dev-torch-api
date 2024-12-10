package io.ksmrva.visual.torch.domain.entity.model.database.column;

import io.ksmrva.visual.torch.domain.entity.model.database.component.column.ColumnCategory;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class ColumnCategoryTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(ColumnCategory.class)
                      .verify();
    }

}
