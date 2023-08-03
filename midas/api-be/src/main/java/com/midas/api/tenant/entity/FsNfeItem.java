package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_nfeitem")
public class FsNfeItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fsnfeitems" }, allowSetters = true)
	@ManyToOne
	private FsNfe fsnfe;
	@Column(length = 60)
	private String cprod;
	@Column(length = 14)
	private String cean = "SEM GTIN";
	@Column(length = 120)
	private String xprod;
	@Column(length = 8)
	private String ncm;
	@Column(length = 7)
	private String cest;
	@Column(length = 1)
	private String indescala;
	@Column(length = 20)
	private String cnpjfab = "00000000000000";
	@Column(length = 4)
	private String cfop;
	@Column(length = 6)
	private String ucom;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal qcom = BigDecimal.ZERO;
	@Digits(integer = 21, fraction = 10)
	private BigDecimal vuncom = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vprod = BigDecimal.ZERO;
	@Column(length = 14)
	private String ceantrib = "SEM GTIN";
	@Column(length = 6)
	private String utrib;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal qtrib = BigDecimal.ZERO;
	@Digits(integer = 21, fraction = 10)
	private BigDecimal vuntrib = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vfrete = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vseg = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vdesc = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal voutro = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vtot = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vtottrib = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal vdescext = BigDecimal.ZERO; // Apenas no final, nao desconto do vtot
	private Integer indtot;
	@Column(length = 15)
	private String xped;
	@Column(length = 6)
	private String nitemped;
	@Column(length = 9)
	private String cprodanp;
	@Column(length = 95)
	private String descanp;
	@Column(length = 2)
	private String ufcons;
	@Column(length = 12)
	private String cbenef;
	@OneToOne(orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FsNfeItemIcms fsnfeitemicms;
	@OneToOne(orphanRemoval = true)
	private FsNfeItemPis fsnfeitempis;
	@OneToOne(orphanRemoval = true)
	private FsNfeItemCofins fsnfeitemcofins;
	@OneToOne(orphanRemoval = true)
	private FsNfeItemIpi fsnfeitemipi;
	private Long idprod;
	@Column(length = 120)
	private String xprodconv;
	@Column(length = 4)
	private String cfopconv;
	@Digits(integer = 15, fraction = 6)
	private BigDecimal qtdconv = BigDecimal.ZERO;
	@Column(length = 6)
	private String uconv;
	@Digits(integer = 21, fraction = 10)
	private BigDecimal vconv = BigDecimal.ZERO;
	@ManyToOne
	private CdNfeCfg cdnfecfg;
	@Transient
	private String orig = "0";
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_nacional = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_importado = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_estadual = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vibpt_municipal = BigDecimal.ZERO;

	public FsNfeItem() {
	}

	public FsNfeItem(Long id, FsNfe fsnfe, String cprod, String cean, String xprod, String ncm, String cest,
			String indescala, String cnpjfab, String cfop, String ucom,
			@Digits(integer = 15, fraction = 6) BigDecimal qcom, @Digits(integer = 21, fraction = 10) BigDecimal vuncom,
			@Digits(integer = 15, fraction = 6) BigDecimal vprod, String ceantrib, String utrib,
			@Digits(integer = 15, fraction = 6) BigDecimal qtrib,
			@Digits(integer = 21, fraction = 10) BigDecimal vuntrib,
			@Digits(integer = 15, fraction = 6) BigDecimal vfrete, @Digits(integer = 15, fraction = 6) BigDecimal vseg,
			@Digits(integer = 15, fraction = 6) BigDecimal vdesc, @Digits(integer = 15, fraction = 6) BigDecimal voutro,
			@Digits(integer = 15, fraction = 6) BigDecimal vtot,
			@Digits(integer = 15, fraction = 6) BigDecimal vtottrib,
			@Digits(integer = 15, fraction = 6) BigDecimal vdescext, Integer indtot, String xped, String nitemped,
			String cprodanp, String descanp, String ufcons, String cbenef, FsNfeItemIcms fsnfeitemicms,
			FsNfeItemPis fsnfeitempis, FsNfeItemCofins fsnfeitemcofins, FsNfeItemIpi fsnfeitemipi, Long idprod,
			String xprodconv, String cfopconv, @Digits(integer = 15, fraction = 6) BigDecimal qtdconv, String uconv,
			@Digits(integer = 21, fraction = 10) BigDecimal vconv, CdNfeCfg cdnfecfg, String orig,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_nacional,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_importado,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_estadual,
			@Digits(integer = 15, fraction = 2) BigDecimal vibpt_municipal) {
		super();
		this.id = id;
		this.fsnfe = fsnfe;
		this.cprod = cprod;
		this.cean = cean;
		this.xprod = xprod;
		this.ncm = ncm;
		this.cest = cest;
		this.indescala = indescala;
		this.cnpjfab = cnpjfab;
		this.cfop = cfop;
		this.ucom = ucom;
		this.qcom = qcom;
		this.vuncom = vuncom;
		this.vprod = vprod;
		this.ceantrib = ceantrib;
		this.utrib = utrib;
		this.qtrib = qtrib;
		this.vuntrib = vuntrib;
		this.vfrete = vfrete;
		this.vseg = vseg;
		this.vdesc = vdesc;
		this.voutro = voutro;
		this.vtot = vtot;
		this.vtottrib = vtottrib;
		this.vdescext = vdescext;
		this.indtot = indtot;
		this.xped = xped;
		this.nitemped = nitemped;
		this.cprodanp = cprodanp;
		this.descanp = descanp;
		this.ufcons = ufcons;
		this.cbenef = cbenef;
		this.fsnfeitemicms = fsnfeitemicms;
		this.fsnfeitempis = fsnfeitempis;
		this.fsnfeitemcofins = fsnfeitemcofins;
		this.fsnfeitemipi = fsnfeitemipi;
		this.idprod = idprod;
		this.xprodconv = xprodconv;
		this.cfopconv = cfopconv;
		this.qtdconv = qtdconv;
		this.uconv = uconv;
		this.vconv = vconv;
		this.cdnfecfg = cdnfecfg;
		this.orig = orig;
		this.vibpt_nacional = vibpt_nacional;
		this.vibpt_importado = vibpt_importado;
		this.vibpt_estadual = vibpt_estadual;
		this.vibpt_municipal = vibpt_municipal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsNfe getFsnfe() {
		return fsnfe;
	}

	public void setFsnfe(FsNfe fsnfe) {
		this.fsnfe = fsnfe;
	}

	public String getCprod() {
		return cprod;
	}

	public void setCprod(String cprod) {
		this.cprod = CaracterUtil.remUpper(cprod);
	}

	public String getCean() {
		return cean;
	}

	public void setCean(String cean) {
		this.cean = CaracterUtil.remUpper(cean);
	}

	public String getXprod() {
		return xprod;
	}

	public void setXprod(String xprod) {
		this.xprod = CaracterUtil.remUpper(xprod);
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getCest() {
		return cest;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public String getIndescala() {
		return indescala;
	}

	public void setIndescala(String indescala) {
		this.indescala = indescala;
	}

	public String getCnpjfab() {
		return cnpjfab;
	}

	public void setCnpjfab(String cnpjfab) {
		this.cnpjfab = cnpjfab;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getUcom() {
		return ucom;
	}

	public void setUcom(String ucom) {
		this.ucom = CaracterUtil.remUpper(ucom);
	}

	public BigDecimal getQcom() {
		return qcom;
	}

	public void setQcom(BigDecimal qcom) {
		this.qcom = qcom;
	}

	public BigDecimal getVuncom() {
		return vuncom;
	}

	public void setVuncom(BigDecimal vuncom) {
		this.vuncom = vuncom;
	}

	public BigDecimal getVprod() {
		return vprod;
	}

	public void setVprod(BigDecimal vprod) {
		this.vprod = vprod;
	}

	public String getCeantrib() {
		return ceantrib;
	}

	public void setCeantrib(String ceantrib) {
		this.ceantrib = CaracterUtil.remUpper(ceantrib);
	}

	public String getUtrib() {
		return utrib;
	}

	public void setUtrib(String utrib) {
		this.utrib = CaracterUtil.remUpper(utrib);
	}

	public BigDecimal getQtrib() {
		return qtrib;
	}

	public void setQtrib(BigDecimal qtrib) {
		this.qtrib = qtrib;
	}

	public BigDecimal getVuntrib() {
		return vuntrib;
	}

	public void setVuntrib(BigDecimal vuntrib) {
		this.vuntrib = vuntrib;
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

	public BigDecimal getVoutro() {
		return voutro;
	}

	public void setVoutro(BigDecimal voutro) {
		this.voutro = voutro;
	}

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public BigDecimal getVtottrib() {
		return vtottrib;
	}

	public void setVtottrib(BigDecimal vtottrib) {
		this.vtottrib = vtottrib;
	}

	public BigDecimal getVdescext() {
		return vdescext;
	}

	public void setVdescext(BigDecimal vdescext) {
		this.vdescext = vdescext;
	}

	public Integer getIndtot() {
		return indtot;
	}

	public void setIndtot(Integer indtot) {
		this.indtot = indtot;
	}

	public String getXped() {
		return xped;
	}

	public void setXped(String xped) {
		this.xped = CaracterUtil.remUpper(xped);
	}

	public String getNitemped() {
		return nitemped;
	}

	public void setNitemped(String nitemped) {
		this.nitemped = CaracterUtil.remUpper(nitemped);
	}

	public String getCprodanp() {
		return cprodanp;
	}

	public void setCprodanp(String cprodanp) {
		this.cprodanp = cprodanp;
	}

	public String getDescanp() {
		return descanp;
	}

	public void setDescanp(String descanp) {
		this.descanp = descanp;
	}

	public String getUfcons() {
		return ufcons;
	}

	public void setUfcons(String ufcons) {
		this.ufcons = ufcons;
	}

	public String getCbenef() {
		return cbenef;
	}

	public void setCbenef(String cbenef) {
		this.cbenef = cbenef;
	}

	public FsNfeItemIcms getFsnfeitemicms() {
		return fsnfeitemicms;
	}

	public void setFsnfeitemicms(FsNfeItemIcms fsnfeitemicms) {
		this.fsnfeitemicms = fsnfeitemicms;
	}

	public FsNfeItemPis getFsnfeitempis() {
		return fsnfeitempis;
	}

	public void setFsnfeitempis(FsNfeItemPis fsnfeitempis) {
		this.fsnfeitempis = fsnfeitempis;
	}

	public FsNfeItemCofins getFsnfeitemcofins() {
		return fsnfeitemcofins;
	}

	public void setFsnfeitemcofins(FsNfeItemCofins fsnfeitemcofins) {
		this.fsnfeitemcofins = fsnfeitemcofins;
	}

	public FsNfeItemIpi getFsnfeitemipi() {
		return fsnfeitemipi;
	}

	public void setFsnfeitemipi(FsNfeItemIpi fsnfeitemipi) {
		this.fsnfeitemipi = fsnfeitemipi;
	}

	public Long getIdprod() {
		return idprod;
	}

	public void setIdprod(Long idprod) {
		this.idprod = idprod;
	}

	public String getXprodconv() {
		return xprodconv;
	}

	public void setXprodconv(String xprodconv) {
		this.xprodconv = xprodconv;
	}

	public String getCfopconv() {
		return cfopconv;
	}

	public void setCfopconv(String cfopconv) {
		this.cfopconv = cfopconv;
	}

	public BigDecimal getQtdconv() {
		return qtdconv;
	}

	public void setQtdconv(BigDecimal qtdconv) {
		this.qtdconv = qtdconv;
	}

	public String getUconv() {
		return uconv;
	}

	public void setUconv(String uconv) {
		this.uconv = uconv;
	}

	public BigDecimal getVconv() {
		return vconv;
	}

	public void setVconv(BigDecimal vconv) {
		this.vconv = vconv;
	}

	public CdNfeCfg getCdnfecfg() {
		return cdnfecfg;
	}

	public void setCdnfecfg(CdNfeCfg cdnfecfg) {
		this.cdnfecfg = cdnfecfg;
	}

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
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
		FsNfeItem other = (FsNfeItem) obj;
		return Objects.equals(id, other.id);
	}
}
