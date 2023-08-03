package com.midas.api.constant;

public enum FsNfeIpi {
	IPI00("00"), IPI01("01"), IPI02("02"), IPI03("03"), IPI04("04"), IPI05("05"), IPI49("49"), IPI50("50"), IPI51("51"),
	IPI52("52"), IPI53("53"), IPI54("54"), IPI55("55"), IPI99("99");

	private String descricao;

	private FsNfeIpi(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
