package com.midas.api.constant;

public enum FsNfseRegimeTrib {
	MICROEMPRESA_MUNICIPAL("1"), ESTIMATIVA("2"), SOC_PROF("3"), COOPERATIVA("4"), MEI("5"), MEEPP("6"), OPT_SN("7"), 
	TRIBUTACAO_NORMAL("8"), AUTONOMO("9"), VARIAVEL("10"), LUCRO_REAL("11"), LUCRO_PRESUMIDO("12"), SOC_PROF_PESSOA_JURIDICA("13");

	private String descricao;

	private FsNfseRegimeTrib(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
