package com.midas.api.constant;

public enum FnRecDespTipo {
	RECEITA("RECEITA"), DESPESA("DESPESA");

	private String descricao;

	private FnRecDespTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
