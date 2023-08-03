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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fs_nfecobr_dup")
public class FsNfeCobrDup implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsNfeCobr fsnfecobr;
	@Column(length = 60)
	private String ndup;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dvenc;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vdup = new BigDecimal(0);

	public FsNfeCobrDup() {
	}

	public FsNfeCobrDup(Long id, FsNfeCobr fsnfecobr, String ndup, Date dvenc,
			@Digits(integer = 15, fraction = 2) BigDecimal vdup) {
		super();
		this.id = id;
		this.fsnfecobr = fsnfecobr;
		this.ndup = ndup;
		this.dvenc = dvenc;
		this.vdup = vdup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsNfeCobr getFsnfecobr() {
		return fsnfecobr;
	}

	public void setFsnfecobr(FsNfeCobr fsnfecobr) {
		this.fsnfecobr = fsnfecobr;
	}

	public String getNdup() {
		return ndup;
	}

	public void setNdup(String ndup) {
		this.ndup = ndup;
	}

	public Date getDvenc() {
		return dvenc;
	}

	public void setDvenc(Date dvenc) {
		this.dvenc = dvenc;
	}

	public BigDecimal getVdup() {
		return vdup;
	}

	public void setVdup(BigDecimal vdup) {
		this.vdup = vdup;
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
		FsNfeCobrDup other = (FsNfeCobrDup) obj;
		return Objects.equals(id, other.id);
	}
}
