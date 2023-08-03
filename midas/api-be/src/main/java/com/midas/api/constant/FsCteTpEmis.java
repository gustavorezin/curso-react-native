package com.midas.api.constant;

public enum FsCteTpEmis {
	NORMAL("1"), EPEC("4"), CONTINGENCIA_FSDA("5"), AUTORIZACAO_SVCRS("7"), AUTORIZACAO_SVCSP("8");

	private String descricao;

	private FsCteTpEmis(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
