package io.ksmrva.visual.torch.domain.dto.model.code.file.type.directory;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeDirectoryFileDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeDirectoryFileDto.class)
                      .verify();
    }

}
