package io.ksmrva.visual.torch.domain.dto.model.code.file.type;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeFileTypeDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeFileTypeDto.class)
                      .verify();
    }

}
