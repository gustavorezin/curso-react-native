package com.midas.api.constant;

public enum FsMdfeTpRod {
	TP01("01"), TP02("02"), TP03("03"), TP04("04"), TP05("05"), TP06("06");

	private String descricao;

	private FsMdfeTpRod(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
