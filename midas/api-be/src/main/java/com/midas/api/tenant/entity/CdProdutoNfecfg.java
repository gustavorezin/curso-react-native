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

@Entity
@Table(name = "cd_produto_nfecfg")
public class CdProdutoNfecfg implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdprodutonfecfgitem" }, allowSetters = true)
	@ManyToOne
	private CdProduto cdproduto;
	@ManyToOne
	private CdNfeCfg cdnfecfg;
	@Column(length = 2)
	private String uf;

	public CdProdutoNfecfg() {
	}

	public CdProdutoNfecfg(Integer id, CdProduto cdproduto, CdNfeCfg cdnfecfg, String uf) {
		super();
		this.id = id;
		this.cdproduto = cdproduto;
		this.cdnfecfg = cdnfecfg;
		this.uf = uf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdProduto getCdproduto() {
		return cdproduto;
	}

	public void setCdproduto(CdProduto cdproduto) {
		this.cdproduto = cdproduto;
	}

	public CdNfeCfg getCdnfecfg() {
		return cdnfecfg;
	}

	public void setCdnfecfg(CdNfeCfg cdnfecfg) {
		this.cdnfecfg = cdnfecfg;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
		CdProdutoNfecfg other = (CdProdutoNfecfg) obj;
		return Objects.equals(id, other.id);
	}
}
