package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoRetornoBoletoDados implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idintegracao;
	private String idIntegracao;
	private String cedentecpfcnpj;
	// Ocorrencias
	private String codigo;
	private String mensagem;
	private String criado;
	private String atualizado;

	public TecnoRetornoBoletoDados() {
	}

	public TecnoRetornoBoletoDados(String idintegracao, String idIntegracao2, String cedentecpfcnpj, String codigo,
			String mensagem, String criado, String atualizado) {
		super();
		this.idintegracao = idintegracao;
		idIntegracao = idIntegracao2;
		this.cedentecpfcnpj = cedentecpfcnpj;
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.criado = criado;
		this.atualizado = atualizado;
	}

	public String getIdintegracao() {
		return idintegracao;
	}

	public void setIdintegracao(String idintegracao) {
		this.idintegracao = idintegracao;
	}

	public String getIdIntegracao() {
		return idIntegracao;
	}

	public void setIdIntegracao(String idIntegracao) {
		this.idIntegracao = idIntegracao;
	}

	public String getCedentecpfcnpj() {
		return cedentecpfcnpj;
	}

	public void setCedentecpfcnpj(String cedentecpfcnpj) {
		this.cedentecpfcnpj = cedentecpfcnpj;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCriado() {
		return criado;
	}

	public void setCriado(String criado) {
		this.criado = criado;
	}

	public String getAtualizado() {
		return atualizado;
	}

	public void setAtualizado(String atualizado) {
		this.atualizado = atualizado;
	}
}
