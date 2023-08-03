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
@Table(name = "fs_nfeitem_pis")
public class FsNfeItemPis implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long fsnfe_id; // Apenas controle
	@Column(length = 2)
	private String cst;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbc = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal ppis = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vpis = new BigDecimal(0);
	@Digits(integer = 16, fraction = 4)
	private BigDecimal qbcprod = new BigDecimal(0);
	@Digits(integer = 15, fraction = 4)
	private BigDecimal valiqprod = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbcst = new BigDecimal(0);
	@Digits(integer = 5, fraction = 2)
	private BigDecimal ppisst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vpisst = new BigDecimal(0);
	@Digits(integer = 16, fraction = 4)
	private BigDecimal qbcprodst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 4)
	private BigDecimal valiqprodst = new BigDecimal(0);
	private Integer indsomapisst;

	public FsNfeItemPis() {
	}

	public FsNfeItemPis(Long id, Long fsnfe_id, String cst, @Digits(integer = 15, fraction = 6) BigDecimal vbc,
			@Digits(integer = 6, fraction = 4) BigDecimal ppis, @Digits(integer = 15, fraction = 6) BigDecimal vpis,
			@Digits(integer = 16, fraction = 4) BigDecimal qbcprod,
			@Digits(integer = 15, fraction = 4) BigDecimal valiqprod,
			@Digits(integer = 15, fraction = 6) BigDecimal vbcst, @Digits(integer = 5, fraction = 2) BigDecimal ppisst,
			@Digits(integer = 15, fraction = 6) BigDecimal vpisst,
			@Digits(integer = 16, fraction = 4) BigDecimal qbcprodst,
			@Digits(integer = 15, fraction = 4) BigDecimal valiqprodst, Integer indsomapisst) {
		super();
		this.id = id;
		this.fsnfe_id = fsnfe_id;
		this.cst = cst;
		this.vbc = vbc;
		this.ppis = ppis;
		this.vpis = vpis;
		this.qbcprod = qbcprod;
		this.valiqprod = valiqprod;
		this.vbcst = vbcst;
		this.ppisst = ppisst;
		this.vpisst = vpisst;
		this.qbcprodst = qbcprodst;
		this.valiqprodst = valiqprodst;
		this.indsomapisst = indsomapisst;
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

	public BigDecimal getPpis() {
		return ppis;
	}

	public void setPpis(BigDecimal ppis) {
		this.ppis = ppis;
	}

	public BigDecimal getVpis() {
		return vpis;
	}

	public void setVpis(BigDecimal vpis) {
		this.vpis = vpis;
	}

	public BigDecimal getQbcprod() {
		return qbcprod;
	}

	public void setQbcprod(BigDecimal qbcprod) {
		this.qbcprod = qbcprod;
	}

	public BigDecimal getValiqprod() {
		return valiqprod;
	}

	public void setValiqprod(BigDecimal valiqprod) {
		this.valiqprod = valiqprod;
	}

	public BigDecimal getVbcst() {
		return vbcst;
	}

	public void setVbcst(BigDecimal vbcst) {
		this.vbcst = vbcst;
	}

	public BigDecimal getPpisst() {
		return ppisst;
	}

	public void setPpisst(BigDecimal ppisst) {
		this.ppisst = ppisst;
	}

	public BigDecimal getVpisst() {
		return vpisst;
	}

	public void setVpisst(BigDecimal vpisst) {
		this.vpisst = vpisst;
	}

	public BigDecimal getQbcprodst() {
		return qbcprodst;
	}

	public void setQbcprodst(BigDecimal qbcprodst) {
		this.qbcprodst = qbcprodst;
	}

	public BigDecimal getValiqprodst() {
		return valiqprodst;
	}

	public void setValiqprodst(BigDecimal valiqprodst) {
		this.valiqprodst = valiqprodst;
	}

	public Integer getIndsomapisst() {
		return indsomapisst;
	}

	public void setIndsomapisst(Integer indsomapisst) {
		this.indsomapisst = indsomapisst;
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
		FsNfeItemPis other = (FsNfeItemPis) obj;
		return Objects.equals(id, other.id);
	}
}
