package com.midas.api.constant;

public enum FsMdfeTpProp {
	TP00("0"), TP01("1"), TP2("2");

	private String descricao;

	private FsMdfeTpProp(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
