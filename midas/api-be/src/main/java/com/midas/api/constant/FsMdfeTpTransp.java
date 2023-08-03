package com.midas.api.constant;

public enum FsMdfeTpTransp {
	TP01("1"), TP02("2"), TP3("3");

	private String descricao;

	private FsMdfeTpTransp(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
