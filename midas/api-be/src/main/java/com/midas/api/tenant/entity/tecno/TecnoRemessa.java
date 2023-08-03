package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoRemessa implements Serializable {
	private static final long serialVersionUID = 1L;
	private String remessa;
	private String motivo;
	private String situacao;
	private String arquivo;
	private String criado;
	private boolean vanPendente;

	public TecnoRemessa() {
	}

	public TecnoRemessa(String remessa, String motivo, String situacao, String arquivo, String criado,
			boolean vanPendente) {
		super();
		this.remessa = remessa;
		this.motivo = motivo;
		this.situacao = situacao;
		this.arquivo = arquivo;
		this.criado = criado;
		this.vanPendente = vanPendente;
	}

	public String getRemessa() {
		return remessa;
	}

	public void setRemessa(String remessa) {
		this.remessa = remessa;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getCriado() {
		return criado;
	}

	public void setCriado(String criado) {
		this.criado = criado;
	}

	public boolean isVanPendente() {
		return vanPendente;
	}

	public void setVanPendente(boolean vanPendente) {
		this.vanPendente = vanPendente;
	}
}
