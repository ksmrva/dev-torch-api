package io.ksmrva.visual.torch.domain.dto.model.code.language;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelLanguageDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelLanguageDto.class)
                      .verify();
    }

}
