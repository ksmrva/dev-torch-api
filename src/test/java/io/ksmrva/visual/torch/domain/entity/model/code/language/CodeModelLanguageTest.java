package io.ksmrva.visual.torch.domain.entity.model.code.language;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelLanguageTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelLanguage.class)
                      .verify();
    }

}
