package com.midas.api.constant;

public enum PlconNovoCliente {
	INDUSTRIA("01");

	private String descricao;

	private PlconNovoCliente(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
