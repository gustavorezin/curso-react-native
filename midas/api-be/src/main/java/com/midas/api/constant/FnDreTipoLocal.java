package com.midas.api.constant;

public enum FnDreTipoLocal {
	NAO_INFO("00"), JURO_ENTRADA("01"), JURO_SAIDA("02"), TROCO_ENTRADA("03"), TROCO_SAIDA("04"), TAXA_CARTAO("05"),
	TAXA_BOLETO("06"), TRANSF_ENTRADA("07"), TRANSF_SAIDA("08"), COMISSAO_DOC("09"), VENDA_PDV("10");

	private String descricao;

	private FnDreTipoLocal(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
