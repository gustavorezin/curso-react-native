package com.midas.api.constant;

public enum LcCatDocRep {
	VENDA("01"), ORCAMENTO("03");

	private String descricao;

	private LcCatDocRep(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
