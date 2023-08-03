package com.midas.api.constant;

public enum FnTecnoTipoImpressao {
	NORMAL("0"), TRIPLO("2"), DUPLA("3");

	private String descricao;

	private FnTecnoTipoImpressao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
