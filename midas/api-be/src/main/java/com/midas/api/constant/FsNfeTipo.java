package com.midas.api.constant;

public enum FsNfeTipo {
	ENTRADA("0"), SAIDA("1");

	private String descricao;

	private FsNfeTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
