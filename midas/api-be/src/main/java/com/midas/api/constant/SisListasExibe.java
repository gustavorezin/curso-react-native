package com.midas.api.constant;

public enum SisListasExibe {
	Exibir5(5), Exibir10(10), Exibir15(15), Exibir25(25), Exibir50(50), Exibir100(100), Exibir250(250), Exibir500(500);

	private int id;

	SisListasExibe(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
