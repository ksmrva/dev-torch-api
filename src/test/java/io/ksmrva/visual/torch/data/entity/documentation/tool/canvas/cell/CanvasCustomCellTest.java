package io.ksmrva.visual.torch.data.entity.documentation.tool.canvas.cell;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CanvasCustomCellTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CanvasCustomCell.class)
                      .verify();
    }

}
