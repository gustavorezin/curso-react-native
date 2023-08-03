package com.midas.api.constant;

public enum FnTipoPR {
	RECEBER("R"), PAGAR("P");

	private String descricao;

	private FnTipoPR(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
