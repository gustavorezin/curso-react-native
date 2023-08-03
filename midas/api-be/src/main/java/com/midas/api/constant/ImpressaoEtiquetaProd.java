package com.midas.api.constant;

public enum ImpressaoEtiquetaProd {
	// EM mm - 35mm e 40mm por exemplo
	e15x25codbar("0074");

	private String descricao;

	private ImpressaoEtiquetaProd(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
