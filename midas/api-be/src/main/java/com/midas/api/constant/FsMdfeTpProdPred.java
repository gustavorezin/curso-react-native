package com.midas.api.constant;

public enum FsMdfeTpProdPred {
	GRANEL_SOLIDO("01"), GRANEL_LIQUIDO("02"), FRIGORIFICADA("03"), CONTEINERIZADA("04"), CARGA_GERAL("05"), 
	NEOGRANEL("06"), PERIGOSA_GRANEL_SOLIDO("07"), PERIGOSA_GRANEL_LIQUIDO("08"), PERIGOSA_CARGA_FRIGORIFICADA("09"), 
	PERIGOSA_CONTEINERIZADA("10"), PERIGOSA_CARGA_GERAL("11");

	private String descricao;

	private FsMdfeTpProdPred(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
