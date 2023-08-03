package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoBoletoAlterarBoletos implements Serializable {
	private static final long serialVersionUID = 1L;
	// Campos basicos
	private String IdIntegracao;
	private String TituloDataVencimento;

	public TecnoBoletoAlterarBoletos() {
	}

	public TecnoBoletoAlterarBoletos(String idIntegracao, String tituloDataVencimento) {
		super();
		IdIntegracao = idIntegracao;
		TituloDataVencimento = tituloDataVencimento;
	}

	public String getIdIntegracao() {
		return IdIntegracao;
	}

	public void setIdIntegracao(String idIntegracao) {
		IdIntegracao = idIntegracao;
	}

	public String getTituloDataVencimento() {
		return TituloDataVencimento;
	}

	public void setTituloDataVencimento(String tituloDataVencimento) {
		TituloDataVencimento = tituloDataVencimento;
	}
}