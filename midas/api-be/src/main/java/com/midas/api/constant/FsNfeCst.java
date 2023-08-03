package com.midas.api.constant;

public enum FsNfeCst {
	C00("00"), C10("10"), C20("20"), C30("30"), C40("40"), C41("41"), C50("50"), C51("51"), C60("60"), C70("70"),
	C90("90"), C101("101"), C102("102"), C103("103"), C201("201"), C1202("202"), C203("203"), C300("300"), C400("400"),
	C500("500"), C900("900");

	private String descricao;

	private FsNfeCst(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
