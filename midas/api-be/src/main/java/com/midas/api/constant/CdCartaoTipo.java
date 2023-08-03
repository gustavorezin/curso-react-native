package com.midas.api.constant;

public enum CdCartaoTipo {
	CREDITO("03"), DEBITO("04"), VALE_ALIMENTACAO("10"), VALE_REFEICAO("11"), VALE_PRESENTE("12"),
	VALE_COMBUSTIVEL("13");

	private String descricao;

	private CdCartaoTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
