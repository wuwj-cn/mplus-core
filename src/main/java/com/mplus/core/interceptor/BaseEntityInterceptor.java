package com.mplus.core.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mplus.core.entity.base.BaseEntity;

@Component
public class BaseEntityInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 553299839863417512L;
	private static final Logger logger = LoggerFactory.getLogger(BaseEntityInterceptor.class);

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("onSave interceptor...");
		if(id != null) {
			((BaseEntity) entity).setUpdateAt(new Date());
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	
}
