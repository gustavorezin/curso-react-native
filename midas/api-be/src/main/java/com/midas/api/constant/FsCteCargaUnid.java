package com.midas.api.constant;

public enum FsCteCargaUnid {
	M3("00"), KG("01"), TON("02"), UNIDADE("03"), LITROS("04"), MMBTU("05");

	private String descricao;

	private FsCteCargaUnid(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
