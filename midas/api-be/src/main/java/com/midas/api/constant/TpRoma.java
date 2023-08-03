package com.midas.api.constant;

public enum TpRoma {
	ROMANEIO("01");

	private String descricao;

	private TpRoma(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
