package com.midas.api.constant;

public enum FsMdfeTpCompPag {
	VALE_PEDAGIO("01"), IMPOSTOS_TAXAS_CONTR("02"), DESPESAS_BANCARIAS_PAG("03"), OUTROS("99");

	private String descricao;

	private FsMdfeTpCompPag(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
