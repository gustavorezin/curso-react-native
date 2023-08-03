package com.midas.api.constant;

public enum FsNfseLocalServ {
	MUNICIPIO_SEM_RETENCAO("1"), MUNICIPIO_COM_RETENCAO("2"), FORA_MUNICIPIO_SEM_RETENCAO("3"), FORA_MUNICIPIO_COM_RETENCAO("4"), 
	FORA_MUNICIPIO_PAG_LOCAL("5"), OUTRO_MUNICIPIO("6"), MUNICIPIO_SEM_RETENCAO_SN("7"), MUNICIPIO_TRIB_FIXA_ANUAL("8"), 
	MUNICIPIO_SEM_RECOLHIMENTO("9");

	private String descricao;

	private FsNfseLocalServ(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
