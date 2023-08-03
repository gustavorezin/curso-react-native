package com.midas.api.constant;

public enum ModuloTipo {
	FISCAL("01"), COMPRAS_ESTOQUE("02"), FINANCEIRO("03"), VENDAS("04");

	private String descricao;

	private ModuloTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
