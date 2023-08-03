package com.midas.api.constant;

public enum FsNfeModbc {
	M0("0"), M1("1"), M2("2"), M3("3");

	private String descricao;

	private FsNfeModbc(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
