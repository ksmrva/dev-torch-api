package io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.primary.PrimaryKeyColumnComponentDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class PrimaryKeyColumnComponentDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(PrimaryKeyColumnComponentDto.class)
                      .verify();
    }

}
