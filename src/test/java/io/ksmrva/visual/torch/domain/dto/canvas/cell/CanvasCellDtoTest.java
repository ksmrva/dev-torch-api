package io.ksmrva.visual.torch.domain.dto.canvas.cell;

import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasCellDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CanvasCellDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CanvasCellDto.class)
                      .verify();
    }

}
