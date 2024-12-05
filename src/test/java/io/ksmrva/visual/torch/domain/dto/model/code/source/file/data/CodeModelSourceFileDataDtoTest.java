package io.ksmrva.visual.torch.domain.dto.model.code.source.file.data;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceFileDataDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(CodeModelSourceFileDataDto.class)
                      .verify();
    }

}
