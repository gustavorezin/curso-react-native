package com.midas.api.constant;

public enum CdEmailTipo {
	GMAIL("GMAIL"), HOTMAIL("HOTMAIL"), OUTROS("OUTROS");

	private String descricao;

	private CdEmailTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
