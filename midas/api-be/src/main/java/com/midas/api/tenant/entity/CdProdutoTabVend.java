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
@Table(name = "cd_produto_tabvend")
public class CdProdutoTabVend implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdprodutotabvenditems" }, allowSetters = true)
	@ManyToOne
	private CdPessoa cdpessoavend;
	private Integer cdprodutotab;
	private Long cdpessoaemp;

	public CdProdutoTabVend() {
	}

	public CdProdutoTabVend(Integer id, CdPessoa cdpessoavend, Integer cdprodutotab, Long cdpessoaemp) {
		super();
		this.id = id;
		this.cdpessoavend = cdpessoavend;
		this.cdprodutotab = cdprodutotab;
		this.cdpessoaemp = cdpessoaemp;
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

	public Integer getCdprodutotab() {
		return cdprodutotab;
	}

	public void setCdprodutotab(Integer cdprodutotab) {
		this.cdprodutotab = cdprodutotab;
	}

	public Long getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(Long cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
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
		CdProdutoTabVend other = (CdProdutoTabVend) obj;
		return Objects.equals(id, other.id);
	}
}
