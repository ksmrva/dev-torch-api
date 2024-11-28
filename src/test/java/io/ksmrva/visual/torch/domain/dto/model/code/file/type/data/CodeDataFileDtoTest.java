package io.ksmrva.visual.torch.domain.dto.model.code.file.type.data;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeDataFileDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeDataFileDto.class)
                      .verify();
    }

}
