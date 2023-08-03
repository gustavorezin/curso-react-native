package com.midas.api.constant;

public enum FnChequeStatusTr {
	EMCAIXA("01"), DEPOSITADO("02");

	private String descricao;

	private FnChequeStatusTr(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
