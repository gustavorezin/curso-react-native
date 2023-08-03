package com.midas.api.constant;

public enum LcTpDoc {
	SEMDOC("00"), VENDA("01"), OS("02"), DEVOLUCAO("03"), DEVOLUCAOCOMPRA("04"), COMISSAO("05"), CONSUMOINTERNO("06"),
	COTACAO("07"), COMPRA("08"), REMESSA("09");

	private String descricao;

	private LcTpDoc(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
