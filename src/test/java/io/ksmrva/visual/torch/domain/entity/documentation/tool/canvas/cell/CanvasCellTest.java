package io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell;

import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell.CanvasCell;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CanvasCellTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CanvasCell.class)
                      .verify();
    }

}
