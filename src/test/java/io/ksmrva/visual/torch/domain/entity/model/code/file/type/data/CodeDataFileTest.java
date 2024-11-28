package io.ksmrva.visual.torch.domain.entity.model.code.file.type.data;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeDataFileTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeDataFile.class)
                      .verify();
    }

}
