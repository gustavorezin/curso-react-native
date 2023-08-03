package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_produto_markup")
public class CdProdutoMarkup implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Column(length = 80)
	private String descricao;
	@Digits(integer = 5, fraction = 2)
	private BigDecimal pdespfixas;
	@Digits(integer = 5, fraction = 2)
	private BigDecimal pdespvar;
	@Digits(integer = 5, fraction = 2)
	private BigDecimal plucropret;
	@Digits(integer = 5, fraction = 2)
	private BigDecimal pdescmax;
	@Digits(integer = 5, fraction = 2)
	private BigDecimal pcom;

	public CdProdutoMarkup() {
	}

	public CdProdutoMarkup(Integer id, Date datacad, Date dataat, String descricao,
			@Digits(integer = 5, fraction = 2) BigDecimal pdespfixas,
			@Digits(integer = 5, fraction = 2) BigDecimal pdespvar,
			@Digits(integer = 5, fraction = 2) BigDecimal plucropret,
			@Digits(integer = 5, fraction = 2) BigDecimal pdescmax,
			@Digits(integer = 5, fraction = 2) BigDecimal pcom) {
		super();
		this.id = id;
		this.datacad = datacad;
		this.dataat = dataat;
		this.descricao = descricao;
		this.pdespfixas = pdespfixas;
		this.pdespvar = pdespvar;
		this.plucropret = plucropret;
		this.pdescmax = pdescmax;
		this.pcom = pcom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = CaracterUtil.remUpper(descricao);
	}

	public BigDecimal getPdespfixas() {
		return pdespfixas;
	}

	public void setPdespfixas(BigDecimal pdespfixas) {
		this.pdespfixas = pdespfixas;
	}

	public BigDecimal getPdespvar() {
		return pdespvar;
	}

	public void setPdespvar(BigDecimal pdespvar) {
		this.pdespvar = pdespvar;
	}

	public BigDecimal getPlucropret() {
		return plucropret;
	}

	public void setPlucropret(BigDecimal plucropret) {
		this.plucropret = plucropret;
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
		CdProdutoMarkup other = (CdProdutoMarkup) obj;
		return Objects.equals(id, other.id);
	}
}
