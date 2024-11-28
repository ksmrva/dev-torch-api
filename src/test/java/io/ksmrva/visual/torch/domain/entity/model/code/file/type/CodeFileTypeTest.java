package io.ksmrva.visual.torch.domain.entity.model.code.file.type;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeFileTypeTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeFileType.class)
                      .verify();
    }

}
