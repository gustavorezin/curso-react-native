package com.midas.api.constant;

public enum FsNfseFin {
	NORMAL("1"), CANCELADA("2"), EXTRAVIADO("3"), CONTINGENCIA("4"), INUTILIZACAO("5");

	private String descricao;

	private FsNfseFin(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
