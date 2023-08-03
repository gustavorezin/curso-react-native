package com.midas.api.constant;

public enum FsNfeIndFinal {
	NAO("0"), CONSUMIDOR_FINAL("1");

	private String descricao;

	private FsNfeIndFinal(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
