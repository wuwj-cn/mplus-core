package com.mplus.core.listener;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;

@Deprecated
public class HibernateEvent {

//	@Autowired
	private SessionFactory sessionFactory;
//	@Autowired
	private SaveOrUpdateListener listener;

//	@PostConstruct
	public void registerListeners() {
		EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry()
				.getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.SAVE_UPDATE).appendListener(listener);
	}
}
