package com.midas.api.constant;

public enum FsNfseOptSn {
	SIM("1"), NAO("2"), SIMEI("3"), SIMPLES_FEDERAL_ALIQ_1("7"), 
	SIMLES_FEDERAL_ALIQ_05("8"), SIMPLES_MUNICIPAL("9");

	private String descricao;

	private FsNfseOptSn(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
