package com.midas.api.constant;

public enum FsNfeIndPres {
	NAO_SE_APLICA("0"), OPERACAO_PRESENCIAL("1"), OPERACAO_NAO_PRESENCIAL_INTERNET("2"),
	OPERACAO_NAO_PRESENCIAL_TELE("3"), NFCE_ENTREGA("4"), OPERACAO_PRESENCIAL_FORA("5"), NAO_PRESENCIAL_OUTROS("9");

	private String descricao;

	private FsNfeIndPres(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
