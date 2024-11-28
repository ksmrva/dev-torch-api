package io.ksmrva.visual.torch.domain.dto.model.code.file.type.text.extension;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeTextFileExtensionDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeTextFileExtensionDto.class)
                      .verify();
    }

}
