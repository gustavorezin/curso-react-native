package com.midas.api.constant;

public enum FnFormaPagTroco {
	DINHEIRO("01"), CHEQUE("02"), CLOJA("05"), DEPOSITO("16"), PIX("17"), TRANSBANCO("18"), PROGRAMA("19"),
	SEMPAGAMENTO("90"), OUTROS("99");

	private String descricao;

	private FnFormaPagTroco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
