package com.midas.api.constant;

public enum FsCteModal {
	RODOVIARIO("01")/*
					 * , AEREO("02"), AQUAVIARIO("03"), FERROVIARIO("04"), DUTOVIARIO("05"),
					 * MULTIMODAL("06")
					 */;

	private String descricao;

	private FsCteModal(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
