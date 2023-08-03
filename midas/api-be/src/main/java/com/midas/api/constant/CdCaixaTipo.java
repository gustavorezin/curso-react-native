package com.midas.api.constant;

public enum CdCaixaTipo {
	BALCAO("01"), BANCO("02"), COFRE("03"), INVESTIMENTO("04");

	private String descricao;

	private CdCaixaTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
