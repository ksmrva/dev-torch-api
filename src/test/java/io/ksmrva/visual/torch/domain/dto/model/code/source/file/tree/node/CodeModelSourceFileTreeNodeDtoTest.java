package io.ksmrva.visual.torch.domain.dto.model.code.source.file.tree.node;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceFileTreeNodeDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelSourceFileTreeNodeDto.class)
                      .verify();
    }

}
