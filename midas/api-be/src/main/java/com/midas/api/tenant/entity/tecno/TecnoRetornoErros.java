package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoRetornoErros implements Serializable {
	private static final long serialVersionUID = 1L;
	private String boleto;

	public TecnoRetornoErros() {
		super();
	}

	public TecnoRetornoErros(String boleto) {
		super();
		this.boleto = boleto;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}
}
