package io.ksmrva.visual.torch.domain.entity.model.code.project;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeProjectTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeProject.class)
                      .verify();
    }

}
