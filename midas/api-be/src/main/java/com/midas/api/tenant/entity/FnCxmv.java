package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
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
@Table(name = "fn_cxmv")
public class FnCxmv implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdCaixa cdcaixa;
	@ManyToOne
	private FnTitulo fntitulo;
	@Column(length = 2)
	private String fpag;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	private Time horacad = new Time(new Date().getTime());
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	private Time horaat = new Time(new Date().getTime());
	@Column(length = 500)
	private String info;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vsub;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pdesc;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vdesc;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal pjuro;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vjuro;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vtot;
	private Integer transacao;
	@Column(length = 8)
	private String status;
	@JsonIgnoreProperties(value = { "fncxmv" }, allowSetters = true)
	@OneToMany(mappedBy = "fncxmv", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FnCheque> fnchequeitem = new ArrayList<FnCheque>();
	@JsonIgnoreProperties(value = { "fncxmv" }, allowSetters = true)
	@OneToMany(mappedBy = "fncxmv", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FnCartao> fncartaoitem = new ArrayList<FnCartao>();
	@JsonIgnoreProperties(value = { "fncxmv" }, allowSetters = true)
	@OneToMany(mappedBy = "fncxmv", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@OrderBy("id ASC")
	private List<FnCxmvDre> fncxmvdreitem = new ArrayList<FnCxmvDre>();

	public FnCxmv() {
	}

	public FnCxmv(Long id, CdPessoa cdpessoaemp, CdCaixa cdcaixa, FnTitulo fntitulo, String fpag, Date datacad,
			Time horacad, Date dataat, Time horaat, String info, @Digits(integer = 18, fraction = 2) BigDecimal vsub,
			@Digits(integer = 3, fraction = 2) BigDecimal pdesc, @Digits(integer = 18, fraction = 2) BigDecimal vdesc,
			@Digits(integer = 18, fraction = 2) BigDecimal pjuro, @Digits(integer = 18, fraction = 2) BigDecimal vjuro,
			@Digits(integer = 18, fraction = 2) BigDecimal vtot, Integer transacao, String status,
			List<FnCheque> fnchequeitem, List<FnCartao> fncartaoitem, List<FnCxmvDre> fncxmvdreitem) {
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
		this.fnchequeitem = fnchequeitem;
		this.fncartaoitem = fncartaoitem;
		this.fncxmvdreitem = fncxmvdreitem;
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
		this.info = CaracterUtil.remUpper(info);
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

	public List<FnCheque> getFnchequeitem() {
		return fnchequeitem;
	}

	public void setFnchequeitem(List<FnCheque> fnchequeitem) {
		this.fnchequeitem = fnchequeitem;
	}

	public List<FnCartao> getFncartaoitem() {
		return fncartaoitem;
	}

	public void setFncartaoitem(List<FnCartao> fncartaoitem) {
		this.fncartaoitem = fncartaoitem;
	}

	public List<FnCxmvDre> getFncxmvdreitem() {
		return fncxmvdreitem;
	}

	public void setFncxmvdreitem(List<FnCxmvDre> fncxmvdreitem) {
		this.fncxmvdreitem = fncxmvdreitem;
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
		FnCxmv other = (FnCxmv) obj;
		return Objects.equals(id, other.id);
	}
}
