package io.ksmrva.visual.torch.domain.entity.model.code.file.type.directory;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeDirectoryFileTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeDirectoryFile.class)
                      .verify();
    }

}
