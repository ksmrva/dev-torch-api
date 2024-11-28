package io.ksmrva.visual.torch.domain.dto.model.code.file;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeFileDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeFileDto.class)
                      .verify();
    }

}
