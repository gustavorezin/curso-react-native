package com.midas.api.constant;

public enum SisModuloTipo {
	COMPLETO("COMPLETO"), COMPRAS("COMPRAS"), EXPEDICAO("EXPEDICAO"), FINANCEIRO("FINANCEIRO"), FISCAL("FISCAL"),
	PRODUCAO("PRODUCAO"), VENDAS("VENDAS"), REPRESENTANTES("REPRESENTANTES"), OS("OS"), PDV("PDV");

	private String descricao;

	private SisModuloTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
