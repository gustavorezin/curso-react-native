package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cd_produto_comp")
public class CdProdutoComp implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@JsonIgnoreProperties(value = { "cdprodutocompitem" }, allowSetters = true)
	@ManyToOne
	private CdProduto cdproduto;
	@ManyToOne
	private CdProduto cdprodutocomp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@Digits(integer = 18, fraction = 8)
	private BigDecimal qtd;
	@Digits(integer = 21, fraction = 8)
	private BigDecimal vcusto;

	public CdProdutoComp() {
	}

	public CdProdutoComp(Long id, CdPessoa cdpessoaemp, CdProduto cdproduto, CdProduto cdprodutocomp, Date datacad,
			@Digits(integer = 18, fraction = 8) BigDecimal qtd, @Digits(integer = 21, fraction = 8) BigDecimal vcusto) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdproduto = cdproduto;
		this.cdprodutocomp = cdprodutocomp;
		this.datacad = datacad;
		this.qtd = qtd;
		this.vcusto = vcusto;
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

	public CdProduto getCdprodutocomp() {
		return cdprodutocomp;
	}

	public void setCdprodutocomp(CdProduto cdprodutocomp) {
		this.cdprodutocomp = cdprodutocomp;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getVcusto() {
		return vcusto;
	}

	public void setVcusto(BigDecimal vcusto) {
		this.vcusto = vcusto;
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
		CdProdutoComp other = (CdProdutoComp) obj;
		return Objects.equals(id, other.id);
	}
}
