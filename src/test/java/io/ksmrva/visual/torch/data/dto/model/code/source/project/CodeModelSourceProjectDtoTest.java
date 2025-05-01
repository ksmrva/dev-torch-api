package io.ksmrva.visual.torch.data.dto.model.code.source.project;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceProjectDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelSourceProjectDto.class)
                      .verify();
    }

}
