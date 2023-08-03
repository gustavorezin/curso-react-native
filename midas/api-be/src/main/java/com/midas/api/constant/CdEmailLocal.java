package com.midas.api.constant;

public enum CdEmailLocal {
	CADASTRO("01"), FISCAL("02"), LANCAMENTOS("03"), FINANCEIRO("04"), COMPRAS("05");

	private String descricao;

	private CdEmailLocal(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
