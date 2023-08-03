package com.midas.api.constant;

public enum FsCteTpServ {
	NORMAL("0"), SUBCONTRATACAO("1"), REDESPACHO("2"), REDESPACHO_INTERMEDIARIO("3"), SERVICO_MULTIMODAL("4");

	private String descricao;

	private FsCteTpServ(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
