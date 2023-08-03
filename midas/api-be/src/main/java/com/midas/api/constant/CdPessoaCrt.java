package com.midas.api.constant;

public enum CdPessoaCrt {
	SIMPLES(1), PRESUMIDO(2), REAL(3);

	private Integer descricao;

	private CdPessoaCrt(Integer descricao) {
		this.descricao = descricao;
	}

	public Integer getDescricao() {
		return descricao;
	}

	public void setDescricao(Integer descricao) {
		this.descricao = descricao;
	}
}
