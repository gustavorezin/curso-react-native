package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TecnoBoletoImpressao implements Serializable {
	private static final long serialVersionUID = 1L;
	private String TipoImpressao;
	private List<String> Boletos = new ArrayList<String>();

	public TecnoBoletoImpressao() {
	}

	public TecnoBoletoImpressao(String tipoImpressao, List<String> boletos) {
		super();
		TipoImpressao = tipoImpressao;
		Boletos = boletos;
	}

	public String getTipoImpressao() {
		return TipoImpressao;
	}

	public void setTipoImpressao(String tipoImpressao) {
		TipoImpressao = tipoImpressao;
	}

	public List<String> getBoletos() {
		return Boletos;
	}

	public void setBoletos(List<String> boletos) {
		Boletos = boletos;
	}
}
