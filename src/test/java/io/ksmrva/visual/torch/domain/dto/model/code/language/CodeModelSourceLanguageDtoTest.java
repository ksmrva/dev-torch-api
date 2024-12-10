package io.ksmrva.visual.torch.domain.dto.model.code.language;

import io.ksmrva.visual.torch.domain.dto.model.code.source.language.CodeModelSourceLanguageDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceLanguageDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelSourceLanguageDto.class)
                      .verify();
    }

}
