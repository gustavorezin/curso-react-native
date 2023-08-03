package com.midas.api.tenant.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.midas.api.mt.config.DBContextHolder;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
	private static final String DEFAULT_TENANT_ID = "";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenant = DBContextHolder.getCurrentDb();
		return StringUtils.isNotBlank(tenant) ? tenant : DEFAULT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
