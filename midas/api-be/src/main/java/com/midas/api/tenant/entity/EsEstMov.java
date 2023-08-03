package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "es_estmov")
public class EsEstMov implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdPessoa cdpessoapara;
	@ManyToOne
	private CdProduto cdproduto;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	private Time hora = new Time(new Date().getTime());
	@Column(length = 2)
	private String tpdoc;
	@Column(length = 2)
	private String lctpdoc;
	private Long iddoc;
	private Long iddocitem;
	private Long numdoc;
	@Column(length = 1)
	private String tipo;
	@Digits(integer = 18, fraction = 8)
	private BigDecimal qtdini;
	@Digits(integer = 18, fraction = 8)
	private BigDecimal qtd;
	@Digits(integer = 18, fraction = 8)
	private BigDecimal qtdfim;
	@Digits(integer = 21, fraction = 10)
	private BigDecimal vuni;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vsub;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vfrete;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vseg;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vicms;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vicmsst;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vfcpst;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vipi;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vpis;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vcofins;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal voutro;
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vtot;
	private Integer cdmaqequip_id = 0;
	@Column(length = 250)
	private String info;

	public EsEstMov() {
	}

	public EsEstMov(Long id, CdPessoa cdpessoaemp, CdPessoa cdpessoapara, CdProduto cdproduto, Date data, Time hora,
			String tpdoc, String lctpdoc, Long iddoc, Long iddocitem, Long numdoc, String tipo,
			@Digits(integer = 18, fraction = 8) BigDecimal qtdini, @Digits(integer = 18, fraction = 8) BigDecimal qtd,
			@Digits(integer = 18, fraction = 8) BigDecimal qtdfim, @Digits(integer = 21, fraction = 10) BigDecimal vuni,
			@Digits(integer = 18, fraction = 6) BigDecimal vsub, @Digits(integer = 18, fraction = 6) BigDecimal vfrete,
			@Digits(integer = 18, fraction = 6) BigDecimal vseg, @Digits(integer = 18, fraction = 6) BigDecimal vicms,
			@Digits(integer = 18, fraction = 6) BigDecimal vicmsst,
			@Digits(integer = 18, fraction = 6) BigDecimal vfcpst, @Digits(integer = 18, fraction = 6) BigDecimal vipi,
			@Digits(integer = 18, fraction = 6) BigDecimal vpis, @Digits(integer = 18, fraction = 6) BigDecimal vcofins,
			@Digits(integer = 18, fraction = 6) BigDecimal voutro, @Digits(integer = 18, fraction = 6) BigDecimal vtot,
			Integer cdmaqequip_id, String info) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdpessoapara = cdpessoapara;
		this.cdproduto = cdproduto;
		this.data = data;
		this.hora = hora;
		this.tpdoc = tpdoc;
		this.lctpdoc = lctpdoc;
		this.iddoc = iddoc;
		this.iddocitem = iddocitem;
		this.numdoc = numdoc;
		this.tipo = tipo;
		this.qtdini = qtdini;
		this.qtd = qtd;
		this.qtdfim = qtdfim;
		this.vuni = vuni;
		this.vsub = vsub;
		this.vfrete = vfrete;
		this.vseg = vseg;
		this.vicms = vicms;
		this.vicmsst = vicmsst;
		this.vfcpst = vfcpst;
		this.vipi = vipi;
		this.vpis = vpis;
		this.vcofins = vcofins;
		this.voutro = voutro;
		this.vtot = vtot;
		this.cdmaqequip_id = cdmaqequip_id;
		this.info = info;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public CdPessoa getCdpessoapara() {
		return cdpessoapara;
	}

	public void setCdpessoapara(CdPessoa cdpessoapara) {
		this.cdpessoapara = cdpessoapara;
	}

	public CdProduto getCdproduto() {
		return cdproduto;
	}

	public void setCdproduto(CdProduto cdproduto) {
		this.cdproduto = cdproduto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getTpdoc() {
		return tpdoc;
	}

	public void setTpdoc(String tpdoc) {
		this.tpdoc = tpdoc;
	}

	public String getLctpdoc() {
		return lctpdoc;
	}

	public void setLctpdoc(String lctpdoc) {
		this.lctpdoc = lctpdoc;
	}

	public Long getIddoc() {
		return iddoc;
	}

	public void setIddoc(Long iddoc) {
		this.iddoc = iddoc;
	}

	public Long getIddocitem() {
		return iddocitem;
	}

	public void setIddocitem(Long iddocitem) {
		this.iddocitem = iddocitem;
	}

	public Long getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(Long numdoc) {
		this.numdoc = numdoc;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getQtdini() {
		return qtdini;
	}

	public void setQtdini(BigDecimal qtdini) {
		this.qtdini = qtdini;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getQtdfim() {
		return qtdfim;
	}

	public void setQtdfim(BigDecimal qtdfim) {
		this.qtdfim = qtdfim;
	}

	public BigDecimal getVuni() {
		return vuni;
	}

	public void setVuni(BigDecimal vuni) {
		this.vuni = vuni;
	}

	public BigDecimal getVsub() {
		return vsub;
	}

	public void setVsub(BigDecimal vsub) {
		this.vsub = vsub;
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

	public BigDecimal getVicms() {
		return vicms;
	}

	public void setVicms(BigDecimal vicms) {
		this.vicms = vicms;
	}

	public BigDecimal getVicmsst() {
		return vicmsst;
	}

	public void setVicmsst(BigDecimal vicmsst) {
		this.vicmsst = vicmsst;
	}

	public BigDecimal getVfcpst() {
		return vfcpst;
	}

	public void setVfcpst(BigDecimal vfcpst) {
		this.vfcpst = vfcpst;
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

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public Integer getCdmaqequip_id() {
		return cdmaqequip_id;
	}

	public void setCdmaqequip_id(Integer cdmaqequip_id) {
		this.cdmaqequip_id = cdmaqequip_id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = CaracterUtil.remUpper(info);
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
		EsEstMov other = (EsEstMov) obj;
		return Objects.equals(id, other.id);
	}
}
