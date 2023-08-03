package com.midas.api.constant;

public enum FsNfseOperacao {
	SEM_DEDUCAO("A"), COM_DEDUCAO_MATERIAIS("B"), IMUNE_ISENTA_ISSQN("C"), DEVOLUCAO("D"), INTERMEDIACAO("J");

	private String descricao;

	private FsNfseOperacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
