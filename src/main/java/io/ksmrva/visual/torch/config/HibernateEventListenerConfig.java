package io.ksmrva.visual.torch.config;

import io.ksmrva.visual.torch.db.listener.AuditPreInsertListener;
import io.ksmrva.visual.torch.db.listener.AuditPreUpdateListener;
import jakarta.annotation.PostConstruct;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateEventListenerConfig {

    private final LocalSessionFactoryBean localSessionFactoryBean;

    public HibernateEventListenerConfig(LocalSessionFactoryBean localSessionFactoryBean) {
        this.localSessionFactoryBean = localSessionFactoryBean;
    }

    @PostConstruct
    public void registerListeners() {
        SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) localSessionFactoryBean.getObject();
        assert sessionFactoryImpl != null;

        EventListenerRegistry registry = sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);
        assert registry != null;

        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(new AuditPreInsertListener());
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(new AuditPreUpdateListener());
    }

}
