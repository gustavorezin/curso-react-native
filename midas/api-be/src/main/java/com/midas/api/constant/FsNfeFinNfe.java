package com.midas.api.constant;

public enum FsNfeFinNfe {
	NORMAL("1"), COMPLEMENTAR("2"), AJUSTE("3"), DEVOLUCAO("4");

	private String descricao;

	private FsNfeFinNfe(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
