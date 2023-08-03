package com.midas.api.constant;

public enum FsNfeModFrete {
	CIF_CONTA_REMETENTE_0("0"), FOB_CONTA_DESTINATARIO_1("1"), POR_CONTA_TERCEIROS_2("2"),
	PROPRIO_CONTA_REMETENTE_3("3"), PROPRIO_CONTA_DESTINATARIO_4("4"), SEM_FRETE_9("9");

	private String descricao;

	private FsNfeModFrete(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
