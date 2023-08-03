package com.midas.api.constant;

public enum FsCteToma {
	REMETENTE("0"), EXPEDIDOR("1"), RECEBEDOR("2"), DESTINATARIO("3"), OUTROS("4");

	private String descricao;

	private FsCteToma(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
