package com.midas.api.constant;

public enum CdBolcfgProtesto {
	DIASCORRIDO("1"), DIAUTIL("2"), NAOPROTESTAR("3"), PROT_FFU("4"), PROT_FFC("5"), NEGAR_SEMPROT("8"),
	CAN_PROT_AUTO("9");

	private String descricao;

	private CdBolcfgProtesto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
