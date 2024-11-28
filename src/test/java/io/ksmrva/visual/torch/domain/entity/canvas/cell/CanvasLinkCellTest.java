package io.ksmrva.visual.torch.domain.entity.canvas.cell;

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
