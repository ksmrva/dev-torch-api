package io.ksmrva.visual.torch.domain.dto.model.code.source.file;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceFileDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        CodeModelSourceFileDto fileDtoTestA = new CodeModelSourceFileDto();
        fileDtoTestA.setName("fileDtoTestA");

        CodeModelSourceFileDto fileDtoTestB = new CodeModelSourceFileDto();
        fileDtoTestB.setName("fileDtoTestB");

        EqualsVerifier.simple()
                      .forClass(CodeModelSourceFileDto.class)
                      .withPrefabValues(CodeModelSourceFileDto.class, fileDtoTestA, fileDtoTestB)
                      .verify();
    }

}
