package io.ksmrva.visual.torch.domain.entity.model.database.source.preset;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DatabaseComponentSourcePresetTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(DbModelSourcePreset.class)
                      .verify();
    }

}
