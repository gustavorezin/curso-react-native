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
@Table(name = "cd_produto_local")
public class CdProdutoLocal implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@JsonIgnoreProperties(value = { "cdprodutolocalitem" }, allowSetters = true)
	@ManyToOne
	private CdProduto cdproduto;
	@Column(length = 45)
	private String sessao;
	@Column(length = 45)
	private String corredor;
	@Column(length = 45)
	private String prateleira;

	public CdProdutoLocal() {
	}

	public CdProdutoLocal(Long id, CdPessoa cdpessoaemp, CdProduto cdproduto, String sessao, String corredor,
			String prateleira) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdproduto = cdproduto;
		this.sessao = sessao;
		this.corredor = corredor;
		this.prateleira = prateleira;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public CdProduto getCdproduto() {
		return cdproduto;
	}

	public void setCdproduto(CdProduto cdproduto) {
		this.cdproduto = cdproduto;
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = CaracterUtil.remUpper(sessao);
	}

	public String getCorredor() {
		return corredor;
	}

	public void setCorredor(String corredor) {
		this.corredor = CaracterUtil.remUpper(corredor);
	}

	public String getPrateleira() {
		return prateleira;
	}

	public void setPrateleira(String prateleira) {
		this.prateleira = CaracterUtil.remUpper(prateleira);
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
		CdProdutoLocal other = (CdProdutoLocal) obj;
		return Objects.equals(id, other.id);
	}
}
