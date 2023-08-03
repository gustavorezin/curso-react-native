package com.midas.api.constant;

public enum FsNfeIdDest {
	OPERACAO_INTERNA("1"), OPERACAO_INTERESTADUAL("2"), OPERACAO_EXTERIOR("3");

	private String descricao;

	private FsNfeIdDest(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
