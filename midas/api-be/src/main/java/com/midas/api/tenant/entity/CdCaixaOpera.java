package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cd_caixa_opera")
public class CdCaixaOpera implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdcaixaoperaitems" }, allowSetters = true)
	@ManyToOne
	private CdPessoa cdpessoavend;
	@ManyToOne
	private CdCaixa cdcaixa;

	public CdCaixaOpera() {
	}

	public CdCaixaOpera(Integer id, CdPessoa cdpessoavend, CdCaixa cdcaixa) {
		super();
		this.id = id;
		this.cdpessoavend = cdpessoavend;
		this.cdcaixa = cdcaixa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPessoa getCdpessoavend() {
		return cdpessoavend;
	}

	public void setCdpessoavend(CdPessoa cdpessoavend) {
		this.cdpessoavend = cdpessoavend;
	}

	public CdCaixa getCdcaixa() {
		return cdcaixa;
	}

	public void setCdcaixa(CdCaixa cdcaixa) {
		this.cdcaixa = cdcaixa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdCaixaOpera other = (CdCaixaOpera) obj;
		return Objects.equals(id, other.id);
	}
}
