package io.ksmrva.visual.torch.domain.entity.model.code.source.file.tree.node;

import io.ksmrva.visual.torch.domain.entity.model.code.source.file.tree.node.CodeModelSourceFileTreeNode;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CodeModelSourceFileTreeNodeTest {

    @Test
    public void equalsHashCodeContracts() {
        /**
         * These prefab values allow the Equals Verifier library to instantiate Objects
         * with a recursive data structures
         *
         * https://jqno.nl/equalsverifier/errormessages/recursive-datastructure/
         */
        CodeModelSourceFileTreeNode parentNodeTestA = new CodeModelSourceFileTreeNode();
        parentNodeTestA.setName("parentNodeTestA");

        CodeModelSourceFileTreeNode parentNodeTestB = new CodeModelSourceFileTreeNode();
        parentNodeTestB.setName("parentNodeTestB");

        EqualsVerifier.simple()
                      .forClass(CodeModelSourceFileTreeNode.class)
                      .withPrefabValues(CodeModelSourceFileTreeNode.class, parentNodeTestA, parentNodeTestB)
                      .verify();
    }

}
