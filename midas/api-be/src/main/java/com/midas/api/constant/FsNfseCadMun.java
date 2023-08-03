package com.midas.api.constant;

public enum FsNfseCadMun {
	CADASTRADO_MUNICIPIO("1"), NAO_CADASTRADO_MUNICIPIO("2"), FORA_MUNICIPIO("3"), CONSUMIDOR_FINAL("4");

	private String descricao;

	private FsNfseCadMun(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
