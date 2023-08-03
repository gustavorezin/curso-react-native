package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_produto_comp_model")
public class CdProdutoCompModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 60)
	private String descricao;
	@Column(length = 8)
	private String status;
	@JsonIgnoreProperties(value = { "cdprodutocompmodel" }, allowSetters = true)
	@OneToMany(mappedBy = "cdprodutocompmodel", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdProdutoCompModelItem> cdprodutocompmodelitem = new ArrayList<CdProdutoCompModelItem>();

	public CdProdutoCompModel() {
	}

	public CdProdutoCompModel(Long id, CdPessoa cdpessoaemp, String descricao, String status,
			List<CdProdutoCompModelItem> cdprodutocompmodelitem) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.descricao = descricao;
		this.status = status;
		this.cdprodutocompmodelitem = cdprodutocompmodelitem;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = CaracterUtil.remUpper(descricao);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CdProdutoCompModelItem> getCdprodutocompmodelitem() {
		return cdprodutocompmodelitem;
	}

	public void setCdprodutocompmodelitem(List<CdProdutoCompModelItem> cdprodutocompmodelitem) {
		this.cdprodutocompmodelitem = cdprodutocompmodelitem;
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
		CdProdutoCompModel other = (CdProdutoCompModel) obj;
		return Objects.equals(id, other.id);
	}
}
