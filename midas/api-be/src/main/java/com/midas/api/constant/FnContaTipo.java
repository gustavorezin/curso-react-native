package com.midas.api.constant;

public enum FnContaTipo {
	CORRENTE("CORRENTE"), POUPANCA("POUPANCA");

	private String descricao;

	private FnContaTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
