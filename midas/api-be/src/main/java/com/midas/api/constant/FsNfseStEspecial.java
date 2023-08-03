package com.midas.api.constant;

public enum FsNfseStEspecial {
	SUS("1"), ORGAO_PODER_EXEC("2"), BANCOS("3"), COMERCIO_INDUSTRIA("4"), PODER_LEGIS_JUDIC("5"), OUTRO("0");

	private String descricao;

	private FsNfseStEspecial(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
