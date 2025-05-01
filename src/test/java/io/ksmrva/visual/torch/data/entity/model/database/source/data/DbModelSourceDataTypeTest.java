package io.ksmrva.visual.torch.data.entity.model.database.source.data;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelSourceDataTypeTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceDataType.class)
                      .verify();
    }

}
