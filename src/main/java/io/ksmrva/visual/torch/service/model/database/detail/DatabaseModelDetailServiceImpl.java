package io.ksmrva.visual.torch.service.model.database.detail;

import io.ksmrva.visual.torch.db.dao.model.database.detail.DatabaseModelDetailDao;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseModelDetailServiceImpl implements DatabaseModelDetailService {

    private final DatabaseModelDetailDao databaseModelDetailDao;

    public DatabaseModelDetailServiceImpl(DatabaseModelDetailDao databaseModelDetailDao) {
        this.databaseModelDetailDao = databaseModelDetailDao;
    }

    @Override
    public List<CollectionCategoryDto> getAllCollectionCategories() {
        return databaseModelDetailDao.getAllCollectionCategories();
    }

    @Override
    public List<FieldCategoryDto> getAllFieldCategories() {
        return databaseModelDetailDao.getAllFieldCategories();
    }

    @Override
    public CollectionCategoryDto getUndefinedCollectionCategory() {
        return databaseModelDetailDao.getUndefinedCollectionCategory();
    }

    @Override
    public FieldCategoryDto getUndefinedFieldCategory() {
        return databaseModelDetailDao.getUndefinedFieldCategory();
    }

}
