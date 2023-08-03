package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cd_cfgfiscal_serie")
public class CdCfgFiscalSerie implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 2)
	private String modelo;
	private Integer serieatual = 1;

	public CdCfgFiscalSerie() {
	}

	public CdCfgFiscalSerie(Integer id, CdPessoa cdpessoaemp, String modelo, Integer serieatual) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.modelo = modelo;
		this.serieatual = serieatual;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getSerieatual() {
		return serieatual;
	}

	public void setSerieatual(Integer serieatual) {
		this.serieatual = serieatual;
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
		CdCfgFiscalSerie other = (CdCfgFiscalSerie) obj;
		return Objects.equals(id, other.id);
	}
}
