package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fn_titulo_dre")
public class FnTituloDre implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fntitulodreitem" }, allowSetters = true)
	@ManyToOne
	private FnTitulo fntitulo;
	@ManyToOne
	private CdPlconMicro cdplconmicro;
	@Digits(integer = 10, fraction = 8)
	private BigDecimal pvalor;
	@Column(length = 1)
	private String negocia = "N";

	public FnTituloDre() {
	}

	public FnTituloDre(Long id, FnTitulo fntitulo, CdPlconMicro cdplconmicro,
			@Digits(integer = 10, fraction = 8) BigDecimal pvalor, String negocia) {
		super();
		this.id = id;
		this.fntitulo = fntitulo;
		this.cdplconmicro = cdplconmicro;
		this.pvalor = pvalor;
		this.negocia = negocia;
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

	public String getNegocia() {
		return negocia;
	}

	public void setNegocia(String negocia) {
		this.negocia = negocia;
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
		FnTituloDre other = (FnTituloDre) obj;
		return Objects.equals(id, other.id);
	}
}
