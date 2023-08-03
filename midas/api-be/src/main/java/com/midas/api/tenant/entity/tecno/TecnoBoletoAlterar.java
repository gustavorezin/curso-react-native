package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.List;

public class TecnoBoletoAlterar implements Serializable {
	private static final long serialVersionUID = 1L;
	// Campo basico
	private String Tipo;
	private List<TecnoBoletoAlterarBoletos> Boletos;

	public TecnoBoletoAlterar() {
	}

	public TecnoBoletoAlterar(String tipo, List<TecnoBoletoAlterarBoletos> boletos) {
		super();
		Tipo = tipo;
		Boletos = boletos;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public List<TecnoBoletoAlterarBoletos> getBoletos() {
		return Boletos;
	}

	public void setBoletos(List<TecnoBoletoAlterarBoletos> boletos) {
		Boletos = boletos;
	}
}
