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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fs_cteinf_carga")
public class FsCteInfCarga implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsCteInf fscteinf;
	@Column(length = 2)
	private String cunid;
	@Column(length = 20)
	private String tpmed;
	@Digits(integer = 11, fraction = 4)
	private BigDecimal qcarga = new BigDecimal(0);

	public FsCteInfCarga() {
	}

	public FsCteInfCarga(Long id, FsCteInf fscteinf, String cunid, String tpmed,
			@Digits(integer = 11, fraction = 4) BigDecimal qcarga) {
		super();
		this.id = id;
		this.fscteinf = fscteinf;
		this.cunid = cunid;
		this.tpmed = tpmed;
		this.qcarga = qcarga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsCteInf getFscteinf() {
		return fscteinf;
	}

	public void setFscteinf(FsCteInf fscteinf) {
		this.fscteinf = fscteinf;
	}

	public String getCunid() {
		return cunid;
	}

	public void setCunid(String cunid) {
		this.cunid = cunid;
	}

	public String getTpmed() {
		return tpmed;
	}

	public void setTpmed(String tpmed) {
		this.tpmed = tpmed;
	}

	public BigDecimal getQcarga() {
		return qcarga;
	}

	public void setQcarga(BigDecimal qcarga) {
		this.qcarga = qcarga;
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
		FsCteInfCarga other = (FsCteInfCarga) obj;
		return Objects.equals(id, other.id);
	}
}
