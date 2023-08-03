package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoRetornoDadosConta implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String token_cedente;
	private String situacao;
	private String id_software_house;

	public TecnoRetornoDadosConta() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken_cedente() {
		return token_cedente;
	}

	public void setToken_cedente(String token_cedente) {
		this.token_cedente = token_cedente;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getId_software_house() {
		return id_software_house;
	}

	public void setId_software_house(String id_software_house) {
		this.id_software_house = id_software_house;
	}
}
