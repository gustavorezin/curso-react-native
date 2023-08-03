package com.midas.api.constant;

public enum FnDiaFixo {
	D1(1), D5(5), D7(7), D10(10), D15(15), D20(20), D25(25);

	private Integer descricao;

	private FnDiaFixo(Integer descricao) {
		this.descricao = descricao;
	}

	public Integer getDescricao() {
		return descricao;
	}

	public void setDescricao(Integer descricao) {
		this.descricao = descricao;
	}
}
