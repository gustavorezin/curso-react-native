package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.List;

public class TecnoRetornoBoletoConsulta implements Serializable {
	private static final long serialVersionUID = 1L;
	// Sucessos-Erros
	private String situacao;
	private String mensagem;
	private String protocolo;
	private List<TecnoRetornoBoletoList> _sucesso;
	private List<TecnoRetornoBoletoList> _falha;
	private List<TecnoRetornoBoletoDados> titulos;

	public TecnoRetornoBoletoConsulta() {
	}

	public TecnoRetornoBoletoConsulta(String situacao, String mensagem, String protocolo,
			List<TecnoRetornoBoletoList> _sucesso, List<TecnoRetornoBoletoList> _falha,
			List<TecnoRetornoBoletoDados> titulos) {
		super();
		this.situacao = situacao;
		this.mensagem = mensagem;
		this.protocolo = protocolo;
		this._sucesso = _sucesso;
		this._falha = _falha;
		this.titulos = titulos;
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

	public List<TecnoRetornoBoletoList> get_sucesso() {
		return _sucesso;
	}

	public void set_sucesso(List<TecnoRetornoBoletoList> _sucesso) {
		this._sucesso = _sucesso;
	}

	public List<TecnoRetornoBoletoList> get_falha() {
		return _falha;
	}

	public void set_falha(List<TecnoRetornoBoletoList> _falha) {
		this._falha = _falha;
	}

	public List<TecnoRetornoBoletoDados> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<TecnoRetornoBoletoDados> titulos) {
		this.titulos = titulos;
	}
}
