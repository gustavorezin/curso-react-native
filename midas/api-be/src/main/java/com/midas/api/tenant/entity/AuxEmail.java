package com.midas.api.tenant.entity;

import java.io.Serializable;

/*
 * 
 * Classe auxiliar apenas para leitura e exposicao de dados ao usuario
 * 
 */
public class AuxEmail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String remetente;
	private String email;
	private String senha;
	private String smtp;
	private String requeraut;
	private String sslsmtp;
	private String portastmp;
	private String tipo;

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getRequeraut() {
		return requeraut;
	}

	public void setRequeraut(String requeraut) {
		this.requeraut = requeraut;
	}

	public String getSslsmtp() {
		return sslsmtp;
	}

	public void setSslsmtp(String sslsmtp) {
		this.sslsmtp = sslsmtp;
	}

	public String getPortastmp() {
		return portastmp;
	}

	public void setPortastmp(String portastmp) {
		this.portastmp = portastmp;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
