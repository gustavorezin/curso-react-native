package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoCedente implements Serializable {
	private static final long serialVersionUID = 1L;
	private String CedenteRazaoSocial;
	private String CedenteNomeFantasia;
	private String CedenteCPFCNPJ;
	private String CedenteEnderecoLogradouro;
	private String CedenteEnderecoNumero;
	private String CedenteEnderecoComplemento;
	private String CedenteEnderecoBairro;
	private String CedenteEnderecoCEP;
	private String CedenteEnderecoCidadeIBGE;
	private String CedenteTelefone;
	private String CedenteEmail;

	public TecnoCedente() {
	}

	public TecnoCedente(String cedenteRazaoSocial, String cedenteNomeFantasia, String cedenteCPFCNPJ,
			String cedenteEnderecoLogradouro, String cedenteEnderecoNumero, String cedenteEnderecoComplemento,
			String cedenteEnderecoBairro, String cedenteEnderecoCEP, String cedenteEnderecoCidadeIBGE,
			String cedenteTelefone, String cedenteEmail) {
		super();
		CedenteRazaoSocial = cedenteRazaoSocial;
		CedenteNomeFantasia = cedenteNomeFantasia;
		CedenteCPFCNPJ = cedenteCPFCNPJ;
		CedenteEnderecoLogradouro = cedenteEnderecoLogradouro;
		CedenteEnderecoNumero = cedenteEnderecoNumero;
		CedenteEnderecoComplemento = cedenteEnderecoComplemento;
		CedenteEnderecoBairro = cedenteEnderecoBairro;
		CedenteEnderecoCEP = cedenteEnderecoCEP;
		CedenteEnderecoCidadeIBGE = cedenteEnderecoCidadeIBGE;
		CedenteTelefone = cedenteTelefone;
		CedenteEmail = cedenteEmail;
	}

	public String getCedenteRazaoSocial() {
		return CedenteRazaoSocial;
	}

	public void setCedenteRazaoSocial(String cedenteRazaoSocial) {
		CedenteRazaoSocial = cedenteRazaoSocial;
	}

	public String getCedenteNomeFantasia() {
		return CedenteNomeFantasia;
	}

	public void setCedenteNomeFantasia(String cedenteNomeFantasia) {
		CedenteNomeFantasia = cedenteNomeFantasia;
	}

	public String getCedenteCPFCNPJ() {
		return CedenteCPFCNPJ;
	}

	public void setCedenteCPFCNPJ(String cedenteCPFCNPJ) {
		CedenteCPFCNPJ = cedenteCPFCNPJ;
	}

	public String getCedenteEnderecoLogradouro() {
		return CedenteEnderecoLogradouro;
	}

	public void setCedenteEnderecoLogradouro(String cedenteEnderecoLogradouro) {
		CedenteEnderecoLogradouro = cedenteEnderecoLogradouro;
	}

	public String getCedenteEnderecoNumero() {
		return CedenteEnderecoNumero;
	}

	public void setCedenteEnderecoNumero(String cedenteEnderecoNumero) {
		CedenteEnderecoNumero = cedenteEnderecoNumero;
	}

	public String getCedenteEnderecoComplemento() {
		return CedenteEnderecoComplemento;
	}

	public void setCedenteEnderecoComplemento(String cedenteEnderecoComplemento) {
		CedenteEnderecoComplemento = cedenteEnderecoComplemento;
	}

	public String getCedenteEnderecoBairro() {
		return CedenteEnderecoBairro;
	}

	public void setCedenteEnderecoBairro(String cedenteEnderecoBairro) {
		CedenteEnderecoBairro = cedenteEnderecoBairro;
	}

	public String getCedenteEnderecoCEP() {
		return CedenteEnderecoCEP;
	}

	public void setCedenteEnderecoCEP(String cedenteEnderecoCEP) {
		CedenteEnderecoCEP = cedenteEnderecoCEP;
	}

	public String getCedenteEnderecoCidadeIBGE() {
		return CedenteEnderecoCidadeIBGE;
	}

	public void setCedenteEnderecoCidadeIBGE(String cedenteEnderecoCidadeIBGE) {
		CedenteEnderecoCidadeIBGE = cedenteEnderecoCidadeIBGE;
	}

	public String getCedenteTelefone() {
		return CedenteTelefone;
	}

	public void setCedenteTelefone(String cedenteTelefone) {
		CedenteTelefone = cedenteTelefone;
	}

	public String getCedenteEmail() {
		return CedenteEmail;
	}

	public void setCedenteEmail(String cedenteEmail) {
		CedenteEmail = cedenteEmail;
	}
}
