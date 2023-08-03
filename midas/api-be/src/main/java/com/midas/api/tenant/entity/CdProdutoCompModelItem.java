package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cd_produto_comp_model_item")
public class CdProdutoCompModelItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "cdprodutocompmodelitem" }, allowSetters = true)
	@ManyToOne
	private CdProdutoCompModel cdprodutocompmodel;
	@ManyToOne
	private CdProduto cdproduto;
	@Digits(integer = 18, fraction = 8)
	private BigDecimal qtd = new BigDecimal(0);

	public CdProdutoCompModelItem() {
	}

	public CdProdutoCompModelItem(Long id, CdProdutoCompModel cdprodutocompmodel, CdProduto cdproduto,
			@Digits(integer = 18, fraction = 8) BigDecimal qtd) {
		super();
		this.id = id;
		this.cdprodutocompmodel = cdprodutocompmodel;
		this.cdproduto = cdproduto;
		this.qtd = qtd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CdProdutoCompModel getCdprodutocompmodel() {
		return cdprodutocompmodel;
	}

	public void setCdprodutocompmodel(CdProdutoCompModel cdprodutocompmodel) {
		this.cdprodutocompmodel = cdprodutocompmodel;
	}

	public CdProduto getCdproduto() {
		return cdproduto;
	}

	public void setCdproduto(CdProduto cdproduto) {
		this.cdproduto = cdproduto;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
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
		CdProdutoCompModelItem other = (CdProdutoCompModelItem) obj;
		return Objects.equals(id, other.id);
	}
}
