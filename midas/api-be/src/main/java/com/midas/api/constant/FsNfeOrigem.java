package com.midas.api.constant;

public enum FsNfeOrigem {
	OR0("0"), OR1("1"), OR2("2"), OR3("3"), OR4("4"), OR5("5"), OR6("6"), OR7("7"), OR8("8");

	private String descricao;

	private FsNfeOrigem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
