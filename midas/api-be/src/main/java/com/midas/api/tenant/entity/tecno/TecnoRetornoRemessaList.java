package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.List;

public class TecnoRetornoRemessaList implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _status_http;
	private TecnoRetornoBoletoErros _erro;
	private List<String> idintegracao;
	// Remessa
	private String criado;
	private String arquivo;
	private String situacao;
	private String motivo;
	private String remessa;
	private boolean vanPendente;

	public TecnoRetornoRemessaList() {
	}

	public TecnoRetornoRemessaList(String _status_http, TecnoRetornoBoletoErros _erro, List<String> idintegracao,
			String criado, String arquivo, String situacao, String motivo, String remessa, boolean vanPendente) {
		super();
		this._status_http = _status_http;
		this._erro = _erro;
		this.idintegracao = idintegracao;
		this.criado = criado;
		this.arquivo = arquivo;
		this.situacao = situacao;
		this.motivo = motivo;
		this.remessa = remessa;
		this.vanPendente = vanPendente;
	}

	public String get_status_http() {
		return _status_http;
	}

	public void set_status_http(String _status_http) {
		this._status_http = _status_http;
	}

	public TecnoRetornoBoletoErros get_erro() {
		return _erro;
	}

	public void set_erro(TecnoRetornoBoletoErros _erro) {
		this._erro = _erro;
	}

	public List<String> getIdintegracao() {
		return idintegracao;
	}

	public void setIdintegracao(List<String> idintegracao) {
		this.idintegracao = idintegracao;
	}

	public String getCriado() {
		return criado;
	}

	public void setCriado(String criado) {
		this.criado = criado;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getRemessa() {
		return remessa;
	}

	public void setRemessa(String remessa) {
		this.remessa = remessa;
	}

	public boolean isVanPendente() {
		return vanPendente;
	}

	public void setVanPendente(boolean vanPendente) {
		this.vanPendente = vanPendente;
	}
}
