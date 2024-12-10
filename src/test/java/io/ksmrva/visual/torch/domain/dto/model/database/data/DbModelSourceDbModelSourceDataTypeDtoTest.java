package io.ksmrva.visual.torch.domain.dto.model.database.data;

import io.ksmrva.visual.torch.domain.dto.model.database.source.data.DbModelSourceDataTypeDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DbModelSourceDbModelSourceDataTypeDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourceDataTypeDto.class)
                      .verify();
    }

}
