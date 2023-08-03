package com.midas.api.constant;

public enum FsNfseTipoFone {
	CELULAR("CE"), COMERCIAL("CO"), RESIDENCIAL("RE");

	private String descricao;

	private FsNfseTipoFone(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
