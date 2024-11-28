package io.ksmrva.visual.torch.domain.entity.canvas;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CanvasTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(Canvas.class)
                      .verify();
    }

}
