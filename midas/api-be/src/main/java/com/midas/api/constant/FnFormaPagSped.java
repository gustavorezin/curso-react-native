package com.midas.api.constant;

public enum FnFormaPagSped {
	VALIMENTA("10"), VREFEICAO("11"), VPRESENTE("12"), VCOMBUSTIVEL("13"), BOLETO("15"), DEPOSITO("16"), PIX("17"),
	TRANSBANCO("18"), PROGRAMA("19");

	private String descricao;

	private FnFormaPagSped(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
