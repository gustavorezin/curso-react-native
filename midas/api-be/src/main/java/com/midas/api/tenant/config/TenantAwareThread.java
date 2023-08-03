package com.midas.api.tenant.config;

import com.midas.api.mt.config.DBContextHolder;

public class TenantAwareThread extends Thread {
	private String tenant = null;

	public TenantAwareThread(Runnable target) {
		super(target);
		this.tenant = DBContextHolder.getCurrentDb();
	}

	@Override
	public void run() {
		DBContextHolder.setCurrentDb(tenant);
		super.run();
		DBContextHolder.clear();
	}
}
