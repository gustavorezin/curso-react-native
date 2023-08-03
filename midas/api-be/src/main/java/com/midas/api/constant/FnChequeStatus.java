package com.midas.api.constant;

public enum FnChequeStatus {
	EMCAIXA("01"), DEPOSITADO("02"), PGTOFORNECEDOR("03"), COMPENSADO("04"), TROCO("05"), RETORNADO("06"),
	CANCELADO("07");

	private String descricao;

	private FnChequeStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
