package io.ksmrva.visual.torch.db.listener;

import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuditPreInsertListener extends AuditPreDatabaseOperationListener implements PreInsertEventListener {

    // TODO: Implement authentication and get UID from Spring Security
    private static final String UID = "gizmo-insert";

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        Object entity = event.getEntity();
        if (entity instanceof AbstractBaseEntity baseEntity) {
            Date now = new Date();

            // Set the values within the Entity itself, these values will not be used to update the SQL statement but will allow us to pass this data to other Event Listeners
            setAuditEntityValues(baseEntity, UID, now, UID, now);

            // Now set the values within the State array, which will update the SQL statement
            setPreInsertAuditStateValues(event, UID, now, UID, now, baseEntity);
        }
        // Returning false tells Hibernate to continue with the Event
        return false;
    }

}
