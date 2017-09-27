package com.mplus.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.mplus.core.interceptor.BaseEntityInterceptor;

@Configuration
public class HibernateConfig extends HibernateJpaAutoConfiguration {

	public HibernateConfig(DataSource dataSource, JpaProperties jpaProperties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager,
			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, jpaProperties, jtaTransactionManager, transactionManagerCustomizers);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	BaseEntityInterceptor baseEntityInterceptor;

	@Override
	protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
		vendorProperties.put("hibernate.ejb.interceptor", baseEntityInterceptor);
	}
}
