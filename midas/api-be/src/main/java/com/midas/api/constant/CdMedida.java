package com.midas.api.constant;

public enum CdMedida {
	MM("MM"), CM("CM"), M("M");

	private String descricao;

	private CdMedida(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
