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
@Table(name = "es_est")
public class EsEst implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@JsonIgnoreProperties(value = { "esestitem" }, allowSetters = true)
	@ManyToOne
	private CdProduto cdproduto;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Digits(integer = 18, fraction = 6)
	private BigDecimal qtd = new BigDecimal(0);
	@Digits(integer = 18, fraction = 6)
	private BigDecimal qtdmin = new BigDecimal(0);
	@Digits(integer = 21, fraction = 8)
	private BigDecimal vcusto = new BigDecimal(0);

	public EsEst() {
	}

	public EsEst(Long id, CdPessoa cdpessoaemp, CdProduto cdproduto, Date dataat,
			@Digits(integer = 18, fraction = 6) BigDecimal qtd, @Digits(integer = 18, fraction = 6) BigDecimal qtdmin,
			@Digits(integer = 21, fraction = 8) BigDecimal vcusto) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdproduto = cdproduto;
		this.dataat = dataat;
		this.qtd = qtd;
		this.qtdmin = qtdmin;
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

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getQtdmin() {
		return qtdmin;
	}

	public void setQtdmin(BigDecimal qtdmin) {
		this.qtdmin = qtdmin;
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
		EsEst other = (EsEst) obj;
		return Objects.equals(id, other.id);
	}
}
