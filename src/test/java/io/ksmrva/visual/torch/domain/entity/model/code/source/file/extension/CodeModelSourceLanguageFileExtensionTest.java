package io.ksmrva.visual.torch.domain.entity.model.code.source.file.extension;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceLanguageFileExtensionTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelSourceLanguageFileExtension.class)
                      .verify();
    }

}
