package io.ksmrva.visual.torch.domain.dto.model.database.table;

import io.ksmrva.visual.torch.domain.dto.model.database.component.table.TableCategoryDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class TableCategoryDtoTest {

    @Test
    public void equalsHashCodeContracts() {
        EqualsVerifier.simple()
                      .forClass(TableCategoryDto.class)
                      .verify();
    }

}
