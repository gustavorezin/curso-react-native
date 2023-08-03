package com.midas.api.constant;

public enum ImpressaoEtiquetaDoc {
	// EM mm - 35mm e 40mm por exemplo
	e35x25("0061"), x40x25("0062");

	private String descricao;

	private ImpressaoEtiquetaDoc(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
