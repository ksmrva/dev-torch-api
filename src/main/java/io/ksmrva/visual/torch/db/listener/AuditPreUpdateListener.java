package io.ksmrva.visual.torch.db.listener;

import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuditPreUpdateListener extends AuditPreDatabaseOperationListener implements PreUpdateEventListener {

    private static final Logger LOGGER = LogManager.getLogger(AuditPreUpdateListener.class);

    private static final String UID = "gizmo-update";

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        Object entity = event.getEntity();
        if (entity instanceof AbstractBaseEntity baseEntity) {
            Date now = new Date();

            // Since this is an Update Event, we expect that the Created UID and Date values are already set, but in the rare case that it is not, just use the current UID and Date
            String createdUid = baseEntity.getCreatedUid();
            if (StringUtils.isEmpty(createdUid)) {
                LOGGER.warn("Entity did not have a Created UID value already set on Pre-Update, setting to [" + UID + "]");
                createdUid = UID;
            }
            Date createdDate = baseEntity.getCreatedDate();
            if (createdDate == null) {
                LOGGER.warn("Entity did not have a Created Date value already set on Pre-Update, setting to [" + now + "]");
                createdDate = now;
            }

            // Set the values within the Entity itself, these values will not be used to update the SQL statement but will allow us to pass this data to other Event Listeners
            setAuditEntityValues(baseEntity, createdUid, createdDate, UID, now);

            // Now set the values within the State array, which will update the SQL statement
            setPreUpdateAuditStateValues(event, createdUid, createdDate, UID, now, baseEntity);
        }
        // Returning false tells Hibernate to continue with the Event
        return false;
    }

}
