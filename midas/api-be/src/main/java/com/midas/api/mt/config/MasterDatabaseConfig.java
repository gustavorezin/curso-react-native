package com.midas.api.mt.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.TenantRepository;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.midas.api.mt.entity",
		"com.midas.api.mt.repository" }, entityManagerFactoryRef = "masterEntityManagerFactory", transactionManagerRef = "masterTransactionManager")
public class MasterDatabaseConfig {
	// private static final Logger LOG =
	// LoggerFactory.getLogger(MasterDatabaseConfig.class);
	@Autowired
	private MasterDatabaseConfigProperties masterDbProperties;

	// Create Master Data Source using master properties and also configure HikariCP
	@Bean(name = "masterDataSource")
	public DataSource masterDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setUsername(masterDbProperties.getUsername());
		hikariDataSource.setPassword(masterDbProperties.getPassword());
		hikariDataSource.setJdbcUrl(masterDbProperties.getUrl());
		hikariDataSource.setDriverClassName(masterDbProperties.getDriverClassName());
		hikariDataSource.setPoolName(masterDbProperties.getPoolName());
		// HikariCP settings
		hikariDataSource.setMaximumPoolSize(masterDbProperties.getMaxPoolSize());
		hikariDataSource.setMinimumIdle(masterDbProperties.getMinIdle());
		hikariDataSource.setConnectionTimeout(masterDbProperties.getConnectionTimeout());
		hikariDataSource.setIdleTimeout(masterDbProperties.getIdleTimeout());
		// LOG.info("Configuracao da masterDataSource concluida.");
		return hikariDataSource;
	}

	@Primary
	@Bean(name = "masterEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		// Set the master data source
		em.setDataSource(masterDataSource());
		// The master tenant entity and repository need to be scanned
		em.setPackagesToScan(
				new String[] { Tenant.class.getPackage().getName(), TenantRepository.class.getPackage().getName() });
		// Setting a name for the persistence unit as Spring sets it as
		// 'default' if not defined
		em.setPersistenceUnitName("masterdb-persistence-unit");
		// Setting Hibernate as the JPA provider
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		// Set the hibernate properties
		em.setJpaProperties(hibernateProperties());
		// LOG.info("Configuracao da masterEntityManagerFactory concluida.");
		return em;
	}

	@Bean(name = "masterTransactionManager")
	public JpaTransactionManager masterTransactionManager(
			@Qualifier("masterEntityManagerFactory") EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// Hibernate configuration properties
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		properties.put(org.hibernate.cfg.Environment.SHOW_SQL, false);
		properties.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
		properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
		// DEFAULT_BATCH_FETCH_SIZE Ajuda na velocidade. Nao pode ser muito, usa memoria
		properties.put(Environment.DEFAULT_BATCH_FETCH_SIZE, 10);
		return properties;
	}
}
