package io.ksmrva.visual.torch.domain.dto.model.database.constraint.key.primary;

import io.ksmrva.visual.torch.domain.dto.model.database.component.constraint.key.primary.PrimaryKeyComponentDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class PrimaryKeyComponentDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(PrimaryKeyComponentDto.class)
                      .verify();
    }

}
