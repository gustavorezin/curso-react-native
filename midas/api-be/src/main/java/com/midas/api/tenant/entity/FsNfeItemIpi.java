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
@Table(name = "fs_nfeitem_ipi")
public class FsNfeItemIpi implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long fsnfe_id;
	@Column(length = 14)
	private String cnpjprod;
	@Column(length = 60)
	private String cselo;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal qselo = new BigDecimal(0);
	@Column(length = 3)
	private String cenq;
	@Column(length = 3)
	private String cst;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbc = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pipi = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vipi = new BigDecimal(0);
	@Digits(integer = 15, fraction = 4)
	private BigDecimal qunid = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vunid = new BigDecimal(0);

	public FsNfeItemIpi() {
	}

	public FsNfeItemIpi(Long id, Long fsnfe_id, String cnpjprod, String cselo,
			@Digits(integer = 15, fraction = 2) BigDecimal qselo, String cenq, String cst,
			@Digits(integer = 15, fraction = 6) BigDecimal vbc, @Digits(integer = 6, fraction = 4) BigDecimal pipi,
			@Digits(integer = 15, fraction = 6) BigDecimal vipi, @Digits(integer = 15, fraction = 4) BigDecimal qunid,
			@Digits(integer = 15, fraction = 6) BigDecimal vunid) {
		super();
		this.id = id;
		this.fsnfe_id = fsnfe_id;
		this.cnpjprod = cnpjprod;
		this.cselo = cselo;
		this.qselo = qselo;
		this.cenq = cenq;
		this.cst = cst;
		this.vbc = vbc;
		this.pipi = pipi;
		this.vipi = vipi;
		this.qunid = qunid;
		this.vunid = vunid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFsnfe_id() {
		return fsnfe_id;
	}

	public void setFsnfe_id(Long fsnfe_id) {
		this.fsnfe_id = fsnfe_id;
	}

	public String getCnpjprod() {
		return cnpjprod;
	}

	public void setCnpjprod(String cnpjprod) {
		this.cnpjprod = cnpjprod;
	}

	public String getCselo() {
		return cselo;
	}

	public void setCselo(String cselo) {
		this.cselo = cselo;
	}

	public BigDecimal getQselo() {
		return qselo;
	}

	public void setQselo(BigDecimal qselo) {
		this.qselo = qselo;
	}

	public String getCenq() {
		return cenq;
	}

	public void setCenq(String cenq) {
		this.cenq = cenq;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public BigDecimal getVbc() {
		return vbc;
	}

	public void setVbc(BigDecimal vbc) {
		this.vbc = vbc;
	}

	public BigDecimal getPipi() {
		return pipi;
	}

	public void setPipi(BigDecimal pipi) {
		this.pipi = pipi;
	}

	public BigDecimal getVipi() {
		return vipi;
	}

	public void setVipi(BigDecimal vipi) {
		this.vipi = vipi;
	}

	public BigDecimal getQunid() {
		return qunid;
	}

	public void setQunid(BigDecimal qunid) {
		this.qunid = qunid;
	}

	public BigDecimal getVunid() {
		return vunid;
	}

	public void setVunid(BigDecimal vunid) {
		this.vunid = vunid;
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
		FsNfeItemIpi other = (FsNfeItemIpi) obj;
		return Objects.equals(id, other.id);
	}
}
