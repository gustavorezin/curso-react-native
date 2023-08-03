package com.midas.api.constant;

public enum TpDoc {
	SEM_DOC("00"), FISCAL("01"), MANUAL("02"), AJUSTE("03"), LCDOC("04"), PRODUCAO("05"), PRODUCAOMT("06"),
	PRODUCAOENCOMENDA("07");

	private String descricao;

	private TpDoc(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
