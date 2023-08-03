package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fn_titulo_rel")
public class FnTituloRel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private FnTitulo fntitulo;
	@JsonIgnoreProperties(value = { "fntitulorelitem" }, allowSetters = true)
	@ManyToOne
	private FnTitulo fntituloant;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();

	public FnTituloRel() {
	}

	public FnTituloRel(Long id, FnTitulo fntitulo, FnTitulo fntituloant, Date datacad) {
		super();
		this.id = id;
		this.fntitulo = fntitulo;
		this.fntituloant = fntituloant;
		this.datacad = datacad;
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

	public FnTitulo getFntituloant() {
		return fntituloant;
	}

	public void setFntituloant(FnTitulo fntituloant) {
		this.fntituloant = fntituloant;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
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
		FnTituloRel other = (FnTituloRel) obj;
		return Objects.equals(id, other.id);
	}
}
