package com.midas.api.dto;

import java.io.Serializable;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long tenantid;
	private String username;
	private String password;

	public ClienteDTO() {
	}

	public ClienteDTO(Long tenantid, String username, String password) {
		this.tenantid = tenantid;
		this.username = username;
		this.password = password;
	}

	public Long getTenantid() {
		return tenantid;
	}

	public void setTenantid(Long tenantid) {
		this.tenantid = tenantid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
