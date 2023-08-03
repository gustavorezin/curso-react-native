package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "lc_docitem")
public class LcDocItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "lcdocitem" }, allowSetters = true)
	@ManyToOne
	private LcDoc lcdoc;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@ManyToOne
	private CdProduto cdproduto;
	@ManyToOne
	private CdNfeCfg cdnfecfg;
	@Column(length = 120)
	private String descricao;
	@Column(length = 60)
	private String ref;
	@Column(length = 60)
	private String localaplic;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal qtd = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal qtdtrib = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 10)
	private BigDecimal vuni = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 10)
	private BigDecimal vunitrib = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vsub = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal pdesc = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vdesc = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vtot = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcipi = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pipi = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vipi = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcicmsst = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal picmsst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vicmsst = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcfcpst = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pfcpst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vfcpst = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vtottrib = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcpis = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal ppis = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vpis = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcpisst = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal ppisst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vpisst = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbccofins = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pcofins = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vcofins = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbccofinsst = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pcofinsst = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vcofinsst = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbcicms = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal picms = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vicms = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbccredsn = BigDecimal.ZERO;
	@Digits(integer = 5, fraction = 4)
	private BigDecimal pcredsn = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vcredsn = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pfcpufdest = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal picmsufdest = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal picmsinter = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal picmsinterpart = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vfcpufdest = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsufdest = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vicmsufremet = BigDecimal.ZERO;
	@Digits(integer = 12, fraction = 6)
	private BigDecimal vfrete = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vtribcob = BigDecimal.ZERO;
	@Digits(integer = 20, fraction = 8)
	private BigDecimal mpesol = BigDecimal.ZERO;
	@Digits(integer = 20, fraction = 8)
	private BigDecimal mpesob = BigDecimal.ZERO;
	@Digits(integer = 24, fraction = 8)
	private BigDecimal mmcub = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_nacional = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_importado = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_estadual = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_municipal = BigDecimal.ZERO;
	@ManyToOne
	private CdPessoa cdpessoatec;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vbciss = BigDecimal.ZERO;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal piss = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal viss = BigDecimal.ZERO;
	@Column(length = 20)
	private String ns;
	@JsonIgnoreProperties(value = { "lcdocitem" }, allowSetters = true)
	@OneToMany(mappedBy = "lcdocitem", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<LcDocItemCot> lcdocitemcot = new ArrayList<LcDocItemCot>();

	public LcDocItem() {
	}

	public LcDocItem(Long id, LcDoc lcdoc, Date datacad, Date dataat, CdProduto cdproduto, CdNfeCfg cdnfecfg,
			String descricao, String ref, String localaplic, @Digits(integer = 18, fraction = 6) BigDecimal qtd,
			@Digits(integer = 18, fraction = 6) BigDecimal qtdtrib,
			@Digits(integer = 18, fraction = 10) BigDecimal vuni,
			@Digits(integer = 18, fraction = 10) BigDecimal vunitrib,
			@Digits(integer = 18, fraction = 6) BigDecimal vsub, @Digits(integer = 18, fraction = 6) BigDecimal pdesc,
			@Digits(integer = 18, fraction = 6) BigDecimal vdesc, @Digits(integer = 18, fraction = 6) BigDecimal vtot,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcipi, @Digits(integer = 3, fraction = 2) BigDecimal pipi,
			@Digits(integer = 12, fraction = 6) BigDecimal vipi,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcicmsst,
			@Digits(integer = 3, fraction = 2) BigDecimal picmsst,
			@Digits(integer = 12, fraction = 6) BigDecimal vicmsst,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcfcpst,
			@Digits(integer = 3, fraction = 2) BigDecimal pfcpst, @Digits(integer = 12, fraction = 6) BigDecimal vfcpst,
			@Digits(integer = 18, fraction = 6) BigDecimal vtottrib,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcpis, @Digits(integer = 3, fraction = 2) BigDecimal ppis,
			@Digits(integer = 12, fraction = 6) BigDecimal vpis,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcpisst,
			@Digits(integer = 3, fraction = 2) BigDecimal ppisst, @Digits(integer = 12, fraction = 6) BigDecimal vpisst,
			@Digits(integer = 18, fraction = 6) BigDecimal vbccofins,
			@Digits(integer = 3, fraction = 2) BigDecimal pcofins,
			@Digits(integer = 12, fraction = 6) BigDecimal vcofins,
			@Digits(integer = 18, fraction = 6) BigDecimal vbccofinsst,
			@Digits(integer = 3, fraction = 2) BigDecimal pcofinsst,
			@Digits(integer = 12, fraction = 6) BigDecimal vcofinsst,
			@Digits(integer = 18, fraction = 6) BigDecimal vbcicms, @Digits(integer = 3, fraction = 2) BigDecimal picms,
			@Digits(integer = 12, fraction = 6) BigDecimal vicms,
			@Digits(integer = 18, fraction = 6) BigDecimal vbccredsn,
			@Digits(integer = 5, fraction = 4) BigDecimal pcredsn,
			@Digits(integer = 12, fraction = 6) BigDecimal vcredsn,
			@Digits(integer = 3, fraction = 2) BigDecimal pfcpufdest,
			@Digits(integer = 3, fraction = 2) BigDecimal picmsufdest,
			@Digits(integer = 3, fraction = 2) BigDecimal picmsinter,
			@Digits(integer = 3, fraction = 2) BigDecimal picmsinterpart,
			@Digits(integer = 15, fraction = 6) BigDecimal vfcpufdest,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsufdest,
			@Digits(integer = 15, fraction = 6) BigDecimal vicmsufremet,
			@Digits(integer = 12, fraction = 6) BigDecimal vfrete,
			@Digits(integer = 18, fraction = 6) BigDecimal vtribcob,
			@Digits(integer = 20, fraction = 8) BigDecimal mpesol,
			@Digits(integer = 20, fraction = 8) BigDecimal mpesob, @Digits(integer = 24, fraction = 8) BigDecimal mmcub,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_nacional,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_importado,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_estadual,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_municipal, CdPessoa cdpessoatec,
			@Digits(integer = 18, fraction = 6) BigDecimal vbciss, @Digits(integer = 3, fraction = 2) BigDecimal piss,
			@Digits(integer = 18, fraction = 6) BigDecimal viss, String ns, List<LcDocItemCot> lcdocitemcot) {
		super();
		this.id = id;
		this.lcdoc = lcdoc;
		this.datacad = datacad;
		this.dataat = dataat;
		this.cdproduto = cdproduto;
		this.cdnfecfg = cdnfecfg;
		this.descricao = descricao;
		this.ref = ref;
		this.localaplic = localaplic;
		this.qtd = qtd;
		this.qtdtrib = qtdtrib;
		this.vuni = vuni;
		this.vunitrib = vunitrib;
		this.vsub = vsub;
		this.pdesc = pdesc;
		this.vdesc = vdesc;
		this.vtot = vtot;
		this.vbcipi = vbcipi;
		this.pipi = pipi;
		this.vipi = vipi;
		this.vbcicmsst = vbcicmsst;
		this.picmsst = picmsst;
		this.vicmsst = vicmsst;
		this.vbcfcpst = vbcfcpst;
		this.pfcpst = pfcpst;
		this.vfcpst = vfcpst;
		this.vtottrib = vtottrib;
		this.vbcpis = vbcpis;
		this.ppis = ppis;
		this.vpis = vpis;
		this.vbcpisst = vbcpisst;
		this.ppisst = ppisst;
		this.vpisst = vpisst;
		this.vbccofins = vbccofins;
		this.pcofins = pcofins;
		this.vcofins = vcofins;
		this.vbccofinsst = vbccofinsst;
		this.pcofinsst = pcofinsst;
		this.vcofinsst = vcofinsst;
		this.vbcicms = vbcicms;
		this.picms = picms;
		this.vicms = vicms;
		this.vbccredsn = vbccredsn;
		this.pcredsn = pcredsn;
		this.vcredsn = vcredsn;
		this.pfcpufdest = pfcpufdest;
		this.picmsufdest = picmsufdest;
		this.picmsinter = picmsinter;
		this.picmsinterpart = picmsinterpart;
		this.vfcpufdest = vfcpufdest;
		this.vicmsufdest = vicmsufdest;
		this.vicmsufremet = vicmsufremet;
		this.vfrete = vfrete;
		this.vtribcob = vtribcob;
		this.mpesol = mpesol;
		this.mpesob = mpesob;
		this.mmcub = mmcub;
		this.vibpt_nacional = vibpt_nacional;
		this.vibpt_importado = vibpt_importado;
		this.vibpt_estadual = vibpt_estadual;
		this.vibpt_municipal = vibpt_municipal;
		this.cdpessoatec = cdpessoatec;
		this.vbciss = vbciss;
		this.piss = piss;
		this.viss = viss;
		this.ns = ns;
		this.lcdocitemcot = lcdocitemcot;
	}

	// Usado para duplicar itens
	public LcDocItem(LcDocItem lci) {
		super();
		this.id = lci.id;
		this.lcdoc = lci.lcdoc;
		this.datacad = lci.datacad;
		this.dataat = lci.dataat;
		this.cdproduto = lci.cdproduto;
		this.cdnfecfg = lci.cdnfecfg;
		this.descricao = lci.descricao;
		this.ref = lci.ref;
		this.localaplic = lci.localaplic;
		this.qtd = lci.qtd;
		this.vuni = lci.vuni;
		this.qtdtrib = lci.qtdtrib;
		this.vunitrib = lci.vunitrib;
		this.vsub = lci.vsub;
		this.pdesc = lci.pdesc;
		this.vdesc = lci.vdesc;
		this.vtot = lci.vtot;
		this.vbcipi = lci.vbcipi;
		this.pipi = lci.pipi;
		this.vipi = lci.vipi;
		this.vbcicmsst = lci.vbcicmsst;
		this.picmsst = lci.picmsst;
		this.vicmsst = lci.vicmsst;
		this.vbcfcpst = lci.vbcfcpst;
		this.pfcpst = lci.pfcpst;
		this.vfcpst = lci.vfcpst;
		this.vtottrib = lci.vtottrib;
		this.vbcpis = lci.vbcpis;
		this.ppis = lci.ppis;
		this.vpis = lci.vpis;
		this.vbcpisst = lci.vbcpisst;
		this.ppisst = lci.ppisst;
		this.vpisst = lci.vpisst;
		this.vbccofins = lci.vbccofins;
		this.pcofins = lci.pcofins;
		this.vcofins = lci.vcofins;
		this.vbccofinsst = lci.vbccofinsst;
		this.pcofinsst = lci.pcofinsst;
		this.vcofinsst = lci.vcofinsst;
		this.vbcicms = lci.vbcicms;
		this.picms = lci.picms;
		this.vicms = lci.vicms;
		this.vbccredsn = lci.vbccredsn;
		this.pcredsn = lci.pcredsn;
		this.vcredsn = lci.vcredsn;
		this.pfcpufdest = lci.pfcpufdest;
		this.picmsufdest = lci.picmsufdest;
		this.picmsinter = lci.picmsinter;
		this.picmsinter = lci.picmsinter;
		this.vfcpufdest = lci.vfcpufdest;
		this.vicmsufdest = lci.vicmsufdest;
		this.vicmsufremet = lci.vicmsufremet;
		this.vfrete = lci.vfrete;
		this.vtribcob = lci.vtribcob;
		this.mpesol = lci.mpesol;
		this.mpesob = lci.mpesob;
		this.mmcub = lci.mmcub;
		this.vibpt_nacional = lci.vibpt_nacional;
		this.vibpt_importado = lci.vibpt_importado;
		this.vibpt_estadual = lci.vibpt_estadual;
		this.vibpt_municipal = lci.vibpt_municipal;
		this.cdpessoatec = lci.cdpessoatec;
		this.vbciss = lci.vbciss;
		this.piss = lci.piss;
		this.viss = lci.viss;
		this.ns = lci.ns;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LcDoc getLcdoc() {
		return lcdoc;
	}

	public void setLcdoc(LcDoc lcdoc) {
		this.lcdoc = lcdoc;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public CdProduto getCdproduto() {
		return cdproduto;
	}

	public void setCdproduto(CdProduto cdproduto) {
		this.cdproduto = cdproduto;
	}

	public CdNfeCfg getCdnfecfg() {
		return cdnfecfg;
	}

	public void setCdnfecfg(CdNfeCfg cdnfecfg) {
		this.cdnfecfg = cdnfecfg;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = CaracterUtil.remUpper(descricao);
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = CaracterUtil.remUpper(ref);
	}

	public String getLocalaplic() {
		return localaplic;
	}

	public void setLocalaplic(String localaplic) {
		this.localaplic = CaracterUtil.remUpper(localaplic);
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getQtdtrib() {
		return qtdtrib;
	}

	public void setQtdtrib(BigDecimal qtdtrib) {
		this.qtdtrib = qtdtrib;
	}

	public BigDecimal getVuni() {
		return vuni;
	}

	public void setVuni(BigDecimal vuni) {
		this.vuni = vuni;
	}

	public BigDecimal getVunitrib() {
		return vunitrib;
	}

	public void setVunitrib(BigDecimal vunitrib) {
		this.vunitrib = vunitrib;
	}

	public BigDecimal getVsub() {
		return vsub;
	}

	public void setVsub(BigDecimal vsub) {
		this.vsub = vsub.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getPdesc() {
		return pdesc;
	}

	public void setPdesc(BigDecimal pdesc) {
		this.pdesc = pdesc;
	}

	public BigDecimal getVdesc() {
		return vdesc;
	}

	public void setVdesc(BigDecimal vdesc) {
		this.vdesc = vdesc.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot.setScale(2, RoundingMode.HALF_UP);
		;
	}

	public BigDecimal getVbcipi() {
		return vbcipi;
	}

	public void setVbcipi(BigDecimal vbcipi) {
		this.vbcipi = vbcipi;
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

	public BigDecimal getVbcicmsst() {
		return vbcicmsst;
	}

	public void setVbcicmsst(BigDecimal vbcicmsst) {
		this.vbcicmsst = vbcicmsst;
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

	public BigDecimal getVtottrib() {
		return vtottrib;
	}

	public void setVtottrib(BigDecimal vtottrib) {
		this.vtottrib = vtottrib;
	}

	public BigDecimal getVbcpis() {
		return vbcpis;
	}

	public void setVbcpis(BigDecimal vbcpis) {
		this.vbcpis = vbcpis;
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

	public BigDecimal getVbcpisst() {
		return vbcpisst;
	}

	public void setVbcpisst(BigDecimal vbcpisst) {
		this.vbcpisst = vbcpisst;
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

	public BigDecimal getVbccofins() {
		return vbccofins;
	}

	public void setVbccofins(BigDecimal vbccofins) {
		this.vbccofins = vbccofins;
	}

	public BigDecimal getPcofins() {
		return pcofins;
	}

	public void setPcofins(BigDecimal pcofins) {
		this.pcofins = pcofins;
	}

	public BigDecimal getVcofins() {
		return vcofins;
	}

	public void setVcofins(BigDecimal vcofins) {
		this.vcofins = vcofins;
	}

	public BigDecimal getVbccofinsst() {
		return vbccofinsst;
	}

	public void setVbccofinsst(BigDecimal vbccofinsst) {
		this.vbccofinsst = vbccofinsst;
	}

	public BigDecimal getPcofinsst() {
		return pcofinsst;
	}

	public void setPcofinsst(BigDecimal pcofinsst) {
		this.pcofinsst = pcofinsst;
	}

	public BigDecimal getVcofinsst() {
		return vcofinsst;
	}

	public void setVcofinsst(BigDecimal vcofinsst) {
		this.vcofinsst = vcofinsst;
	}

	public BigDecimal getVbcicms() {
		return vbcicms;
	}

	public void setVbcicms(BigDecimal vbcicms) {
		this.vbcicms = vbcicms;
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

	public BigDecimal getVbccredsn() {
		return vbccredsn;
	}

	public void setVbccredsn(BigDecimal vbccredsn) {
		this.vbccredsn = vbccredsn;
	}

	public BigDecimal getPcredsn() {
		return pcredsn;
	}

	public void setPcredsn(BigDecimal pcredsn) {
		this.pcredsn = pcredsn;
	}

	public BigDecimal getVcredsn() {
		return vcredsn;
	}

	public void setVcredsn(BigDecimal vcredsn) {
		this.vcredsn = vcredsn;
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

	public BigDecimal getVfcpufdest() {
		return vfcpufdest;
	}

	public void setVfcpufdest(BigDecimal vfcpufdest) {
		this.vfcpufdest = vfcpufdest;
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

	public BigDecimal getVfrete() {
		return vfrete;
	}

	public void setVfrete(BigDecimal vfrete) {
		this.vfrete = vfrete;
	}

	public BigDecimal getVtribcob() {
		return vtribcob;
	}

	public void setVtribcob(BigDecimal vtribcob) {
		this.vtribcob = vtribcob;
	}

	public BigDecimal getMpesol() {
		return mpesol;
	}

	public void setMpesol(BigDecimal mpesol) {
		this.mpesol = mpesol;
	}

	public BigDecimal getMpesob() {
		return mpesob;
	}

	public void setMpesob(BigDecimal mpesob) {
		this.mpesob = mpesob;
	}

	public BigDecimal getMmcub() {
		return mmcub;
	}

	public void setMmcub(BigDecimal mmcub) {
		this.mmcub = mmcub;
	}

	public BigDecimal getVibpt_nacional() {
		return vibpt_nacional;
	}

	public void setVibpt_nacional(BigDecimal vibpt_nacional) {
		this.vibpt_nacional = vibpt_nacional;
	}

	public BigDecimal getVibpt_importado() {
		return vibpt_importado;
	}

	public void setVibpt_importado(BigDecimal vibpt_importado) {
		this.vibpt_importado = vibpt_importado;
	}

	public BigDecimal getVibpt_estadual() {
		return vibpt_estadual;
	}

	public void setVibpt_estadual(BigDecimal vibpt_estadual) {
		this.vibpt_estadual = vibpt_estadual;
	}

	public BigDecimal getVibpt_municipal() {
		return vibpt_municipal;
	}

	public void setVibpt_municipal(BigDecimal vibpt_municipal) {
		this.vibpt_municipal = vibpt_municipal;
	}

	public CdPessoa getCdpessoatec() {
		return cdpessoatec;
	}

	public void setCdpessoatec(CdPessoa cdpessoatec) {
		this.cdpessoatec = cdpessoatec;
	}

	public BigDecimal getVbciss() {
		return vbciss;
	}

	public void setVbciss(BigDecimal vbciss) {
		this.vbciss = vbciss;
	}

	public BigDecimal getPiss() {
		return piss;
	}

	public void setPiss(BigDecimal piss) {
		this.piss = piss;
	}

	public BigDecimal getViss() {
		return viss;
	}

	public void setViss(BigDecimal viss) {
		this.viss = viss;
	}

	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}

	public List<LcDocItemCot> getLcdocitemcot() {
		return lcdocitemcot;
	}

	public void setLcdocitemcot(List<LcDocItemCot> lcdocitemcot) {
		this.lcdocitemcot = lcdocitemcot;
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
		LcDocItem other = (LcDocItem) obj;
		return Objects.equals(id, other.id);
	}
}
