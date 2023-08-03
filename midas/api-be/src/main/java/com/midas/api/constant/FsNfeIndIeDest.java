package com.midas.api.constant;

public enum FsNfeIndIeDest {
	CONTRIBUINTE("1"), ISENTO("2"), NAO_CONTRIBUINTE("9");

	private String descricao;

	private FsNfeIndIeDest(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
