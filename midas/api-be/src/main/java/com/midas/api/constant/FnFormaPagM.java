package com.midas.api.constant;

//Movimento caixa
public enum FnFormaPagM {
	DINHEIRO("01"), CCREDITO("03"), CDEBITO("04"), VALIMENTA("10"), VREFEICAO("11"), VPRESENTE("12"),
	VCOMBUSTIVEL("13"), DEPOSITO("16"), PIX("17"), TRANSBANCO("18"), PROGRAMA("19"), SEMPAGAMENTO("90"), OUTROS("99");

	private String descricao;

	private FnFormaPagM(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
