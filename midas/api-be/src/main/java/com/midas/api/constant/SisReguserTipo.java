package com.midas.api.constant;

public enum SisReguserTipo {
	NOVO("N"), CADASTRAR("C"), ATUALIZAR("A"), REMOVER("R"), LISTAR("L"), IMPRIMIR("I"), FINALIZAR("F"), REABRIR("B"),
	CANCELAR("X"), DUPLICAR("D"), CONSULTA("Y"), EMITIR("E");

	private String descricao;

	private SisReguserTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
