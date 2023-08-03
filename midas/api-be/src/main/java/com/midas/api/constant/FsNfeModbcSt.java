package com.midas.api.constant;

public enum FsNfeModbcSt {
	M0("0"), M1("1"), M2("2"), M3("3"), M4("4"), M5("5");

	private String descricao;

	private FsNfeModbcSt(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
