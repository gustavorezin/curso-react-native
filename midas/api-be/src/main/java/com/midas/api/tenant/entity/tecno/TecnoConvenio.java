package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoConvenio implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ConvenioNumero;
	private String ConvenioDescricao;
	private String ConvenioCarteira;
	private String ConvenioCarteiraCodigo;
	private String ConvenioEspecie;
	private String ConvenioPadraoCNAB;
	private boolean ConvenioReiniciarDiariamente;
	private String ConvenioNumeroRemessa;
	private Integer Conta;

	public TecnoConvenio() {
	}

	public TecnoConvenio(String convenioNumero, String convenioDescricao, String convenioCarteira,
			String convenioCarteiraCodigo, String convenioEspecie, String convenioPadraoCNAB,
			boolean convenioReiniciarDiariamente, String convenioNumeroRemessa, Integer conta) {
		super();
		ConvenioNumero = convenioNumero;
		ConvenioDescricao = convenioDescricao;
		ConvenioCarteira = convenioCarteira;
		ConvenioCarteiraCodigo = convenioCarteiraCodigo;
		ConvenioEspecie = convenioEspecie;
		ConvenioPadraoCNAB = convenioPadraoCNAB;
		ConvenioReiniciarDiariamente = convenioReiniciarDiariamente;
		ConvenioNumeroRemessa = convenioNumeroRemessa;
		Conta = conta;
	}

	public String getConvenioNumero() {
		return ConvenioNumero;
	}

	public void setConvenioNumero(String convenioNumero) {
		ConvenioNumero = convenioNumero;
	}

	public String getConvenioDescricao() {
		return ConvenioDescricao;
	}

	public void setConvenioDescricao(String convenioDescricao) {
		ConvenioDescricao = convenioDescricao;
	}

	public String getConvenioCarteira() {
		return ConvenioCarteira;
	}

	public void setConvenioCarteira(String convenioCarteira) {
		ConvenioCarteira = convenioCarteira;
	}

	public String getConvenioCarteiraCodigo() {
		return ConvenioCarteiraCodigo;
	}

	public void setConvenioCarteiraCodigo(String convenioCarteiraCodigo) {
		ConvenioCarteiraCodigo = convenioCarteiraCodigo;
	}

	public String getConvenioEspecie() {
		return ConvenioEspecie;
	}

	public void setConvenioEspecie(String convenioEspecie) {
		ConvenioEspecie = convenioEspecie;
	}

	public String getConvenioPadraoCNAB() {
		return ConvenioPadraoCNAB;
	}

	public void setConvenioPadraoCNAB(String convenioPadraoCNAB) {
		ConvenioPadraoCNAB = convenioPadraoCNAB;
	}

	public boolean isConvenioReiniciarDiariamente() {
		return ConvenioReiniciarDiariamente;
	}

	public void setConvenioReiniciarDiariamente(boolean convenioReiniciarDiariamente) {
		ConvenioReiniciarDiariamente = convenioReiniciarDiariamente;
	}

	public String getConvenioNumeroRemessa() {
		return ConvenioNumeroRemessa;
	}

	public void setConvenioNumeroRemessa(String convenioNumeroRemessa) {
		ConvenioNumeroRemessa = convenioNumeroRemessa;
	}

	public Integer getConta() {
		return Conta;
	}

	public void setConta(Integer conta) {
		Conta = conta;
	}
}
