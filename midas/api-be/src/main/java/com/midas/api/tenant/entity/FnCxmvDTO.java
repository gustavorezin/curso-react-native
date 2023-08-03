package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class FnCxmvDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private CdPessoa cdpessoaemp;
	private CdCaixa cdcaixa;
	private FnTitulo fntitulo;
	private String fpag;
	private Date datacad;
	private Time horacad;
	private Date dataat = new Date();
	private Time horaat = new Time(new Date().getTime());
	private String info;
	private BigDecimal vsub;
	private BigDecimal pdesc;
	private BigDecimal vdesc;
	private BigDecimal pjuro;
	private BigDecimal vjuro;
	private BigDecimal vtot;
	private Integer transacao;
	private String status;

	public FnCxmvDTO() {
	}

	public FnCxmvDTO(Long id, CdPessoa cdpessoaemp, CdCaixa cdcaixa, FnTitulo fntitulo, String fpag, Date datacad,
			Time horacad, Date dataat, Time horaat, String info, BigDecimal vsub, BigDecimal pdesc, BigDecimal vdesc,
			BigDecimal pjuro, BigDecimal vjuro, BigDecimal vtot, Integer transacao, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdcaixa = cdcaixa;
		this.fntitulo = fntitulo;
		this.fpag = fpag;
		this.datacad = datacad;
		this.horacad = horacad;
		this.dataat = dataat;
		this.horaat = horaat;
		this.info = info;
		this.vsub = vsub;
		this.pdesc = pdesc;
		this.vdesc = vdesc;
		this.pjuro = pjuro;
		this.vjuro = vjuro;
		this.vtot = vtot;
		this.transacao = transacao;
		this.status = status;
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

	public CdCaixa getCdcaixa() {
		return cdcaixa;
	}

	public void setCdcaixa(CdCaixa cdcaixa) {
		this.cdcaixa = cdcaixa;
	}

	public FnTitulo getFntitulo() {
		return fntitulo;
	}

	public void setFntitulo(FnTitulo fntitulo) {
		this.fntitulo = fntitulo;
	}

	public String getFpag() {
		return fpag;
	}

	public void setFpag(String fpag) {
		this.fpag = fpag;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Time getHoracad() {
		return horacad;
	}

	public void setHoracad(Time horacad) {
		this.horacad = horacad;
	}

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public Time getHoraat() {
		return horaat;
	}

	public void setHoraat(Time horaat) {
		this.horaat = horaat;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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

	public BigDecimal getPjuro() {
		return pjuro;
	}

	public void setPjuro(BigDecimal pjuro) {
		this.pjuro = pjuro;
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
		FnCxmvDTO other = (FnCxmvDTO) obj;
		return Objects.equals(id, other.id);
	}
}
