package com.midas.api.constant;

public enum FsNfePisCofins {
	PC01("01"), PC02("02"), PC03("03"), PC04("04"), PC05("05"), PC06("06"), PC07("07"), PC08("08"), PC09("09"),
	PC49("49"), PC50("50"), PC51("51"), PC52("52"), PC53("53"), PC54("54"), PC55("55"), PC56("56"), PC60("60"),
	PC61("61"), PC62("62"), PC63("63"), PC64("64"), PC65("65"), PC66("66"), PC67("67"), PC70("70"), PC71("71"),
	PC72("72"), PC73("73"), PC74("74"), PC75("75"), PC98("98"), PC99("99");

	private String descricao;

	private FsNfePisCofins(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
