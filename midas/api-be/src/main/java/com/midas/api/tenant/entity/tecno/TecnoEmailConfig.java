package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoEmailConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	private String smtp;
	private String port;
	private boolean requiretls;

	public TecnoEmailConfig() {
	}

	public TecnoEmailConfig(String smtp, String port, boolean requiretls) {
		super();
		this.smtp = smtp;
		this.port = port;
		this.requiretls = requiretls;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public boolean isRequiretls() {
		return requiretls;
	}

	public void setRequiretls(boolean requiretls) {
		this.requiretls = requiretls;
	}
}
