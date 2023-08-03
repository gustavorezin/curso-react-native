package com.midas.api.constant;

public enum FsNfseFpag {
	AVISTA("1"), APRESENTACAO("2"), APRAZO("3"), DEBITO("4"), CREDITO("5");

	private String descricao;

	private FsNfseFpag(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
