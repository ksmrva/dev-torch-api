package io.ksmrva.visual.torch.domain.entity.model.database.table;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbTableCategoryTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbTableCategory.class)
                      .verify();
    }

}
