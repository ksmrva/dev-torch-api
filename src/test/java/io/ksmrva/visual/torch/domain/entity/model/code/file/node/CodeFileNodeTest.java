package io.ksmrva.visual.torch.domain.entity.model.code.file.node;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeFileNodeTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeFileNode.class)
                      .verify();
    }

}
