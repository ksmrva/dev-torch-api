package io.ksmrva.visual.torch.domain.entity.model.database.table;

import io.ksmrva.visual.torch.domain.entity.model.database.component.table.TableCategory;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class TableCategoryTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(TableCategory.class)
                      .verify();
    }

}
