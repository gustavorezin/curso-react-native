package com.midas.api.constant;

public enum FsNfeTpEmis {
	NORMAL("1"), CONTINGENCIA_FSIA("2"), CONTINGENCIA_SCAN("3"), CONTINGENCIA_EPEC("4"), CONTINGENCIA_FSDA("5"),
	CONTINGENCIA_SVCAN("6"), CONTINGENCIA_SVCRS("7"), CONTINGENCIA_OFFNFCE("9");

	private String descricao;

	private FsNfeTpEmis(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
