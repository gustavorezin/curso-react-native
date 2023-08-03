package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.List;

public class TecnoRetornoRemessaConsulta implements Serializable {
	private static final long serialVersionUID = 1L;
	// Sucessos-Erros
	private String situacao;
	private String mensagem;
	private String protocolo;
	private List<TecnoRetornoRemessaList> _sucesso;
	private List<TecnoRetornoRemessaList> _falha;

	public TecnoRetornoRemessaConsulta() {
	}

	public TecnoRetornoRemessaConsulta(String situacao, String mensagem, String protocolo,
			List<TecnoRetornoRemessaList> _sucesso, List<TecnoRetornoRemessaList> _falha) {
		super();
		this.situacao = situacao;
		this.mensagem = mensagem;
		this.protocolo = protocolo;
		this._sucesso = _sucesso;
		this._falha = _falha;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public List<TecnoRetornoRemessaList> get_sucesso() {
		return _sucesso;
	}

	public void set_sucesso(List<TecnoRetornoRemessaList> _sucesso) {
		this._sucesso = _sucesso;
	}

	public List<TecnoRetornoRemessaList> get_falha() {
		return _falha;
	}

	public void set_falha(List<TecnoRetornoRemessaList> _falha) {
		this._falha = _falha;
	}
}
