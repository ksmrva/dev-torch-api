package io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell;

import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell.CanvasCustomCell;
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
