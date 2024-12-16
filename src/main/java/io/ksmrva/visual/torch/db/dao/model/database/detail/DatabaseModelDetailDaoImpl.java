package io.ksmrva.visual.torch.db.dao.model.database.detail;

import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.collection.CollectionCategoryDto;
import io.ksmrva.visual.torch.domain.dto.model.database.detail.category.field.FieldCategoryDto;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.category.collection.CollectionCategory;
import io.ksmrva.visual.torch.domain.entity.model.database.detail.category.field.FieldCategory;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DatabaseModelDetailDaoImpl implements DatabaseModelDetailDao {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseModelDetailDaoImpl.class);

    public static final String UNDEFINED_CATEGORY_VALUE = "Undefined";


    private final SessionFactory sessionFactory;

    @Autowired
    public DatabaseModelDetailDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create
     **/

    /**
     * Read
     **/
    @Override
    public List<CollectionCategoryDto> getAllCollectionCategories() {
        List<CollectionCategory> collectionCategoriesQueryResult = this.getAllCollectionCategoryEntities();

        return DtoFactory.fromEntities(collectionCategoriesQueryResult);
    }

    @Override
    public List<FieldCategoryDto> getAllFieldCategories() {
        List<FieldCategory> fieldCategoriesQueryResult = this.getAllFieldCategoryEntities();

        return DtoFactory.fromEntities(fieldCategoriesQueryResult);
    }

    public CollectionCategoryDto getUndefinedCollectionCategory() {
        List<CollectionCategory> allCollectionCategories = this.getAllCollectionCategoryEntities();
        Optional<CollectionCategory> collectionCategoryUnknownOptional = allCollectionCategories.stream()
                                                                                                .filter(collectionCategory -> collectionCategory.getName()
                                                                                                                                                .equalsIgnoreCase(UNDEFINED_CATEGORY_VALUE))
                                                                                                .findFirst();
        if (collectionCategoryUnknownOptional.isEmpty()) {
            throw new RuntimeException("Failed to find the Database Collection Category for value [" + UNDEFINED_CATEGORY_VALUE + "]");
        }
        return collectionCategoryUnknownOptional.get()
                                                .convertToDto();
    }

    public FieldCategoryDto getUndefinedFieldCategory() {
        List<FieldCategory> allFieldCategories = this.getAllFieldCategoryEntities();
        Optional<FieldCategory> fieldCategoryUnknownOptional = allFieldCategories.stream()
                                                                                 .filter(fieldCategory -> fieldCategory.getName()
                                                                                                                       .equalsIgnoreCase(UNDEFINED_CATEGORY_VALUE))
                                                                                 .findFirst();
        if (fieldCategoryUnknownOptional.isEmpty()) {
            throw new RuntimeException("Failed to find the Database Field Category for value [" + UNDEFINED_CATEGORY_VALUE + "]");
        }
        return fieldCategoryUnknownOptional.get()
                                           .convertToDto();
    }

    /**
     * Update
     **/

    /** Delete **/

    /**
     * Private
     **/
    private List<CollectionCategory> getAllCollectionCategoryEntities() {
        List<CollectionCategory> collectionCategoriesQueryResult;
        try {
            collectionCategoriesQueryResult = this.sessionFactory.getCurrentSession()
                                                                 .createSelectionQuery("from CollectionCategory", CollectionCategory.class)
                                                                 .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Collection Categories", e);
            collectionCategoriesQueryResult = new ArrayList<>();
        }

        return collectionCategoriesQueryResult;
    }

    private List<FieldCategory> getAllFieldCategoryEntities() {
        List<FieldCategory> fieldCategoriesQueryResult;
        try {
            fieldCategoriesQueryResult = this.sessionFactory.getCurrentSession()
                                                            .createSelectionQuery("from FieldCategory", FieldCategory.class)
                                                            .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Database Field Categories", e);
            fieldCategoriesQueryResult = new ArrayList<>();
        }

        return fieldCategoriesQueryResult;
    }

}
