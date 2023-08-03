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
@Table(name = "cd_produto_ns_rel")
public class CdProdutoNsRel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "cdprodutonsrelitem" }, allowSetters = true)
	@ManyToOne
	private CdProduto cdproduto;
	private Long cdpessoaemp_id = 0L;
	private Integer cdprodutons_id = 0;

	public CdProdutoNsRel() {
	}

	public CdProdutoNsRel(Long id, CdProduto cdproduto, Long cdpessoaemp_id, Integer cdprodutons_id) {
		super();
		this.id = id;
		this.cdproduto = cdproduto;
		this.cdpessoaemp_id = cdpessoaemp_id;
		this.cdprodutons_id = cdprodutons_id;
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

	public Long getCdpessoaemp_id() {
		return cdpessoaemp_id;
	}

	public void setCdpessoaemp_id(Long cdpessoaemp_id) {
		this.cdpessoaemp_id = cdpessoaemp_id;
	}

	public Integer getCdprodutons_id() {
		return cdprodutons_id;
	}

	public void setCdprodutons_id(Integer cdprodutons_id) {
		this.cdprodutons_id = cdprodutons_id;
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
		CdProdutoNsRel other = (CdProdutoNsRel) obj;
		return Objects.equals(id, other.id);
	}
}
