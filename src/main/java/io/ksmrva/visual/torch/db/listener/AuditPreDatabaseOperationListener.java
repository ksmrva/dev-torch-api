package io.ksmrva.visual.torch.db.listener;

import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.event.spi.AbstractPreDatabaseOperationEvent;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreUpdateEvent;

import java.util.Date;

public abstract class AuditPreDatabaseOperationListener {

    private static final Logger LOGGER = LogManager.getLogger(AuditPreDatabaseOperationListener.class);

    private static final String CREATED_UID_ENTITY_FIELD_NAME = "createdUid";

    private static final String CREATED_DATE_ENTITY_FIELD_NAME = "createdDate";

    private static final String MODIFIED_UID_ENTITY_FIELD_NAME = "modifiedUid";

    private static final String MODIFIED_DATE_ENTITY_FIELD_NAME = "modifiedDate";


    protected static void setAuditEntityValues(AbstractBaseEntity baseEntity, String createdUid, Date createdDate, String modifiedUid, Date modifiedDate) {
        baseEntity.setCreatedUid(createdUid);
        baseEntity.setCreatedDate(createdDate);
        baseEntity.setModifiedUid(modifiedUid);
        baseEntity.setModifiedDate(modifiedDate);
    }

    protected static void setPreInsertAuditStateValues(PreInsertEvent event, String createdUid, Date createdDate, String modifiedUid, Date modifiedDate, AbstractBaseEntity baseEntity) {
        setAuditStateValues(event.getState(), event, createdUid, createdDate, modifiedUid, modifiedDate, baseEntity);
    }

    protected static void setPreUpdateAuditStateValues(PreUpdateEvent event, String createdUid, Date createdDate, String modifiedUid, Date modifiedDate, AbstractBaseEntity baseEntity) {
        setAuditStateValues(event.getState(), event, createdUid, createdDate, modifiedUid, modifiedDate, baseEntity);
    }

    protected static void setAuditStateValues(Object[] state, AbstractPreDatabaseOperationEvent event, String createdUid, Date createdDate, String modifiedUid, Date modifiedDate, AbstractBaseEntity baseEntity) {
        String[] propertyNames = event.getPersister().getPropertyNames();
        setAuditStateValue(state, propertyNames, CREATED_UID_ENTITY_FIELD_NAME, createdUid, baseEntity);
        setAuditStateValue(state, propertyNames, CREATED_DATE_ENTITY_FIELD_NAME, createdDate, baseEntity);
        setAuditStateValue(state, propertyNames, MODIFIED_UID_ENTITY_FIELD_NAME, modifiedUid, baseEntity);
        setAuditStateValue(state, propertyNames, MODIFIED_DATE_ENTITY_FIELD_NAME, modifiedDate, baseEntity);
    }

    protected static void setAuditStateValue(Object[] currentState, String[] propertyNames, String propertyToSet, Object valueToSet, AbstractBaseEntity baseEntity) {
        int index = ArrayUtils.indexOf(propertyNames, propertyToSet);
        if (index >= 0) {
            currentState[index] = valueToSet;
        } else {
            LOGGER.warn("Field [" + propertyToSet + "] not found on entity [" + baseEntity.getClass().getName() + "]");
        }
    }

}
