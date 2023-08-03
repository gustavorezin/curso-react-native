package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;
import java.util.List;

public class TecnoBoletoEmail implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> IdIntegracao;
	private String EmailNomeRemetente;
	private String EmailRemetente;
	private String EmailResponderPara;
	private String EmailAssunto;
	private String EmailMensagem;
	private List<String> EmailDestinatario;
	private boolean EmailAnexarBoleto;
	private boolean EmailConteudoHtml;
	private String TipoImpressao;

	public TecnoBoletoEmail() {
	}

	public TecnoBoletoEmail(List<String> idIntegracao, String emailNomeRemetente, String emailRemetente,
			String emailResponderPara, String emailAssunto, String emailMensagem, List<String> emailDestinatario,
			boolean emailAnexarBoleto, boolean emailConteudoHtml, String tipoImpressao) {
		super();
		IdIntegracao = idIntegracao;
		EmailNomeRemetente = emailNomeRemetente;
		EmailRemetente = emailRemetente;
		EmailResponderPara = emailResponderPara;
		EmailAssunto = emailAssunto;
		EmailMensagem = emailMensagem;
		EmailDestinatario = emailDestinatario;
		EmailAnexarBoleto = emailAnexarBoleto;
		EmailConteudoHtml = emailConteudoHtml;
		TipoImpressao = tipoImpressao;
	}

	public List<String> getIdIntegracao() {
		return IdIntegracao;
	}

	public void setIdIntegracao(List<String> idIntegracao) {
		IdIntegracao = idIntegracao;
	}

	public String getEmailNomeRemetente() {
		return EmailNomeRemetente;
	}

	public void setEmailNomeRemetente(String emailNomeRemetente) {
		EmailNomeRemetente = emailNomeRemetente;
	}

	public String getEmailRemetente() {
		return EmailRemetente;
	}

	public void setEmailRemetente(String emailRemetente) {
		EmailRemetente = emailRemetente;
	}

	public String getEmailResponderPara() {
		return EmailResponderPara;
	}

	public void setEmailResponderPara(String emailResponderPara) {
		EmailResponderPara = emailResponderPara;
	}

	public String getEmailAssunto() {
		return EmailAssunto;
	}

	public void setEmailAssunto(String emailAssunto) {
		EmailAssunto = emailAssunto;
	}

	public String getEmailMensagem() {
		return EmailMensagem;
	}

	public void setEmailMensagem(String emailMensagem) {
		EmailMensagem = emailMensagem;
	}

	public List<String> getEmailDestinatario() {
		return EmailDestinatario;
	}

	public void setEmailDestinatario(List<String> emailDestinatario) {
		EmailDestinatario = emailDestinatario;
	}

	public boolean isEmailAnexarBoleto() {
		return EmailAnexarBoleto;
	}

	public void setEmailAnexarBoleto(boolean emailAnexarBoleto) {
		EmailAnexarBoleto = emailAnexarBoleto;
	}

	public boolean isEmailConteudoHtml() {
		return EmailConteudoHtml;
	}

	public void setEmailConteudoHtml(boolean emailConteudoHtml) {
		EmailConteudoHtml = emailConteudoHtml;
	}

	public String getTipoImpressao() {
		return TipoImpressao;
	}

	public void setTipoImpressao(String tipoImpressao) {
		TipoImpressao = tipoImpressao;
	}
}
