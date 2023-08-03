package com.midas.api.util;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.midas.api.mt.config.AutoDDLConfig;
import com.midas.api.mt.entity.Tenant;
import com.zaxxer.hikari.HikariDataSource;

public final class DataSourceUtil {
	@Autowired
	private static final Logger LOG = LoggerFactory.getLogger(DataSourceUtil.class);
	private static final CryptPassUtil cryptPass = new CryptPassUtil();

	public static DataSource createAndConfigureDataSource(Tenant tenant) {
		HikariDataSource ds = new HikariDataSource();
		ds.setUsername(cryptPass.dBase64(tenant.getUsername()));
		ds.setPassword(cryptPass.dBase64(tenant.getPassword()));
		ds.setJdbcUrl(tenant.getUrl());
		ds.setDriverClassName(tenant.getDriverclass());
		// HikariCP settings - could come from the master_tenant table but
		// hardcoded here for brevity
		// Maximum waiting time for a connection from the pool
		ds.setConnectionTimeout(20000);
		// Minimum number of idle connections in the pool
		ds.setMinimumIdle(5);
		// Maximum number of actual connection in the pool
		ds.setMaximumPoolSize(500);
		// Maximum time that a connection is allowed to sit idle in the pool
		ds.setIdleTimeout(300000);
		ds.setConnectionTimeout(20000);
		// Setting up a pool name for each tenant datasource
		String tenantConnectionPoolName = tenant.getDbname() + "-connection-pool";
		ds.setPoolName(tenantConnectionPoolName);
		// Atualiza bancos/tabelas automaticamente
		// TODO HABILITAR SOMENTE QUANDO HOUVER ALTERACAO NAS TABELAS
		AutoDDLConfig.updateDatabases(tenant);
		LOG.info("Banco de dados configurado.....: " + tenant.getDbname() + ". Conexao pool name.....: "
				+ tenantConnectionPoolName);
		return ds;
	}
}
