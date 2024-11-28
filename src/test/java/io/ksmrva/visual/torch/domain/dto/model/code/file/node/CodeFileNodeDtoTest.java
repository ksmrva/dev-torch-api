package io.ksmrva.visual.torch.domain.dto.model.code.file.node;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeFileNodeDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeFileNodeDto.class)
                      .verify();
    }

}
