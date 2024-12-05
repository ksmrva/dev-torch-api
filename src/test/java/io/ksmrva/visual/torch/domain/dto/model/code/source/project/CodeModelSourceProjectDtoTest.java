package io.ksmrva.visual.torch.domain.dto.model.code.source.project;

import io.ksmrva.visual.torch.domain.dto.model.code.source.project.CodeModelSourceProjectDto;
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
