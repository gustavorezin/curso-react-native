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
@Table(name = "fn_cxmv_dre")
public class FnCxmvDre implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fncxmvdreitem" }, allowSetters = true)
	@ManyToOne
	private FnCxmv fncxmv;
	@ManyToOne
	private CdPlconMicro cdplconmicro;
	@Digits(integer = 10, fraction = 8)
	private BigDecimal pvalor;

	public FnCxmvDre() {
	}

	public FnCxmvDre(Long id, FnCxmv fncxmv, CdPlconMicro cdplconmicro,
			@Digits(integer = 10, fraction = 8) BigDecimal pvalor) {
		super();
		this.id = id;
		this.fncxmv = fncxmv;
		this.cdplconmicro = cdplconmicro;
		this.pvalor = pvalor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FnCxmv getFncxmv() {
		return fncxmv;
	}

	public void setFncxmv(FnCxmv fncxmv) {
		this.fncxmv = fncxmv;
	}

	public CdPlconMicro getCdplconmicro() {
		return cdplconmicro;
	}

	public void setCdplconmicro(CdPlconMicro cdplconmicro) {
		this.cdplconmicro = cdplconmicro;
	}

	public BigDecimal getPvalor() {
		return pvalor;
	}

	public void setPvalor(BigDecimal pvalor) {
		this.pvalor = pvalor;
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
		FnCxmvDre other = (FnCxmvDre) obj;
		return Objects.equals(id, other.id);
	}
}
