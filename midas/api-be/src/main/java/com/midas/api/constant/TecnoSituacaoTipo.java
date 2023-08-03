package com.midas.api.constant;

public enum TecnoSituacaoTipo {
	SALVO("SALVO"), FALHA("FALHA"), EMITIDO("EMITIDO"), REJEITADO("REJEITADO"), REGISTRADO("REGISTRADO"),
	LIQUIDADO("LIQUIDADO"), BAIXADO("BAIXADO");

	private String descricao;

	private TecnoSituacaoTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
