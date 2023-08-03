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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fs_mdfepag_prazo")
public class FsMdfePagPrazo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "fsmdfepagprazoitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfePag fsmdfepag;
	@Column(length = 3)
	private String nparcela;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dvenc;
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vparcela;

	public FsMdfePagPrazo() {
	}
	
	public FsMdfePagPrazo(Integer id, FsMdfePag fsmdfepag, String nparcela, Date dvenc,
			@Digits(integer = 13, fraction = 2) BigDecimal vparcela) {
		super();
		this.id = id;
		this.fsmdfepag = fsmdfepag;
		this.nparcela = nparcela;
		this.dvenc = dvenc;
		this.vparcela = vparcela;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FsMdfePag getFsmdfepag() {
		return fsmdfepag;
	}

	public void setFsmdfepag(FsMdfePag fsmdfepag) {
		this.fsmdfepag = fsmdfepag;
	}

	public String getNparcela() {
		return nparcela;
	}

	public void setNparcela(String nparcela) {
		this.nparcela = nparcela;
	}

	public Date getDvenc() {
		return dvenc;
	}

	public void setDvenc(Date dvenc) {
		this.dvenc = dvenc;
	}

	public BigDecimal getVparcela() {
		return vparcela;
	}

	public void setVparcela(BigDecimal vparcela) {
		this.vparcela = vparcela;
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
		FsMdfePagPrazo other = (FsMdfePagPrazo) obj;
		return Objects.equals(id, other.id);
	}
}
