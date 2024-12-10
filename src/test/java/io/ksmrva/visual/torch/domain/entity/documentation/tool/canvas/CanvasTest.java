package io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas;

import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.Canvas;
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
