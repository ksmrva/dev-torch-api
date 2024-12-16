package io.ksmrva.visual.torch.controller.model.database.detail;

import io.ksmrva.visual.torch.api.arg.constant.DevTorchApiConstants;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import io.ksmrva.visual.torch.service.model.database.detail.DatabaseModelDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(DevTorchApiConstants.DATABASE_MODEL_DETAIL_BASE_URI_PATH)
public class DatabaseModelDetailController {

    private final DatabaseModelDetailService databaseModelDetailService;

    public DatabaseModelDetailController(DatabaseModelDetailService databaseModelDetailService) {
        this.databaseModelDetailService = databaseModelDetailService;
    }

    @GetMapping("/category/collection")
    public @ResponseBody List<CollectionCategoryDto> getAllCollectionCategories() {
        return databaseModelDetailService.getAllCollectionCategories();
    }

    @GetMapping("/category/field")
    public @ResponseBody List<FieldCategoryDto> getAllFieldCategories() {
        return databaseModelDetailService.getAllFieldCategories();
    }

    @GetMapping("/category/collection/unknown")
    public @ResponseBody CollectionCategoryDto getUnknownCollectionCategory() {
        return databaseModelDetailService.getUndefinedCollectionCategory();
    }

    @GetMapping("/category/field/unknown")
    public @ResponseBody FieldCategoryDto getUnknownCategory() {
        return databaseModelDetailService.getUndefinedFieldCategory();
    }

}
