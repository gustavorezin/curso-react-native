package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.List;

public class TecnoRetornoBoletoList implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _status_http;
	private TecnoRetornoBoletoErros _erro;
	// Boleto
	private String idintegracao;
	private String TituloNumeroDocumento;
	private String protocolo;
	private String TituloNossoNumero;
	private String CedenteContaCodigoBanco;
	private String CedenteContaNumero;
	private String CedenteConvenioNumero;
	// Remessa
	private String uniqueId;
	private String arquivo;
	private String situacao;
	private String motivo;
	private String remessa;
	private boolean transmissaoAutomatica;
	private List<TecnoRetornoBoletoDados> titulos;

	public TecnoRetornoBoletoList() {
	}

	public TecnoRetornoBoletoList(String _status_http, TecnoRetornoBoletoErros _erro, String idintegracao,
			String tituloNumeroDocumento, String protocolo, String tituloNossoNumero, String cedenteContaCodigoBanco,
			String cedenteContaNumero, String cedenteConvenioNumero, String uniqueId, String arquivo, String situacao,
			String motivo, String remessa, boolean transmissaoAutomatica, List<TecnoRetornoBoletoDados> titulos) {
		super();
		this._status_http = _status_http;
		this._erro = _erro;
		this.idintegracao = idintegracao;
		TituloNumeroDocumento = tituloNumeroDocumento;
		this.protocolo = protocolo;
		TituloNossoNumero = tituloNossoNumero;
		CedenteContaCodigoBanco = cedenteContaCodigoBanco;
		CedenteContaNumero = cedenteContaNumero;
		CedenteConvenioNumero = cedenteConvenioNumero;
		this.uniqueId = uniqueId;
		this.arquivo = arquivo;
		this.situacao = situacao;
		this.motivo = motivo;
		this.remessa = remessa;
		this.transmissaoAutomatica = transmissaoAutomatica;
		this.titulos = titulos;
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

	public String getIdintegracao() {
		return idintegracao;
	}

	public void setIdintegracao(String idintegracao) {
		this.idintegracao = idintegracao;
	}

	public String getTituloNumeroDocumento() {
		return TituloNumeroDocumento;
	}

	public void setTituloNumeroDocumento(String tituloNumeroDocumento) {
		TituloNumeroDocumento = tituloNumeroDocumento;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getTituloNossoNumero() {
		return TituloNossoNumero;
	}

	public void setTituloNossoNumero(String tituloNossoNumero) {
		TituloNossoNumero = tituloNossoNumero;
	}

	public String getCedenteContaCodigoBanco() {
		return CedenteContaCodigoBanco;
	}

	public void setCedenteContaCodigoBanco(String cedenteContaCodigoBanco) {
		CedenteContaCodigoBanco = cedenteContaCodigoBanco;
	}

	public String getCedenteContaNumero() {
		return CedenteContaNumero;
	}

	public void setCedenteContaNumero(String cedenteContaNumero) {
		CedenteContaNumero = cedenteContaNumero;
	}

	public String getCedenteConvenioNumero() {
		return CedenteConvenioNumero;
	}

	public void setCedenteConvenioNumero(String cedenteConvenioNumero) {
		CedenteConvenioNumero = cedenteConvenioNumero;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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

	public boolean isTransmissaoAutomatica() {
		return transmissaoAutomatica;
	}

	public void setTransmissaoAutomatica(boolean transmissaoAutomatica) {
		this.transmissaoAutomatica = transmissaoAutomatica;
	}

	public List<TecnoRetornoBoletoDados> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<TecnoRetornoBoletoDados> titulos) {
		this.titulos = titulos;
	}
}
