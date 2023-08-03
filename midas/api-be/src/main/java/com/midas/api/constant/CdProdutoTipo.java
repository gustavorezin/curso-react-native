package com.midas.api.constant;

public enum CdProdutoTipo {
	PRODUTO("PRODUTO"), SERVICO("SERVIÇO");

	private String descricao;

	private CdProdutoTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
