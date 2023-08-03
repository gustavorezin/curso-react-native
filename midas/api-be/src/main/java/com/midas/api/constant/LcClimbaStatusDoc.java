package com.midas.api.constant;

public enum LcClimbaStatusDoc {
	AGUARDA_PAG("1"), PAG_CONFIRMADO("2"), ENVIADO("3"), CANCELADO("6"), PRONTO_ENVIO("8"), PAG_AUTORIZADO("9"),
	SEPARACAO("10");

	private String descricao;

	private LcClimbaStatusDoc(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
