package com.midas.api.constant;

public enum FsModelo {
	SEMDOC("00"), NFE("55"), CTE("57"), MDFE("58"), NFCE("65"), NFSE("99");

	private String descricao;

	private FsModelo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
