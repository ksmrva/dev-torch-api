package io.ksmrva.visual.torch.domain.entity.model.database.source.data;

import io.ksmrva.visual.torch.domain.entity.model.database.source.data.DbModelSourceDataType;
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
