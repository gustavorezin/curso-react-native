package com.midas.api.constant;

public enum FsMdfeModal {
	RODOVIARIO("1"), AEREO("2"), AQUAVIARIO("3"), FERROVIARIO("4");

	private String descricao;

	private FsMdfeModal(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
