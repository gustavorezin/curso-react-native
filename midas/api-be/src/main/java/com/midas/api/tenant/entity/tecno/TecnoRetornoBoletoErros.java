package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoRetornoBoletoErros implements Serializable {
	private static final long serialVersionUID = 1L;
	private TecnoRetornoErros erros;

	public TecnoRetornoBoletoErros(TecnoRetornoErros erros) {
		super();
		this.erros = erros;
	}

	public TecnoRetornoErros getErros() {
		return erros;
	}

	public void setErros(TecnoRetornoErros erros) {
		this.erros = erros;
	}
}
