package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fn_cheque_hist")
public class FnChequeHist implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fnchequehistitem" }, allowSetters = true)
	@ManyToOne
	private FnCheque fncheque;
	@ManyToOne(fetch = FetchType.LAZY)
	private CdCaixa cdcaixa;
	private Long fncxmvini;
	private Long fncxmvfim;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	private Time horacad = new Time(new Date().getTime());
	@Column(length = 250)
	private String motivo;
	@Column(length = 60)
	private String info;
	@Column(length = 2)
	private String status;

	public FnChequeHist() {
	}

	public FnChequeHist(Long id, FnCheque fncheque, CdCaixa cdcaixa, Long fncxmvini, Date datacad, Time horacad,
			String motivo, String info, String status) {
		super();
		this.id = id;
		this.fncheque = fncheque;
		this.cdcaixa = cdcaixa;
		this.fncxmvini = fncxmvini;
		this.datacad = datacad;
		this.horacad = horacad;
		this.motivo = motivo;
		this.info = info;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FnCheque getFncheque() {
		return fncheque;
	}

	public void setFncheque(FnCheque fncheque) {
		this.fncheque = fncheque;
	}

	public CdCaixa getCdcaixa() {
		return cdcaixa;
	}

	public void setCdcaixa(CdCaixa cdcaixa) {
		this.cdcaixa = cdcaixa;
	}

	public Long getFncxmvini() {
		return fncxmvini;
	}

	public void setFncxmvini(Long fncxmvini) {
		this.fncxmvini = fncxmvini;
	}

	public Long getFncxmvfim() {
		return fncxmvfim;
	}

	public void setFncxmvfim(Long fncxmvfim) {
		this.fncxmvfim = fncxmvfim;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = CaracterUtil.remUpper(motivo);
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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
		FnChequeHist other = (FnChequeHist) obj;
		return Objects.equals(id, other.id);
	}
}
