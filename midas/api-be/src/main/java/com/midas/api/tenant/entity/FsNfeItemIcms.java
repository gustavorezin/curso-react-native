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
@Table(name = "fs_nfeitem_icms")
public class FsNfeItemIcms implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long fsnfe_id; // Apenas controle
	@Column(length = 1)
	private String orig;
	@Column(length = 3)
	private String cst;
	private Integer modbc;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal predbc = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbc = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal picms = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicms = new BigDecimal(0);
	private Integer modbcst;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pmvast = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal predbcst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbcst = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal picmsst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbcstret = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsstret = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbcstdest = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsstdest = new BigDecimal(0);
	private Integer motdesicms;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pbcop = new BigDecimal(0);
	@Column(length = 2)
	private String ufst;
	@Digits(integer = 15, fraction = 4)
	private BigDecimal pcredsn = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vcredicmssn = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsdeson = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsop = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pdif = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsdif = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsdifremet = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbcfcp = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pfcp = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vfcp = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbcfcpst = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pfcpst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vfcpst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbcfcpstret = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pfcpstret = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vfcpstret = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pst = new BigDecimal(0);
	@Digits(integer = 6, fraction = 4)
	private BigDecimal pfcpdif = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vfcpdif = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vfcpefet = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsstdeson = new BigDecimal(0);
	private Integer motdesicmsst;

	public FsNfeItemIcms() {
	}

	public FsNfeItemIcms(Long id, Long fsnfe_id, String orig, String cst, Integer modbc,
			@Digits(integer = 6, fraction = 4) BigDecimal predbc, @Digits(integer = 15, fraction = 6) BigDecimal vbc,
			@Digits(integer = 6, fraction = 4) BigDecimal picms, @Digits(integer = 15, fraction = 6) BigDecimal vicms,
			Integer modbcst, @Digits(integer = 6, fraction = 4) BigDecimal pmvast,
			@Digits(integer = 6, fraction = 4) BigDecimal predbcst,
			@Digits(integer = 15, fraction = 6) BigDecimal vbcst, @Digits(integer = 6, fraction = 4) BigDecimal picmsst,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsst,
			@Digits(integer = 15, fraction = 6) BigDecimal vbcstret,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsstret,
			@Digits(integer = 15, fraction = 6) BigDecimal vbcstdest,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsstdest, Integer motdesicms,
			@Digits(integer = 6, fraction = 4) BigDecimal pbcop, String ufst,
			@Digits(integer = 15, fraction = 4) BigDecimal pcredsn,
			@Digits(integer = 15, fraction = 6) BigDecimal vcredicmssn,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsdeson,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsop, @Digits(integer = 6, fraction = 4) BigDecimal pdif,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsdif,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsdifremet,
			@Digits(integer = 15, fraction = 6) BigDecimal vbcfcp, @Digits(integer = 6, fraction = 4) BigDecimal pfcp,
			@Digits(integer = 15, fraction = 6) BigDecimal vfcp,
			@Digits(integer = 15, fraction = 6) BigDecimal vbcfcpst,
			@Digits(integer = 6, fraction = 4) BigDecimal pfcpst, @Digits(integer = 15, fraction = 6) BigDecimal vfcpst,
			@Digits(integer = 15, fraction = 6) BigDecimal vbcfcpstret,
			@Digits(integer = 6, fraction = 4) BigDecimal pfcpstret,
			@Digits(integer = 15, fraction = 6) BigDecimal vfcpstret, @Digits(integer = 6, fraction = 4) BigDecimal pst,
			@Digits(integer = 6, fraction = 4) BigDecimal pfcpdif,
			@Digits(integer = 15, fraction = 6) BigDecimal vfcpdif,
			@Digits(integer = 15, fraction = 6) BigDecimal vfcpefet,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsstdeson, Integer motdesicmsst) {
		super();
		this.id = id;
		this.fsnfe_id = fsnfe_id;
		this.orig = orig;
		this.cst = cst;
		this.modbc = modbc;
		this.predbc = predbc;
		this.vbc = vbc;
		this.picms = picms;
		this.vicms = vicms;
		this.modbcst = modbcst;
		this.pmvast = pmvast;
		this.predbcst = predbcst;
		this.vbcst = vbcst;
		this.picmsst = picmsst;
		this.vicmsst = vicmsst;
		this.vbcstret = vbcstret;
		this.vicmsstret = vicmsstret;
		this.vbcstdest = vbcstdest;
		this.vicmsstdest = vicmsstdest;
		this.motdesicms = motdesicms;
		this.pbcop = pbcop;
		this.ufst = ufst;
		this.pcredsn = pcredsn;
		this.vcredicmssn = vcredicmssn;
		this.vicmsdeson = vicmsdeson;
		this.vicmsop = vicmsop;
		this.pdif = pdif;
		this.vicmsdif = vicmsdif;
		this.vicmsdifremet = vicmsdifremet;
		this.vbcfcp = vbcfcp;
		this.pfcp = pfcp;
		this.vfcp = vfcp;
		this.vbcfcpst = vbcfcpst;
		this.pfcpst = pfcpst;
		this.vfcpst = vfcpst;
		this.vbcfcpstret = vbcfcpstret;
		this.pfcpstret = pfcpstret;
		this.vfcpstret = vfcpstret;
		this.pst = pst;
		this.pfcpdif = pfcpdif;
		this.vfcpdif = vfcpdif;
		this.vfcpefet = vfcpefet;
		this.vicmsstdeson = vicmsstdeson;
		this.motdesicmsst = motdesicmsst;
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

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public Integer getModbc() {
		return modbc;
	}

	public void setModbc(Integer modbc) {
		this.modbc = modbc;
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

	public Integer getModbcst() {
		return modbcst;
	}

	public void setModbcst(Integer modbcst) {
		this.modbcst = modbcst;
	}

	public BigDecimal getPmvast() {
		return pmvast;
	}

	public void setPmvast(BigDecimal pmvast) {
		this.pmvast = pmvast;
	}

	public BigDecimal getPredbcst() {
		return predbcst;
	}

	public void setPredbcst(BigDecimal predbcst) {
		this.predbcst = predbcst;
	}

	public BigDecimal getVbcst() {
		return vbcst;
	}

	public void setVbcst(BigDecimal vbcst) {
		this.vbcst = vbcst;
	}

	public BigDecimal getPicmsst() {
		return picmsst;
	}

	public void setPicmsst(BigDecimal picmsst) {
		this.picmsst = picmsst;
	}

	public BigDecimal getVicmsst() {
		return vicmsst;
	}

	public void setVicmsst(BigDecimal vicmsst) {
		this.vicmsst = vicmsst;
	}

	public BigDecimal getVbcstret() {
		return vbcstret;
	}

	public void setVbcstret(BigDecimal vbcstret) {
		this.vbcstret = vbcstret;
	}

	public BigDecimal getVicmsstret() {
		return vicmsstret;
	}

	public void setVicmsstret(BigDecimal vicmsstret) {
		this.vicmsstret = vicmsstret;
	}

	public BigDecimal getVbcstdest() {
		return vbcstdest;
	}

	public void setVbcstdest(BigDecimal vbcstdest) {
		this.vbcstdest = vbcstdest;
	}

	public BigDecimal getVicmsstdest() {
		return vicmsstdest;
	}

	public void setVicmsstdest(BigDecimal vicmsstdest) {
		this.vicmsstdest = vicmsstdest;
	}

	public Integer getMotdesicms() {
		return motdesicms;
	}

	public void setMotdesicms(Integer motdesicms) {
		this.motdesicms = motdesicms;
	}

	public BigDecimal getPbcop() {
		return pbcop;
	}

	public void setPbcop(BigDecimal pbcop) {
		this.pbcop = pbcop;
	}

	public String getUfst() {
		return ufst;
	}

	public void setUfst(String ufst) {
		this.ufst = ufst;
	}

	public BigDecimal getPcredsn() {
		return pcredsn;
	}

	public void setPcredsn(BigDecimal pcredsn) {
		this.pcredsn = pcredsn;
	}

	public BigDecimal getVcredicmssn() {
		return vcredicmssn;
	}

	public void setVcredicmssn(BigDecimal vcredicmssn) {
		this.vcredicmssn = vcredicmssn;
	}

	public BigDecimal getVicmsdeson() {
		return vicmsdeson;
	}

	public void setVicmsdeson(BigDecimal vicmsdeson) {
		this.vicmsdeson = vicmsdeson;
	}

	public BigDecimal getVicmsop() {
		return vicmsop;
	}

	public void setVicmsop(BigDecimal vicmsop) {
		this.vicmsop = vicmsop;
	}

	public BigDecimal getPdif() {
		return pdif;
	}

	public void setPdif(BigDecimal pdif) {
		this.pdif = pdif;
	}

	public BigDecimal getVicmsdif() {
		return vicmsdif;
	}

	public void setVicmsdif(BigDecimal vicmsdif) {
		this.vicmsdif = vicmsdif;
	}

	public BigDecimal getVicmsdifremet() {
		return vicmsdifremet;
	}

	public void setVicmsdifremet(BigDecimal vicmsdifremet) {
		this.vicmsdifremet = vicmsdifremet;
	}

	public BigDecimal getVbcfcp() {
		return vbcfcp;
	}

	public void setVbcfcp(BigDecimal vbcfcp) {
		this.vbcfcp = vbcfcp;
	}

	public BigDecimal getPfcp() {
		return pfcp;
	}

	public void setPfcp(BigDecimal pfcp) {
		this.pfcp = pfcp;
	}

	public BigDecimal getVfcp() {
		return vfcp;
	}

	public void setVfcp(BigDecimal vfcp) {
		this.vfcp = vfcp;
	}

	public BigDecimal getVbcfcpst() {
		return vbcfcpst;
	}

	public void setVbcfcpst(BigDecimal vbcfcpst) {
		this.vbcfcpst = vbcfcpst;
	}

	public BigDecimal getPfcpst() {
		return pfcpst;
	}

	public void setPfcpst(BigDecimal pfcpst) {
		this.pfcpst = pfcpst;
	}

	public BigDecimal getVfcpst() {
		return vfcpst;
	}

	public void setVfcpst(BigDecimal vfcpst) {
		this.vfcpst = vfcpst;
	}

	public BigDecimal getVbcfcpstret() {
		return vbcfcpstret;
	}

	public void setVbcfcpstret(BigDecimal vbcfcpstret) {
		this.vbcfcpstret = vbcfcpstret;
	}

	public BigDecimal getPfcpstret() {
		return pfcpstret;
	}

	public void setPfcpstret(BigDecimal pfcpstret) {
		this.pfcpstret = pfcpstret;
	}

	public BigDecimal getVfcpstret() {
		return vfcpstret;
	}

	public void setVfcpstret(BigDecimal vfcpstret) {
		this.vfcpstret = vfcpstret;
	}

	public BigDecimal getPst() {
		return pst;
	}

	public void setPst(BigDecimal pst) {
		this.pst = pst;
	}

	public BigDecimal getPfcpdif() {
		return pfcpdif;
	}

	public void setPfcpdif(BigDecimal pfcpdif) {
		this.pfcpdif = pfcpdif;
	}

	public BigDecimal getVfcpdif() {
		return vfcpdif;
	}

	public void setVfcpdif(BigDecimal vfcpdif) {
		this.vfcpdif = vfcpdif;
	}

	public BigDecimal getVfcpefet() {
		return vfcpefet;
	}

	public void setVfcpefet(BigDecimal vfcpefet) {
		this.vfcpefet = vfcpefet;
	}

	public BigDecimal getVicmsstdeson() {
		return vicmsstdeson;
	}

	public void setVicmsstdeson(BigDecimal vicmsstdeson) {
		this.vicmsstdeson = vicmsstdeson;
	}

	public Integer getMotdesicmsst() {
		return motdesicmsst;
	}

	public void setMotdesicmsst(Integer motdesicmsst) {
		this.motdesicmsst = motdesicmsst;
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
		FsNfeItemIcms other = (FsNfeItemIcms) obj;
		return Objects.equals(id, other.id);
	}
}
