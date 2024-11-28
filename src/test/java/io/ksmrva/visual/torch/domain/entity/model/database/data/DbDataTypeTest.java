package io.ksmrva.visual.torch.domain.entity.model.database.data;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbDataTypeTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbDataType.class)
                      .verify();
    }

}
