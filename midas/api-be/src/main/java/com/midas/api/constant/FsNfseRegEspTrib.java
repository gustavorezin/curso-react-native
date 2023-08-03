package com.midas.api.constant;

public enum FsNfseRegEspTrib {
	NAO("0"), EMP_ADM_PUBLIC_CONDOM("1"), INSITUICAO_FINANCEIRA("2"), TOMA_INSCRITO_PRODEVA("3");
	
	private String descricao;

	private FsNfseRegEspTrib(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
