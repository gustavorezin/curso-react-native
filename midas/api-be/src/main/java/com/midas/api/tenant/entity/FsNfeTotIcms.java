package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "fs_nfetot_icms")
public class FsNfeTotIcms implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long fsnfe_id;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbc = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vicms = new BigDecimal(0);
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vbcst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vprod = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vfrete = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vseg = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vdesc = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vii = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vipi = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vpis = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vcofins = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal voutro = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vdescext = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vnf = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vtottrib = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vicmsdeson = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vicmsufdest = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vicmsufremet = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vfcpufdest = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vfcp = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vfcpst = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vfcpstret = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vipidevol = new BigDecimal(0);

	public FsNfeTotIcms() {
	}

	public FsNfeTotIcms(Long id, Long fsnfe_id, @Digits(integer = 15, fraction = 6) BigDecimal vbc,
			@Digits(integer = 15, fraction = 2) BigDecimal vicms, @Digits(integer = 15, fraction = 6) BigDecimal vbcst,
			@Digits(integer = 15, fraction = 2) BigDecimal vst, @Digits(integer = 15, fraction = 2) BigDecimal vprod,
			@Digits(integer = 15, fraction = 2) BigDecimal vfrete, @Digits(integer = 15, fraction = 2) BigDecimal vseg,
			@Digits(integer = 15, fraction = 2) BigDecimal vdesc, @Digits(integer = 15, fraction = 2) BigDecimal vii,
			@Digits(integer = 15, fraction = 2) BigDecimal vipi, @Digits(integer = 15, fraction = 2) BigDecimal vpis,
			@Digits(integer = 15, fraction = 2) BigDecimal vcofins,
			@Digits(integer = 15, fraction = 2) BigDecimal voutro,
			@Digits(integer = 15, fraction = 2) BigDecimal vdescext, @Digits(integer = 15, fraction = 2) BigDecimal vnf,
			@Digits(integer = 15, fraction = 2) BigDecimal vtottrib,
			@Digits(integer = 15, fraction = 2) BigDecimal vicmsdeson,
			@Digits(integer = 15, fraction = 2) BigDecimal vicmsufdest,
			@Digits(integer = 15, fraction = 2) BigDecimal vicmsufremet,
			@Digits(integer = 15, fraction = 2) BigDecimal vfcpufdest,
			@Digits(integer = 15, fraction = 2) BigDecimal vfcp, @Digits(integer = 15, fraction = 2) BigDecimal vfcpst,
			@Digits(integer = 15, fraction = 2) BigDecimal vfcpstret,
			@Digits(integer = 15, fraction = 2) BigDecimal vipidevol) {
		super();
		this.id = id;
		this.fsnfe_id = fsnfe_id;
		this.vbc = vbc;
		this.vicms = vicms;
		this.vbcst = vbcst;
		this.vst = vst;
		this.vprod = vprod;
		this.vfrete = vfrete;
		this.vseg = vseg;
		this.vdesc = vdesc;
		this.vii = vii;
		this.vipi = vipi;
		this.vpis = vpis;
		this.vcofins = vcofins;
		this.voutro = voutro;
		this.vdescext = vdescext;
		this.vnf = vnf;
		this.vtottrib = vtottrib;
		this.vicmsdeson = vicmsdeson;
		this.vicmsufdest = vicmsufdest;
		this.vicmsufremet = vicmsufremet;
		this.vfcpufdest = vfcpufdest;
		this.vfcp = vfcp;
		this.vfcpst = vfcpst;
		this.vfcpstret = vfcpstret;
		this.vipidevol = vipidevol;
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

	public BigDecimal getVbc() {
		return vbc;
	}

	public void setVbc(BigDecimal vbc) {
		this.vbc = vbc;
	}

	public BigDecimal getVicms() {
		return vicms;
	}

	public void setVicms(BigDecimal vicms) {
		this.vicms = vicms;
	}

	public BigDecimal getVbcst() {
		return vbcst;
	}

	public void setVbcst(BigDecimal vbcst) {
		this.vbcst = vbcst;
	}

	public BigDecimal getVst() {
		return vst;
	}

	public void setVst(BigDecimal vst) {
		this.vst = vst;
	}

	public BigDecimal getVprod() {
		return vprod;
	}

	public void setVprod(BigDecimal vprod) {
		this.vprod = vprod;
	}

	public BigDecimal getVfrete() {
		return vfrete;
	}

	public void setVfrete(BigDecimal vfrete) {
		this.vfrete = vfrete;
	}

	public BigDecimal getVseg() {
		return vseg;
	}

	public void setVseg(BigDecimal vseg) {
		this.vseg = vseg;
	}

	public BigDecimal getVdesc() {
		return vdesc;
	}

	public void setVdesc(BigDecimal vdesc) {
		this.vdesc = vdesc;
	}

	public BigDecimal getVii() {
		return vii;
	}

	public void setVii(BigDecimal vii) {
		this.vii = vii;
	}

	public BigDecimal getVipi() {
		return vipi;
	}

	public void setVipi(BigDecimal vipi) {
		this.vipi = vipi;
	}

	public BigDecimal getVpis() {
		return vpis;
	}

	public void setVpis(BigDecimal vpis) {
		this.vpis = vpis;
	}

	public BigDecimal getVcofins() {
		return vcofins;
	}

	public void setVcofins(BigDecimal vcofins) {
		this.vcofins = vcofins;
	}

	public BigDecimal getVoutro() {
		return voutro;
	}

	public void setVoutro(BigDecimal voutro) {
		this.voutro = voutro;
	}

	public BigDecimal getVdescext() {
		return vdescext;
	}

	public void setVdescext(BigDecimal vdescext) {
		this.vdescext = vdescext;
	}

	public BigDecimal getVnf() {
		return vnf;
	}

	public void setVnf(BigDecimal vnf) {
		this.vnf = vnf;
	}

	public BigDecimal getVtottrib() {
		return vtottrib;
	}

	public void setVtottrib(BigDecimal vtottrib) {
		this.vtottrib = vtottrib;
	}

	public BigDecimal getVicmsdeson() {
		return vicmsdeson;
	}

	public void setVicmsdeson(BigDecimal vicmsdeson) {
		this.vicmsdeson = vicmsdeson;
	}

	public BigDecimal getVicmsufdest() {
		return vicmsufdest;
	}

	public void setVicmsufdest(BigDecimal vicmsufdest) {
		this.vicmsufdest = vicmsufdest;
	}

	public BigDecimal getVicmsufremet() {
		return vicmsufremet;
	}

	public void setVicmsufremet(BigDecimal vicmsufremet) {
		this.vicmsufremet = vicmsufremet;
	}

	public BigDecimal getVfcpufdest() {
		return vfcpufdest;
	}

	public void setVfcpufdest(BigDecimal vfcpufdest) {
		this.vfcpufdest = vfcpufdest;
	}

	public BigDecimal getVfcp() {
		return vfcp;
	}

	public void setVfcp(BigDecimal vfcp) {
		this.vfcp = vfcp;
	}

	public BigDecimal getVfcpst() {
		return vfcpst;
	}

	public void setVfcpst(BigDecimal vfcpst) {
		this.vfcpst = vfcpst;
	}

	public BigDecimal getVfcpstret() {
		return vfcpstret;
	}

	public void setVfcpstret(BigDecimal vfcpstret) {
		this.vfcpstret = vfcpstret;
	}

	public BigDecimal getVipidevol() {
		return vipidevol;
	}

	public void setVipidevol(BigDecimal vipidevol) {
		this.vipidevol = vipidevol;
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
		FsNfeTotIcms other = (FsNfeTotIcms) obj;
		return Objects.equals(id, other.id);
	}
}
