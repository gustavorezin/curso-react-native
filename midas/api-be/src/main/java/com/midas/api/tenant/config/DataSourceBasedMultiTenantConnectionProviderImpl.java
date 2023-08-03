package com.midas.api.tenant.config;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.midas.api.mt.config.DBContextHolder;
import com.midas.api.mt.entity.Tenant;
import com.midas.api.mt.repository.TenantRepository;
import com.midas.api.util.DataSourceUtil;

@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl
		extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
	// private static final Logger LOG =
	// LoggerFactory.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);
	private static final long serialVersionUID = 1L;
	private Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();
	@Autowired
	private TenantRepository tenantRp;
	@Autowired
	ApplicationContext applicationContext;

	@Override
	protected DataSource selectAnyDataSource() {
		// This method is called more than once. So check if the data source map
		// is empty. If it is then rescan master_tenant table for all tenant
		if (dataSourcesMtApp.isEmpty()) {
			List<Tenant> tenants = tenantRp.findAll();
			// LOG.info("selectAnyDataSource() metodo chamado...Total de tenants
			// instalados:" + tenants.size());
			for (Tenant tn : tenants) {
				dataSourcesMtApp.put(tn.getDbname(), DataSourceUtil.createAndConfigureDataSource(tn));
			}
		}
		return this.dataSourcesMtApp.values().iterator().next();
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		// If the requested tenant id is not present check for it in the master
		// database 'master_tenant' table
		tenantIdentifier = initializeTenantIfLost(tenantIdentifier);
		if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
			List<Tenant> tenants = tenantRp.findAll();
			/*
			 * LOG.info("selectDataSource() metodo chamado...CLIENTE:" + tenantIdentifier +
			 * " Total de tenants instalados:" + tenants.size());
			 */
			for (Tenant tn : tenants) {
				dataSourcesMtApp.put(tn.getDbname(), DataSourceUtil.createAndConfigureDataSource(tn));
			}
		}
		// check again if tenant exist in map after rescan master_db, if not, throw
		// UsernameNotFoundException
		if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
			// LOG.warn("Trying to get tenant:" + tenantIdentifier + " which was not found
			// in master db after rescan");
			throw new UsernameNotFoundException(
					String.format("CLIENTE nao encontrato, " + " tenant=%s", tenantIdentifier));
		}
		return this.dataSourcesMtApp.get(tenantIdentifier);
	}

	private String initializeTenantIfLost(String tenantIdentifier) {
		if (tenantIdentifier != DBContextHolder.getCurrentDb()) {
			tenantIdentifier = DBContextHolder.getCurrentDb();
		}
		return tenantIdentifier;
	}
}
