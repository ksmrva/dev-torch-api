package io.ksmrva.visual.torch.domain.entity.model.database.constraint.key.primary;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbPrimaryKeyModelTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbPrimaryKeyModel.class)
                      .verify();
    }

}
