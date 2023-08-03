package com.midas.api.constant;

public enum FnFormaPag {
	DINHEIRO("01"), CHEQUE("02"), CCREDITO("03"), CDEBITO("04"), CLOJA("05"), VALIMENTA("10"), VREFEICAO("11"),
	VPRESENTE("12"), VCOMBUSTIVEL("13"), BOLETO("15"), DEPOSITO("16"), PIX("17"), TRANSBANCO("18"), PROGRAMA("19"),
	SEMPAGAMENTO("90"), OUTROS("99");

	private String descricao;

	private FnFormaPag(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
