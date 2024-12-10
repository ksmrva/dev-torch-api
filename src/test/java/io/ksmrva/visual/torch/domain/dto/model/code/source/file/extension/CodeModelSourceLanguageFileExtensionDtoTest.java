package io.ksmrva.visual.torch.domain.dto.model.code.source.file.extension;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceLanguageFileExtensionDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelSourceLanguageFileExtensionDto.class)
                      .verify();
    }

}
