package com.midas.api.constant;

public enum LcCatDoc {
	VENDA("01"), PEDIDO("02"), ORCAMENTO("03"), CONDICIONAL("04"), LOCACAO("05"), SERVICO("06"), COMODATO("07"),
	FINANCIAMENTO("08"), BONIFICACAO("09"), AMOSTRA("10");

	private String descricao;

	private LcCatDoc(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
