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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_nfecfg")
public class CdNfeCfg implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdEstado cdestadoaplic;
	@Column(length = 120)
	private String descricao;
	@Column(length = 4)
	private String cfop;
	@Column(length = 60)
	private String natop;
	@Column(length = 3)
	private String cst;
	@Column(length = 1)
	private String tipoop;
	private Integer crtdest = 0;
	@Column(length = 2)
	private String pis;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pis_aliq = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pis_aliqst = new BigDecimal(0);
	@Column(length = 2)
	private String cofins;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal cofins_aliq = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal cofins_aliqst = new BigDecimal(0);
	@Column(length = 1)
	private String aplicadescpc;
	@Column(length = 1)
	private String icms_modbc;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icms_aliq = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icms_redbc = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icms_mva = new BigDecimal(0);
	@Column(length = 1)
	private String icmsst_modbc;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icmsst_aliq = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icmsst_redbc = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icmsst_mva = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal icmsst_aliqfcp = new BigDecimal(0);
	@Column(length = 20)
	private String iest;
	@Column(length = 2)
	private String ipi;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal ipi_aliq = new BigDecimal(0);
	@Column(length = 3)
	private String ipi_classeenq;
	@Column(length = 3000)
	private String info;
	@Column(length = 1)
	private String usa_nfse = "N";
	@Column(length = 1)
	private String usa_nfce = "N";
	@Column(length = 1)
	private String incluimsgsn = "S";
	@Column(length = 40)
	private String chavecom_nfse;
	@Column(length = 4)
	private String natop_nfse;
	@Column(length = 2)
	private String regesptrib_nfse;
	@Column(length = 2)
	private String optsn_nfse;
	@Column(length = 2)
	private String inccult_nfse;
	@Column(length = 6)
	private String atvprin_nfse;
	@Column(length = 3000)
	private String descatvprin_nfse;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal iss_aliq_nfse = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal issret_aliq_nfse = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal csll_aliq_nfse = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pcargtrib_aliq_nfse = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pis_aliq_nfse = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal cofins_aliq_nfse = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal ir_aliq_nfse = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal inss_aliq_nfse = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pfcpufdest = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal picmsufdest = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal picmsinter = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal picmsinterpart = new BigDecimal(0);
	@Column(length = 1)
	private String finnfe = "1"; // 1 - Normal / 4 - Devolucao Etc
	@Column(length = 8)
	private String status;

	public CdNfeCfg() {
	}

	public CdNfeCfg(Integer id, CdPessoa cdpessoaemp, CdEstado cdestadoaplic, String descricao, String cfop,
			String natop, String cst, String tipoop, Integer crtdest, String pis,
			@Digits(integer = 3, fraction = 2) BigDecimal pis_aliq,
			@Digits(integer = 3, fraction = 2) BigDecimal pis_aliqst, String cofins,
			@Digits(integer = 3, fraction = 2) BigDecimal cofins_aliq,
			@Digits(integer = 3, fraction = 2) BigDecimal cofins_aliqst, String aplicadescpc, String icms_modbc,
			@Digits(integer = 3, fraction = 2) BigDecimal icms_aliq,
			@Digits(integer = 3, fraction = 2) BigDecimal icms_redbc,
			@Digits(integer = 3, fraction = 2) BigDecimal icms_mva, String icmsst_modbc,
			@Digits(integer = 3, fraction = 2) BigDecimal icmsst_aliq,
			@Digits(integer = 3, fraction = 2) BigDecimal icmsst_redbc,
			@Digits(integer = 3, fraction = 2) BigDecimal icmsst_mva,
			@Digits(integer = 3, fraction = 2) BigDecimal icmsst_aliqfcp, String iest, String ipi,
			@Digits(integer = 3, fraction = 2) BigDecimal ipi_aliq, String ipi_classeenq, String info, String usa_nfse,
			String usa_nfce, String incluimsgsn, String chavecom_nfse, String natop_nfse, String regesptrib_nfse,
			String optsn_nfse, String inccult_nfse, String atvprin_nfse, String descatvprin_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal iss_aliq_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal issret_aliq_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal csll_aliq_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal pcargtrib_aliq_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal pis_aliq_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal cofins_aliq_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal ir_aliq_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal inss_aliq_nfse,
			@Digits(integer = 3, fraction = 2) BigDecimal pfcpufdest,
			@Digits(integer = 3, fraction = 2) BigDecimal picmsufdest,
			@Digits(integer = 3, fraction = 2) BigDecimal picmsinter,
			@Digits(integer = 3, fraction = 2) BigDecimal picmsinterpart, String finnfe, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdestadoaplic = cdestadoaplic;
		this.descricao = descricao;
		this.cfop = cfop;
		this.natop = natop;
		this.cst = cst;
		this.tipoop = tipoop;
		this.crtdest = crtdest;
		this.pis = pis;
		this.pis_aliq = pis_aliq;
		this.pis_aliqst = pis_aliqst;
		this.cofins = cofins;
		this.cofins_aliq = cofins_aliq;
		this.cofins_aliqst = cofins_aliqst;
		this.aplicadescpc = aplicadescpc;
		this.icms_modbc = icms_modbc;
		this.icms_aliq = icms_aliq;
		this.icms_redbc = icms_redbc;
		this.icms_mva = icms_mva;
		this.icmsst_modbc = icmsst_modbc;
		this.icmsst_aliq = icmsst_aliq;
		this.icmsst_redbc = icmsst_redbc;
		this.icmsst_mva = icmsst_mva;
		this.icmsst_aliqfcp = icmsst_aliqfcp;
		this.iest = iest;
		this.ipi = ipi;
		this.ipi_aliq = ipi_aliq;
		this.ipi_classeenq = ipi_classeenq;
		this.info = info;
		this.usa_nfse = usa_nfse;
		this.usa_nfce = usa_nfce;
		this.incluimsgsn = incluimsgsn;
		this.chavecom_nfse = chavecom_nfse;
		this.natop_nfse = natop_nfse;
		this.regesptrib_nfse = regesptrib_nfse;
		this.optsn_nfse = optsn_nfse;
		this.inccult_nfse = inccult_nfse;
		this.atvprin_nfse = atvprin_nfse;
		this.descatvprin_nfse = descatvprin_nfse;
		this.iss_aliq_nfse = iss_aliq_nfse;
		this.issret_aliq_nfse = issret_aliq_nfse;
		this.csll_aliq_nfse = csll_aliq_nfse;
		this.pcargtrib_aliq_nfse = pcargtrib_aliq_nfse;
		this.pis_aliq_nfse = pis_aliq_nfse;
		this.cofins_aliq_nfse = cofins_aliq_nfse;
		this.ir_aliq_nfse = ir_aliq_nfse;
		this.inss_aliq_nfse = inss_aliq_nfse;
		this.pfcpufdest = pfcpufdest;
		this.picmsufdest = picmsufdest;
		this.picmsinter = picmsinter;
		this.picmsinterpart = picmsinterpart;
		this.finnfe = finnfe;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public CdEstado getCdestadoaplic() {
		return cdestadoaplic;
	}

	public void setCdestadoaplic(CdEstado cdestadoaplic) {
		this.cdestadoaplic = cdestadoaplic;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = CaracterUtil.remUpper(descricao);
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getNatop() {
		return natop;
	}

	public void setNatop(String natop) {
		this.natop = CaracterUtil.remUpper(natop);
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public String getTipoop() {
		return tipoop;
	}

	public void setTipoop(String tipoop) {
		this.tipoop = tipoop;
	}

	public Integer getCrtdest() {
		return crtdest;
	}

	public void setCrtdest(Integer crtdest) {
		this.crtdest = crtdest;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public BigDecimal getPis_aliq() {
		return pis_aliq;
	}

	public void setPis_aliq(BigDecimal pis_aliq) {
		this.pis_aliq = pis_aliq;
	}

	public BigDecimal getPis_aliqst() {
		return pis_aliqst;
	}

	public void setPis_aliqst(BigDecimal pis_aliqst) {
		this.pis_aliqst = pis_aliqst;
	}

	public String getCofins() {
		return cofins;
	}

	public void setCofins(String cofins) {
		this.cofins = cofins;
	}

	public BigDecimal getCofins_aliq() {
		return cofins_aliq;
	}

	public void setCofins_aliq(BigDecimal cofins_aliq) {
		this.cofins_aliq = cofins_aliq;
	}

	public BigDecimal getCofins_aliqst() {
		return cofins_aliqst;
	}

	public void setCofins_aliqst(BigDecimal cofins_aliqst) {
		this.cofins_aliqst = cofins_aliqst;
	}

	public String getAplicadescpc() {
		return aplicadescpc;
	}

	public void setAplicadescpc(String aplicadescpc) {
		this.aplicadescpc = aplicadescpc;
	}

	public String getIcms_modbc() {
		return icms_modbc;
	}

	public void setIcms_modbc(String icms_modbc) {
		this.icms_modbc = icms_modbc;
	}

	public BigDecimal getIcms_aliq() {
		return icms_aliq;
	}

	public void setIcms_aliq(BigDecimal icms_aliq) {
		this.icms_aliq = icms_aliq;
	}

	public BigDecimal getIcms_redbc() {
		return icms_redbc;
	}

	public void setIcms_redbc(BigDecimal icms_redbc) {
		this.icms_redbc = icms_redbc;
	}

	public BigDecimal getIcms_mva() {
		return icms_mva;
	}

	public void setIcms_mva(BigDecimal icms_mva) {
		this.icms_mva = icms_mva;
	}

	public String getIcmsst_modbc() {
		return icmsst_modbc;
	}

	public void setIcmsst_modbc(String icmsst_modbc) {
		this.icmsst_modbc = icmsst_modbc;
	}

	public BigDecimal getIcmsst_aliq() {
		return icmsst_aliq;
	}

	public void setIcmsst_aliq(BigDecimal icmsst_aliq) {
		this.icmsst_aliq = icmsst_aliq;
	}

	public BigDecimal getIcmsst_redbc() {
		return icmsst_redbc;
	}

	public void setIcmsst_redbc(BigDecimal icmsst_redbc) {
		this.icmsst_redbc = icmsst_redbc;
	}

	public BigDecimal getIcmsst_mva() {
		return icmsst_mva;
	}

	public void setIcmsst_mva(BigDecimal icmsst_mva) {
		this.icmsst_mva = icmsst_mva;
	}

	public BigDecimal getIcmsst_aliqfcp() {
		return icmsst_aliqfcp;
	}

	public void setIcmsst_aliqfcp(BigDecimal icmsst_aliqfcp) {
		this.icmsst_aliqfcp = icmsst_aliqfcp;
	}

	public String getIest() {
		return iest;
	}

	public void setIest(String iest) {
		this.iest = CaracterUtil.remPout(iest);
	}

	public String getIpi() {
		return ipi;
	}

	public void setIpi(String ipi) {
		this.ipi = ipi;
	}

	public BigDecimal getIpi_aliq() {
		return ipi_aliq;
	}

	public void setIpi_aliq(BigDecimal ipi_aliq) {
		this.ipi_aliq = ipi_aliq;
	}

	public String getIpi_classeenq() {
		return ipi_classeenq;
	}

	public void setIpi_classeenq(String ipi_classeenq) {
		this.ipi_classeenq = ipi_classeenq;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = CaracterUtil.remUpper(info);
	}

	public String getUsa_nfse() {
		return usa_nfse;
	}

	public void setUsa_nfse(String usa_nfse) {
		this.usa_nfse = usa_nfse;
	}

	public String getChavecom_nfse() {
		return chavecom_nfse;
	}

	public void setChavecom_nfse(String chavecom_nfse) {
		this.chavecom_nfse = chavecom_nfse;
	}

	public String getNatop_nfse() {
		return natop_nfse;
	}

	public void setNatop_nfse(String natop_nfse) {
		this.natop_nfse = natop_nfse;
	}

	public String getRegesptrib_nfse() {
		return regesptrib_nfse;
	}

	public void setRegesptrib_nfse(String regesptrib_nfse) {
		this.regesptrib_nfse = regesptrib_nfse;
	}

	public String getOptsn_nfse() {
		return optsn_nfse;
	}

	public void setOptsn_nfse(String optsn_nfse) {
		this.optsn_nfse = optsn_nfse;
	}

	public String getInccult_nfse() {
		return inccult_nfse;
	}

	public void setInccult_nfse(String inccult_nfse) {
		this.inccult_nfse = inccult_nfse;
	}

	public String getAtvprin_nfse() {
		return atvprin_nfse;
	}

	public void setAtvprin_nfse(String atvprin_nfse) {
		this.atvprin_nfse = atvprin_nfse;
	}

	public String getDescatvprin_nfse() {
		return descatvprin_nfse;
	}

	public void setDescatvprin_nfse(String descatvprin_nfse) {
		this.descatvprin_nfse = CaracterUtil.remUpper(descatvprin_nfse);
	}

	public BigDecimal getIss_aliq_nfse() {
		return iss_aliq_nfse;
	}

	public void setIss_aliq_nfse(BigDecimal iss_aliq_nfse) {
		this.iss_aliq_nfse = iss_aliq_nfse;
	}

	public BigDecimal getIssret_aliq_nfse() {
		return issret_aliq_nfse;
	}

	public void setIssret_aliq_nfse(BigDecimal issret_aliq_nfse) {
		this.issret_aliq_nfse = issret_aliq_nfse;
	}

	public BigDecimal getCsll_aliq_nfse() {
		return csll_aliq_nfse;
	}

	public void setCsll_aliq_nfse(BigDecimal csll_aliq_nfse) {
		this.csll_aliq_nfse = csll_aliq_nfse;
	}

	public BigDecimal getPcargtrib_aliq_nfse() {
		return pcargtrib_aliq_nfse;
	}

	public void setPcargtrib_aliq_nfse(BigDecimal pcargtrib_aliq_nfse) {
		this.pcargtrib_aliq_nfse = pcargtrib_aliq_nfse;
	}

	public BigDecimal getPis_aliq_nfse() {
		return pis_aliq_nfse;
	}

	public void setPis_aliq_nfse(BigDecimal pis_aliq_nfse) {
		this.pis_aliq_nfse = pis_aliq_nfse;
	}

	public BigDecimal getCofins_aliq_nfse() {
		return cofins_aliq_nfse;
	}

	public void setCofins_aliq_nfse(BigDecimal cofins_aliq_nfse) {
		this.cofins_aliq_nfse = cofins_aliq_nfse;
	}

	public BigDecimal getIr_aliq_nfse() {
		return ir_aliq_nfse;
	}

	public void setIr_aliq_nfse(BigDecimal ir_aliq_nfse) {
		this.ir_aliq_nfse = ir_aliq_nfse;
	}

	public BigDecimal getInss_aliq_nfse() {
		return inss_aliq_nfse;
	}

	public void setInss_aliq_nfse(BigDecimal inss_aliq_nfse) {
		this.inss_aliq_nfse = inss_aliq_nfse;
	}

	public String getUsa_nfce() {
		return usa_nfce;
	}

	public void setUsa_nfce(String usa_nfce) {
		this.usa_nfce = usa_nfce;
	}

	public String getIncluimsgsn() {
		return incluimsgsn;
	}

	public void setIncluimsgsn(String incluimsgsn) {
		this.incluimsgsn = incluimsgsn;
	}

	public BigDecimal getPfcpufdest() {
		return pfcpufdest;
	}

	public void setPfcpufdest(BigDecimal pfcpufdest) {
		this.pfcpufdest = pfcpufdest;
	}

	public BigDecimal getPicmsufdest() {
		return picmsufdest;
	}

	public void setPicmsufdest(BigDecimal picmsufdest) {
		this.picmsufdest = picmsufdest;
	}

	public BigDecimal getPicmsinter() {
		return picmsinter;
	}

	public void setPicmsinter(BigDecimal picmsinter) {
		this.picmsinter = picmsinter;
	}

	public BigDecimal getPicmsinterpart() {
		return picmsinterpart;
	}

	public void setPicmsinterpart(BigDecimal picmsinterpart) {
		this.picmsinterpart = picmsinterpart;
	}

	public String getFinnfe() {
		return finnfe;
	}

	public void setFinnfe(String finnfe) {
		this.finnfe = finnfe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		CdNfeCfg other = (CdNfeCfg) obj;
		return Objects.equals(id, other.id);
	}
}
