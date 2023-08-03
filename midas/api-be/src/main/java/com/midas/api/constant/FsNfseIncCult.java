package com.midas.api.constant;

public enum FsNfseIncCult {
	SIM("1"), NAO("2"), OPT_SN_INICIANTE("3"), SERV_PREST_MINHA_CASA_MINHA_VIDA("4");

	private String descricao;

	private FsNfseIncCult(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
