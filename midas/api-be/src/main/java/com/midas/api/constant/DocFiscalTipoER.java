package com.midas.api.constant;

public enum DocFiscalTipoER {
	EMITIDO("E"), RECEBIDO("R");

	private String descricao;

	private DocFiscalTipoER(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
