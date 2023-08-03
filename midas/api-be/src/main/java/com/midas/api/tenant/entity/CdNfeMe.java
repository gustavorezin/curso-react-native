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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_nfe_me")
public class CdNfeMe implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdnfemeitems" }, allowSetters = true)
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 60)
	private String marca;
	@Column(length = 60)
	private String especie;

	public CdNfeMe() {
	}

	public CdNfeMe(Integer id, CdPessoa cdpessoaemp, String marca, String especie) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.marca = marca;
		this.especie = especie;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = CaracterUtil.remUpper(marca);
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = CaracterUtil.remUpper(especie);
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
		CdNfeMe other = (CdNfeMe) obj;
		return Objects.equals(id, other.id);
	}
}
