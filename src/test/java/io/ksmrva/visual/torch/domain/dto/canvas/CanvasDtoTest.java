package io.ksmrva.visual.torch.domain.dto.canvas;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CanvasDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                        .forClass(CanvasDto.class)
                        .verify();
    }

}
