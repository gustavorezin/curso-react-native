package com.midas.api.constant;

public enum FnTipoMov {
	ENTRADA("R"), SAIDA("P"), SAIDATAXA("PC"), TRANSF_ENTRADA("TR"), TRANSF_SAIDA("TP");

	private String descricao;

	private FnTipoMov(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
