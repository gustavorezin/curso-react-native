package com.midas.api.constant;

public enum FsNfeManConsSt {
	CONFIRMA_OPERACAO("210200"), CIENCIA_OPERACAO("210210"), DESCONHECE_OPERACAO("210220"),
	OPERACAO_NAO_REALIZADA("210240");

	private String descricao;

	private FsNfeManConsSt(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
