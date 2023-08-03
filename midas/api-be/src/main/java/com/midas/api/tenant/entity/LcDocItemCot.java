package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lc_docitem_cot")
public class LcDocItemCot implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "lcdocitemcot" }, allowSetters = true)
	@ManyToOne
	private LcDocItem lcdocitem;
	@ManyToOne
	private CdPessoa cdpessoacot;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Digits(integer = 18, fraction = 6)
	private BigDecimal qtd = new BigDecimal(0);
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vuni = new BigDecimal(0);
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vsub = new BigDecimal(0);
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pdesc = new BigDecimal(0);
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vdesc = new BigDecimal(0);
	@Digits(integer = 18, fraction = 6)
	private BigDecimal vtot = new BigDecimal(0);
	@Column(length = 1)
	private String mpreco = "N";

	public LcDocItemCot() {
	}

	public LcDocItemCot(Long id, LcDocItem lcdocitem, CdPessoa cdpessoacot, Date datacad, Date dataat,
			@Digits(integer = 18, fraction = 6) BigDecimal qtd, @Digits(integer = 18, fraction = 6) BigDecimal vuni,
			@Digits(integer = 18, fraction = 6) BigDecimal vsub, @Digits(integer = 3, fraction = 2) BigDecimal pdesc,
			@Digits(integer = 18, fraction = 6) BigDecimal vdesc, @Digits(integer = 18, fraction = 6) BigDecimal vtot,
			String mpreco) {
		super();
		this.id = id;
		this.lcdocitem = lcdocitem;
		this.cdpessoacot = cdpessoacot;
		this.datacad = datacad;
		this.dataat = dataat;
		this.qtd = qtd;
		this.vuni = vuni;
		this.vsub = vsub;
		this.pdesc = pdesc;
		this.vdesc = vdesc;
		this.vtot = vtot;
		this.mpreco = mpreco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LcDocItem getLcdocitem() {
		return lcdocitem;
	}

	public void setLcdocitem(LcDocItem lcdocitem) {
		this.lcdocitem = lcdocitem;
	}

	public CdPessoa getCdpessoacot() {
		return cdpessoacot;
	}

	public void setCdpessoacot(CdPessoa cdpessoacot) {
		this.cdpessoacot = cdpessoacot;
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

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
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
		this.vdesc = vdesc;
	}

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public String getMpreco() {
		return mpreco;
	}

	public void setMpreco(String mpreco) {
		this.mpreco = mpreco;
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
		LcDocItemCot other = (LcDocItemCot) obj;
		return Objects.equals(id, other.id);
	}
}
