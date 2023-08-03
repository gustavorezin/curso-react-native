package com.midas.api.constant;

public enum FnCartaoStatus {
	PENDENTE("01"), ENTRADA_CONFIRMADA("02"), RECUSADO("03"), DEVOLVIDO_ESTORNADO("04");

	private String descricao;

	private FnCartaoStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
