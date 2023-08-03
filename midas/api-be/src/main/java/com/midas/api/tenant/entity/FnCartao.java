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
@Table(name = "fn_cartao")
public class FnCartao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fncartaoitem" }, allowSetters = true)
	@ManyToOne
	private FnCxmv fncxmv;
	@ManyToOne
	private CdCartao cdcartao;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataprev = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	private Integer qtdparc;
	private Integer parcnum;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vsub;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vdesc;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vjuro;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vtot;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal ptaxa;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vtaxa;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vfinal;
	private Integer transacao;
	@Column(length = 2)
	private String status;

	public FnCartao() {
	}

	public FnCartao(Long id, FnCxmv fncxmv, CdCartao cdcartao, Date datacad, Date dataprev, Date dataat,
			Integer qtdparc, Integer parcnum, @Digits(integer = 18, fraction = 2) BigDecimal vsub,
			@Digits(integer = 18, fraction = 2) BigDecimal vdesc, @Digits(integer = 18, fraction = 2) BigDecimal vjuro,
			@Digits(integer = 18, fraction = 2) BigDecimal vtot, @Digits(integer = 3, fraction = 2) BigDecimal ptaxa,
			@Digits(integer = 18, fraction = 2) BigDecimal vtaxa, @Digits(integer = 18, fraction = 2) BigDecimal vfinal,
			Integer transacao, String status) {
		super();
		this.id = id;
		this.fncxmv = fncxmv;
		this.cdcartao = cdcartao;
		this.datacad = datacad;
		this.dataprev = dataprev;
		this.dataat = dataat;
		this.qtdparc = qtdparc;
		this.parcnum = parcnum;
		this.vsub = vsub;
		this.vdesc = vdesc;
		this.vjuro = vjuro;
		this.vtot = vtot;
		this.ptaxa = ptaxa;
		this.vtaxa = vtaxa;
		this.vfinal = vfinal;
		this.transacao = transacao;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FnCxmv getFncxmv() {
		return fncxmv;
	}

	public void setFncxmv(FnCxmv fncxmv) {
		this.fncxmv = fncxmv;
	}

	public CdCartao getCdcartao() {
		return cdcartao;
	}

	public void setCdcartao(CdCartao cdcartao) {
		this.cdcartao = cdcartao;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Date getDataprev() {
		return dataprev;
	}

	public void setDataprev(Date dataprev) {
		this.dataprev = dataprev;
	}

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public Integer getQtdparc() {
		return qtdparc;
	}

	public void setQtdparc(Integer qtdparc) {
		this.qtdparc = qtdparc;
	}

	public Integer getParcnum() {
		return parcnum;
	}

	public void setParcnum(Integer parcnum) {
		this.parcnum = parcnum;
	}

	public BigDecimal getVsub() {
		return vsub;
	}

	public void setVsub(BigDecimal vsub) {
		this.vsub = vsub;
	}

	public BigDecimal getVdesc() {
		return vdesc;
	}

	public void setVdesc(BigDecimal vdesc) {
		this.vdesc = vdesc;
	}

	public BigDecimal getVjuro() {
		return vjuro;
	}

	public void setVjuro(BigDecimal vjuro) {
		this.vjuro = vjuro;
	}

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
	}

	public BigDecimal getPtaxa() {
		return ptaxa;
	}

	public void setPtaxa(BigDecimal ptaxa) {
		this.ptaxa = ptaxa;
	}

	public BigDecimal getVtaxa() {
		return vtaxa;
	}

	public void setVtaxa(BigDecimal vtaxa) {
		this.vtaxa = vtaxa;
	}

	public BigDecimal getVfinal() {
		return vfinal;
	}

	public void setVfinal(BigDecimal vfinal) {
		this.vfinal = vfinal;
	}

	public Integer getTransacao() {
		return transacao;
	}

	public void setTransacao(Integer transacao) {
		this.transacao = transacao;
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
		FnCartao other = (FnCartao) obj;
		return Objects.equals(id, other.id);
	}
}
