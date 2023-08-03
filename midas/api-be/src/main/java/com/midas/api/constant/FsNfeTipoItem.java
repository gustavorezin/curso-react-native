package com.midas.api.constant;

public enum FsNfeTipoItem {
	TI0("00"), TI1("01"), TI2("02"), TI3("03"), TI4("04"), TI5("05"), TI6("06"), TI7("07"), TI8("08"), TI9("09"),
	TI10("10"), TI99("99");

	private String descricao;

	private FsNfeTipoItem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
