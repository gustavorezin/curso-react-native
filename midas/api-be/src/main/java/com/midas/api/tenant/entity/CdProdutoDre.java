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
@Table(name = "cd_produto_dre")
public class CdProdutoDre implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "cdprodutodreitem" }, allowSetters = true)
	@ManyToOne
	private CdProduto cdproduto;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	private Integer cdplconmicro_id;

	public CdProdutoDre() {
	}

	public CdProdutoDre(Long id, CdProduto cdproduto, CdPessoa cdpessoaemp, Integer cdplconmicro_id) {
		super();
		this.id = id;
		this.cdproduto = cdproduto;
		this.cdpessoaemp = cdpessoaemp;
		this.cdplconmicro_id = cdplconmicro_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CdProduto getCdproduto() {
		return cdproduto;
	}

	public void setCdproduto(CdProduto cdproduto) {
		this.cdproduto = cdproduto;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public Integer getCdplconmicro_id() {
		return cdplconmicro_id;
	}

	public void setCdplconmicro_id(Integer cdplconmicro_id) {
		this.cdplconmicro_id = cdplconmicro_id;
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
		CdProdutoDre other = (CdProdutoDre) obj;
		return Objects.equals(id, other.id);
	}
}
