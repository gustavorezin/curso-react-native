package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoRetornoDados implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _status;
	private String _mensagem;
	private TecnoRetornoDadosConta _dados;

	public TecnoRetornoDados() {
		super();
	}

	public TecnoRetornoDados(String _status) {
		this._status = _status;
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

	public TecnoRetornoDadosConta get_dados() {
		return _dados;
	}

	public void set_dados(TecnoRetornoDadosConta _dados) {
		this._dados = _dados;
	}
}
