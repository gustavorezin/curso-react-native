package com.midas.api.constant;

public enum LcDocStatus {
	EDICAO("1"), FATURADO("2"), CANCELADO("3");

	private String descricao;

	private LcDocStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
