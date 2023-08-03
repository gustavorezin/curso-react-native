package com.midas.api.tenant.entity.tecno;

import java.io.Serializable;

public class TecnoRetornoDadosConvenioList implements Serializable {
	private static final long serialVersionUID = 1L;
	private String numero_convenio;
	private String descricao_convenio;
	private String carteira;
	private String especie;
	private String id_conta;
	private String padraoCNAB;
	private boolean utiliza_van;
	private boolean reiniciar_diariamente;
	private String numero_remessa;

	public TecnoRetornoDadosConvenioList() {
		super();
	}

	public TecnoRetornoDadosConvenioList(String numero_convenio, String descricao_convenio, String carteira,
			String especie, String id_conta, String padraoCNAB, boolean utiliza_van, boolean reiniciar_diariamente,
			String numero_remessa) {
		super();
		this.numero_convenio = numero_convenio;
		this.descricao_convenio = descricao_convenio;
		this.carteira = carteira;
		this.especie = especie;
		this.id_conta = id_conta;
		this.padraoCNAB = padraoCNAB;
		this.utiliza_van = utiliza_van;
		this.reiniciar_diariamente = reiniciar_diariamente;
		this.numero_remessa = numero_remessa;
	}

	public String getNumero_convenio() {
		return numero_convenio;
	}

	public void setNumero_convenio(String numero_convenio) {
		this.numero_convenio = numero_convenio;
	}

	public String getDescricao_convenio() {
		return descricao_convenio;
	}

	public void setDescricao_convenio(String descricao_convenio) {
		this.descricao_convenio = descricao_convenio;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getId_conta() {
		return id_conta;
	}

	public void setId_conta(String id_conta) {
		this.id_conta = id_conta;
	}

	public String getPadraoCNAB() {
		return padraoCNAB;
	}

	public void setPadraoCNAB(String padraoCNAB) {
		this.padraoCNAB = padraoCNAB;
	}

	public boolean isUtiliza_van() {
		return utiliza_van;
	}

	public void setUtiliza_van(boolean utiliza_van) {
		this.utiliza_van = utiliza_van;
	}

	public boolean isReiniciar_diariamente() {
		return reiniciar_diariamente;
	}

	public void setReiniciar_diariamente(boolean reiniciar_diariamente) {
		this.reiniciar_diariamente = reiniciar_diariamente;
	}

	public String getNumero_remessa() {
		return numero_remessa;
	}

	public void setNumero_remessa(String numero_remessa) {
		this.numero_remessa = numero_remessa;
	}
}
