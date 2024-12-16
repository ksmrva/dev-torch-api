package io.ksmrva.visual.torch.service.model.database.detail;

import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;

import java.util.List;

public interface DatabaseModelDetailService {

    List<CollectionCategoryDto> getAllCollectionCategories();

    List<FieldCategoryDto> getAllFieldCategories();

    CollectionCategoryDto getUndefinedCollectionCategory();

    FieldCategoryDto getUndefinedFieldCategory();

}
