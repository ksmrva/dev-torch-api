package io.ksmrva.visual.torch.domain.dto.canvas.cell;

import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasLinkCellDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CanvasLinkCellDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CanvasLinkCellDto.class)
                      .verify();
    }

}
