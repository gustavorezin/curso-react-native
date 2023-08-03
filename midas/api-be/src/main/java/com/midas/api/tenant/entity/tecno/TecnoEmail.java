package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoEmail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String servico;
	private String emailremetente;
	private String usuario;
	private String senha;
	private TecnoEmailConfig config;

	public TecnoEmail() {
	}

	public TecnoEmail(String servico, String emailremetente, String usuario, String senha, TecnoEmailConfig config) {
		super();
		this.servico = servico;
		this.emailremetente = emailremetente;
		this.usuario = usuario;
		this.senha = senha;
		this.config = config;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getEmailremetente() {
		return emailremetente;
	}

	public void setEmailremetente(String emailremetente) {
		this.emailremetente = emailremetente;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TecnoEmailConfig getConfig() {
		return config;
	}

	public void setConfig(TecnoEmailConfig config) {
		this.config = config;
	}
}
