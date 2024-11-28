package io.ksmrva.visual.torch.domain.entity.model.database.column;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbColumnModelTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbColumnModel.class)
                      .verify();
    }

}
