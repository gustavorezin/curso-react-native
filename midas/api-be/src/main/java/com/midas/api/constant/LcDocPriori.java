package com.midas.api.constant;

public enum LcDocPriori {
	BAIXA("1"), MEDIA("2"), ALTA("3");

	private String descricao;

	private LcDocPriori(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
