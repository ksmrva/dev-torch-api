package io.ksmrva.visual.torch.domain.dto.model.database.column;

import io.ksmrva.visual.torch.domain.dto.model.database.component.column.ColumnCategoryDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class ColumnCategoryDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(ColumnCategoryDto.class)
                      .verify();
    }

}
