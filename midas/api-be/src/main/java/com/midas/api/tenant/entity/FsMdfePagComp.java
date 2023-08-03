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
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_mdfepag_comp")
public class FsMdfePagComp implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "fsmdfepagcompitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfePag fsmdfepag;
	@Column(length = 2)
	private String tpcomp;
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vcomp;
	@Column(length = 60)
	private String xcomp;

	public FsMdfePagComp() {
	}

	public FsMdfePagComp(Integer id, FsMdfePag fsmdfepag, String tpcomp,
			@Digits(integer = 13, fraction = 2) BigDecimal vcomp, String xcomp) {
		super();
		this.id = id;
		this.fsmdfepag = fsmdfepag;
		this.tpcomp = tpcomp;
		this.vcomp = vcomp;
		this.xcomp = xcomp;
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

	public String getTpcomp() {
		return tpcomp;
	}

	public void setTpcomp(String tpcomp) {
		this.tpcomp = tpcomp;
	}

	public BigDecimal getVcomp() {
		return vcomp;
	}

	public void setVcomp(BigDecimal vcomp) {
		this.vcomp = vcomp;
	}

	public String getXcomp() {
		return xcomp;
	}

	public void setXcomp(String xcomp) {
		this.xcomp = CaracterUtil.remUpper(xcomp);
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
		FsMdfePagComp other = (FsMdfePagComp) obj;
		return Objects.equals(id, other.id);
	}
}
