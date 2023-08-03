package com.midas.api.constant;

public enum LcDocEntregaStatus {
	AGUARDAENVIO("0"), CONFIRMADO("1"), PRODUCAO("2"), EMSEPARACAO("3"), POSTADO("4"), EMTRANSITO("5"), ENTREGUE("6"),
	DEVOLVIDO("7");

	private String descricao;

	private LcDocEntregaStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
