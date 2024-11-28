package io.ksmrva.visual.torch.domain.entity.model.code.file.type.text;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeTextFileTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeTextFile.class)
                      .verify();
    }

}
