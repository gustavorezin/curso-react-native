package com.midas.api.constant;

public enum FsNfseOrgaoPublico {
	SIM_MUNICIPIO("1"), SIM_FORA_MUNICIPIO("2"), NAO("3");

	private String descricao;

	private FsNfseOrgaoPublico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
