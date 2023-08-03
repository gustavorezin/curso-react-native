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
@Table(name = "cd_condpagdia")
public class CdCondPagDia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdcondpagdiaitem" }, allowSetters = true)
	@ManyToOne
	private CdCondPag cdcondpag;
	private Integer dias;
	@Digits(integer = 4, fraction = 2)
	private BigDecimal juros;

	public CdCondPagDia() {
	}

	public CdCondPagDia(Integer id, CdCondPag cdcondpag, Integer dias,
			@Digits(integer = 4, fraction = 2) BigDecimal juros) {
		super();
		this.id = id;
		this.cdcondpag = cdcondpag;
		this.dias = dias;
		this.juros = juros;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdCondPag getCdcondpag() {
		return cdcondpag;
	}

	public void setCdcondpag(CdCondPag cdcondpag) {
		this.cdcondpag = cdcondpag;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public BigDecimal getJuros() {
		return juros;
	}

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
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
		CdCondPagDia other = (CdCondPagDia) obj;
		return Objects.equals(id, other.id);
	}
}
