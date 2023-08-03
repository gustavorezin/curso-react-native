package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.List;

public class TecnoRetornoListStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _status;
	private String _mensagem;
	private List<TecnoRetornoDadosList> _dados;

	public TecnoRetornoListStatus() {
		super();
	}

	public TecnoRetornoListStatus(String _status, String _mensagem, List<TecnoRetornoDadosList> _dados) {
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

	public List<TecnoRetornoDadosList> get_dados() {
		return _dados;
	}

	public void set_dados(List<TecnoRetornoDadosList> _dados) {
		this._dados = _dados;
	}
}
