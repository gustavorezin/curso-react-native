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
@Table(name = "fn_titulo_ccusto")
public class FnTituloCcusto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fntituloccustoitem" }, allowSetters = true)
	@ManyToOne
	private FnTitulo fntitulo;
	@ManyToOne
	private CdCcusto cdccusto;
	@Digits(integer = 10, fraction = 8)
	private BigDecimal pvalor;

	public FnTituloCcusto() {
	}

	public FnTituloCcusto(Long id, FnTitulo fntitulo, CdCcusto cdccusto,
			@Digits(integer = 10, fraction = 8) BigDecimal pvalor) {
		super();
		this.id = id;
		this.fntitulo = fntitulo;
		this.cdccusto = cdccusto;
		this.pvalor = pvalor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FnTitulo getFntitulo() {
		return fntitulo;
	}

	public void setFntitulo(FnTitulo fntitulo) {
		this.fntitulo = fntitulo;
	}

	public CdCcusto getCdccusto() {
		return cdccusto;
	}

	public void setCdccusto(CdCcusto cdccusto) {
		this.cdccusto = cdccusto;
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
		FnTituloCcusto other = (FnTituloCcusto) obj;
		return Objects.equals(id, other.id);
	}
}
