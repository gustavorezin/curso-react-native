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

@Entity
@Table(name = "cd_produto_preco")
public class CdProdutoPreco implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdProduto cdproduto;
	// @JsonIgnoreProperties(value = { "cdprodutotabprecositem" }, allowSetters =
	// true)
	@ManyToOne
	private CdProdutoTab cdprodutotab;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Digits(integer = 21, fraction = 8)
	private BigDecimal vcusto;
	@Digits(integer = 7, fraction = 4)
	private BigDecimal indmarkup;
	@Digits(integer = 7, fraction = 4)
	private BigDecimal pmarkup;
	@Digits(integer = 21, fraction = 8)
	private BigDecimal vvenda;
	@Digits(integer = 21, fraction = 8)
	private BigDecimal vvendaideal;
	@Digits(integer = 21, fraction = 8)
	private BigDecimal vlucro;
	@Digits(integer = 7, fraction = 2)
	private BigDecimal pdescmax;
	@Digits(integer = 7, fraction = 2)
	private BigDecimal pcom;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vcom;

	public CdProdutoPreco() {
	}

	public CdProdutoPreco(Long id, CdProduto cdproduto, CdProdutoTab cdprodutotab, Date datacad, Date dataat,
			@Digits(integer = 21, fraction = 8) BigDecimal vcusto,
			@Digits(integer = 7, fraction = 4) BigDecimal indmarkup,
			@Digits(integer = 7, fraction = 4) BigDecimal pmarkup,
			@Digits(integer = 21, fraction = 8) BigDecimal vvenda,
			@Digits(integer = 21, fraction = 8) BigDecimal vvendaideal,
			@Digits(integer = 21, fraction = 8) BigDecimal vlucro,
			@Digits(integer = 7, fraction = 2) BigDecimal pdescmax, @Digits(integer = 7, fraction = 2) BigDecimal pcom,
			@Digits(integer = 18, fraction = 2) BigDecimal vcom) {
		super();
		this.id = id;
		this.cdproduto = cdproduto;
		this.cdprodutotab = cdprodutotab;
		this.datacad = datacad;
		this.dataat = dataat;
		this.vcusto = vcusto;
		this.indmarkup = indmarkup;
		this.pmarkup = pmarkup;
		this.vvenda = vvenda;
		this.vvendaideal = vvendaideal;
		this.vlucro = vlucro;
		this.pdescmax = pdescmax;
		this.pcom = pcom;
		this.vcom = vcom;
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

	public CdProdutoTab getCdprodutotab() {
		return cdprodutotab;
	}

	public void setCdprodutotab(CdProdutoTab cdprodutotab) {
		this.cdprodutotab = cdprodutotab;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public BigDecimal getVcusto() {
		return vcusto;
	}

	public void setVcusto(BigDecimal vcusto) {
		this.vcusto = vcusto;
	}

	public BigDecimal getIndmarkup() {
		return indmarkup;
	}

	public void setIndmarkup(BigDecimal indmarkup) {
		this.indmarkup = indmarkup;
	}

	public BigDecimal getPmarkup() {
		return pmarkup;
	}

	public void setPmarkup(BigDecimal pmarkup) {
		this.pmarkup = pmarkup;
	}

	public BigDecimal getVvenda() {
		return vvenda;
	}

	public void setVvenda(BigDecimal vvenda) {
		this.vvenda = vvenda;
	}

	public BigDecimal getVvendaideal() {
		return vvendaideal;
	}

	public void setVvendaideal(BigDecimal vvendaideal) {
		this.vvendaideal = vvendaideal;
	}

	public BigDecimal getVlucro() {
		return vlucro;
	}

	public void setVlucro(BigDecimal vlucro) {
		this.vlucro = vlucro;
	}

	public BigDecimal getPdescmax() {
		return pdescmax;
	}

	public void setPdescmax(BigDecimal pdescmax) {
		this.pdescmax = pdescmax;
	}

	public BigDecimal getPcom() {
		return pcom;
	}

	public void setPcom(BigDecimal pcom) {
		this.pcom = pcom;
	}

	public BigDecimal getVcom() {
		return vcom;
	}

	public void setVcom(BigDecimal vcom) {
		this.vcom = vcom;
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
		CdProdutoPreco other = (CdProdutoPreco) obj;
		return Objects.equals(id, other.id);
	}
}
