package com.midas.api.constant;

public enum FsStatus {
	EDICAO("1"), AUTORIZADA("100"), CANCELADA("101"), INUTILIZADA("102"), DENEGADA("110"), AUTORIZADA_FORAPRAZO("150"),
	CANCELADA_FORAPRAZO("151");

	private String descricao;

	private FsStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
