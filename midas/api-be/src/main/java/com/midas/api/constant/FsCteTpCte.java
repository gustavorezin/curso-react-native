package com.midas.api.constant;

public enum FsCteTpCte {
	NORMAL("0"), COMPLEMENTO("1"), ANULACAO("2"), SUBSTITUTO("3");

	private String descricao;

	private FsCteTpCte(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
