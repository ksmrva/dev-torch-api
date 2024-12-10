package io.ksmrva.visual.torch.domain.entity.model.code.language;

import io.ksmrva.visual.torch.domain.entity.model.code.source.language.CodeModelSourceLanguage;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceLanguageTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelSourceLanguage.class)
                      .verify();
    }

}
