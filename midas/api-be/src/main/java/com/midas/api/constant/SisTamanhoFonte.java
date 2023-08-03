package com.midas.api.constant;

public enum SisTamanhoFonte {
	CINCO("5"), SEIS("6"), SETE("7");

	private String descricao;

	private SisTamanhoFonte(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
