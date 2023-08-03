package com.midas.api.constant;

public enum ImpressaoReciboTipo {
	PAPEL_COMUM("0"), PAPEL_CUPOM("1"), PAPEL_COMUM_IMAGEM("2");

	private String descricao;

	private ImpressaoReciboTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
