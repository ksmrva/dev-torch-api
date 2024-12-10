package io.ksmrva.visual.torch.domain.dto.model.database.column;

import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnComponentDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class ColumnComponentDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(ColumnComponentDto.class)
                      .verify();
    }

}
