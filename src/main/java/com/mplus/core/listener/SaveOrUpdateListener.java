package com.mplus.core.listener;

import org.hibernate.event.internal.DefaultSaveOrUpdateEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SaveOrUpdateListener extends DefaultSaveOrUpdateEventListener {
	private static final long serialVersionUID = -7740091215891885274L;
	private static final Logger logger = LoggerFactory.getLogger(SaveOrUpdateListener.class);
	
	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) {
		logger.info("onSaveOrUpdate Listener...");
		
		
		super.onSaveOrUpdate(event);
	}
}
