package com.midas.api.constant;

public enum FnChequeTipo {
	PROPRIO("01"), TERCEIRO("02");

	private String descricao;

	private FnChequeTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
