package io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell;

import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell.CanvasLinkCell;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CanvasLinkCellTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CanvasLinkCell.class)
                      .verify();
    }

}
