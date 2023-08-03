package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "fs_cteicms")
public class FsCteIcms implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 2)
	private String cst;
	@Digits(integer = 10, fraction = 4)
	private BigDecimal predbc = new BigDecimal(0);
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vbc = new BigDecimal(0);
	@Digits(integer = 10, fraction = 4)
	private BigDecimal picms = new BigDecimal(0);
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vicms = new BigDecimal(0);
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vbcstret = new BigDecimal(0);
	@Digits(integer = 10, fraction = 4)
	private BigDecimal picmsstret = new BigDecimal(0);
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vicmsstret = new BigDecimal(0);
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vcred = new BigDecimal(0);
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vibpt = new BigDecimal(0);

	public FsCteIcms() {
	}

	public FsCteIcms(Long id, String cst, @Digits(integer = 10, fraction = 4) BigDecimal predbc,
			@Digits(integer = 13, fraction = 2) BigDecimal vbc, @Digits(integer = 10, fraction = 4) BigDecimal picms,
			@Digits(integer = 13, fraction = 2) BigDecimal vicms,
			@Digits(integer = 13, fraction = 2) BigDecimal vbcstret,
			@Digits(integer = 10, fraction = 4) BigDecimal picmsstret,
			@Digits(integer = 13, fraction = 2) BigDecimal vicmsstret,
			@Digits(integer = 13, fraction = 2) BigDecimal vcred,
			@Digits(integer = 13, fraction = 2) BigDecimal vibpt) {
		super();
		this.id = id;
		this.cst = cst;
		this.predbc = predbc;
		this.vbc = vbc;
		this.picms = picms;
		this.vicms = vicms;
		this.vbcstret = vbcstret;
		this.picmsstret = picmsstret;
		this.vicmsstret = vicmsstret;
		this.vcred = vcred;
		this.vibpt = vibpt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public BigDecimal getPredbc() {
		return predbc;
	}

	public void setPredbc(BigDecimal predbc) {
		this.predbc = predbc;
	}

	public BigDecimal getVbc() {
		return vbc;
	}

	public void setVbc(BigDecimal vbc) {
		this.vbc = vbc;
	}

	public BigDecimal getPicms() {
		return picms;
	}

	public void setPicms(BigDecimal picms) {
		this.picms = picms;
	}

	public BigDecimal getVicms() {
		return vicms;
	}

	public void setVicms(BigDecimal vicms) {
		this.vicms = vicms;
	}

	public BigDecimal getVbcstret() {
		return vbcstret;
	}

	public void setVbcstret(BigDecimal vbcstret) {
		this.vbcstret = vbcstret;
	}

	public BigDecimal getPicmsstret() {
		return picmsstret;
	}

	public void setPicmsstret(BigDecimal picmsstret) {
		this.picmsstret = picmsstret;
	}

	public BigDecimal getVicmsstret() {
		return vicmsstret;
	}

	public void setVicmsstret(BigDecimal vicmsstret) {
		this.vicmsstret = vicmsstret;
	}

	public BigDecimal getVcred() {
		return vcred;
	}

	public void setVcred(BigDecimal vcred) {
		this.vcred = vcred;
	}

	public BigDecimal getVibpt() {
		return vibpt;
	}

	public void setVibpt(BigDecimal vibpt) {
		this.vibpt = vibpt;
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
		FsCteIcms other = (FsCteIcms) obj;
		return Objects.equals(id, other.id);
	}
}
