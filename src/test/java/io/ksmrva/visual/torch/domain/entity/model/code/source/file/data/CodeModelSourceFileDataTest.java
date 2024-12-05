package io.ksmrva.visual.torch.domain.entity.model.code.source.file.data;

import io.ksmrva.visual.torch.domain.entity.model.code.source.file.CodeModelSourceFile;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceFileDataTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        CodeModelSourceFile fileTestA = new CodeModelSourceFile();
        fileTestA.setName("fileTestA");

        CodeModelSourceFile fileTestB = new CodeModelSourceFile();
        fileTestB.setName("fileTestB");

        EqualsVerifier.simple()
                      .forClass(CodeModelSourceFileData.class)
                      .withPrefabValues(CodeModelSourceFile.class, fileTestA, fileTestB)
                      .verify();
    }

}
