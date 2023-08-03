package com.midas.api.mt.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.midas.api.mt.entity.Tenant;
import com.midas.api.util.CryptPassUtil;
import com.midas.api.util.DataSourceUtil;

public class AutoDDLConfig {
	@Autowired
	private static final Logger LOG = LoggerFactory.getLogger(DataSourceUtil.class);
	private static final CryptPassUtil cryptPass = new CryptPassUtil();

	public static void updateDatabases(Tenant tenant) {
		try {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(tenant.getDriverclass());
			dataSource.setSchema(tenant.getDbname());
			dataSource.setUrl(tenant.getUrl());
			dataSource.setUsername(cryptPass.dBase64(tenant.getUsername()));
			dataSource.setPassword(cryptPass.dBase64(tenant.getPassword()));
			LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
			emfBean.setDataSource(dataSource);
			emfBean.setPackagesToScan("com.midas.api.tenant.entity"); // Entity classes
			emfBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
			emfBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
			Map<String, Object> properties = new HashMap<>();
			properties.put("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.default_schema", tenant);
			properties.put("spring.datasource.initialize", "false");
			emfBean.setJpaPropertyMap(properties);
			emfBean.setPersistenceUnitName(dataSource.toString());
			emfBean.afterPropertiesSet();
			LOG.info("Atualizacao de Banco de dados realizado para: " + tenant.getDbname());
		} catch (PersistenceException e) {
			LOG.error("Exception: Erro na atualizacao do Banco de dados : {}", e.getMessage());
			e.printStackTrace();
		}
	}
}
