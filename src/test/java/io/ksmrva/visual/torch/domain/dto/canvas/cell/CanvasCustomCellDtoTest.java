package io.ksmrva.visual.torch.domain.dto.canvas.cell;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CanvasCustomCellDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CanvasCustomCellDto.class)
                      .verify();
    }

}
