package io.ksmrva.visual.torch.domain.dto.model.code.file.type.text;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeTextFileDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeTextFileDto.class)
                      .verify();
    }

}
