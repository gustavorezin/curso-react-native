package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoRetornoBoleto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _status;
	private String _mensagem;
	private TecnoRetornoBoletoConsulta _dados;

	public TecnoRetornoBoleto() {
		super();
	}

	public TecnoRetornoBoleto(String _status, String _mensagem, TecnoRetornoBoletoConsulta _dados) {
		super();
		this._status = _status;
		this._mensagem = _mensagem;
		this._dados = _dados;
	}

	public String get_status() {
		return _status;
	}

	public void set_status(String _status) {
		this._status = _status;
	}

	public String get_mensagem() {
		return _mensagem;
	}

	public void set_mensagem(String _mensagem) {
		this._mensagem = _mensagem;
	}

	public TecnoRetornoBoletoConsulta get_dados() {
		return _dados;
	}

	public void set_dados(TecnoRetornoBoletoConsulta _dados) {
		this._dados = _dados;
	}
}
