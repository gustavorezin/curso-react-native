package com.midas.api.constant;

public enum CdPeso {
	MILIGRAMA("MG"), GRAMA("G"), QUILO("KG"), TONELADA("TON");

	private String descricao;

	private CdPeso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
