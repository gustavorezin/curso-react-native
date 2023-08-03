package com.midas.api.constant;

public enum CdPessoaTipo {
	CLIENTE("CLIENTE"), COLABORADOR("COLABORADOR"), CONTADOR("CONTADOR"), EMPRESA("EMPRESA"), FORNECEDOR("FORNECEDOR"), 
	MOTORISTA("MOTORISTA"), PARCEIRO("PARCEIRO"), SEGURADORA("SEGURADORA"), TECNICO("TECNICO"), TRANSPORTADORA("TRANSPORTADORA"), VENDEDOR("VENDEDOR");

	private String descricao;

	private CdPessoaTipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
