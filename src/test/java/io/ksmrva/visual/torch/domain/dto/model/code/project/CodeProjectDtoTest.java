package io.ksmrva.visual.torch.domain.dto.model.code.project;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeProjectDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeProjectDto.class)
                      .verify();
    }

}
