package io.ksmrva.visual.torch.domain.entity.model.code.file;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeFileTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeFile.class)
                      .verify();
    }

}
