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
@Table(name = "lc_roma")
public class LcRoma implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdPessoa cdpessoatransp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataem = new Date();
	private Time horaem = new Time(new Date().getTime());
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataprev = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataent = new Date();
	@Column(length = 2)
	private String tipo;
	private Integer numero;
	@Column(length = 120)
	private String localdesc;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal voutros = BigDecimal.ZERO;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vfrete = BigDecimal.ZERO;
	@Column(length = 3000)
	private String info;
	@Column(length = 400)
	private String motcan;
	@Column(length = 1)
	private String status;
	@JsonIgnoreProperties(value = { "lcroma" }, allowSetters = true)
	@OneToMany(mappedBy = "lcroma", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.JOIN)
	@OrderBy("ordem ASC")
	private List<LcRomaItem> lcromaitem = new ArrayList<LcRomaItem>();

	public LcRoma() {
	}

	public LcRoma(Long id, CdPessoa cdpessoaemp, CdPessoa cdpessoatransp, Date dataem, Time horaem, Date dataat,
			Date dataprev, Date dataent, String tipo, Integer numero, String localdesc,
			@Digits(integer = 18, fraction = 2) BigDecimal voutros,
			@Digits(integer = 18, fraction = 2) BigDecimal vfrete, String info, String motcan, String status,
			List<LcRomaItem> lcromaitem) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdpessoatransp = cdpessoatransp;
		this.dataem = dataem;
		this.horaem = horaem;
		this.dataat = dataat;
		this.dataprev = dataprev;
		this.dataent = dataent;
		this.tipo = tipo;
		this.numero = numero;
		this.localdesc = localdesc;
		this.voutros = voutros;
		this.vfrete = vfrete;
		this.info = info;
		this.motcan = motcan;
		this.status = status;
		this.lcromaitem = lcromaitem;
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

	public CdPessoa getCdpessoatransp() {
		return cdpessoatransp;
	}

	public void setCdpessoatransp(CdPessoa cdpessoatransp) {
		this.cdpessoatransp = cdpessoatransp;
	}

	public Date getDataem() {
		return dataem;
	}

	public void setDataem(Date dataem) {
		this.dataem = dataem;
	}

	public Time getHoraem() {
		return horaem;
	}

	public void setHoraem(Time horaem) {
		this.horaem = horaem;
	}

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public Date getDataprev() {
		return dataprev;
	}

	public void setDataprev(Date dataprev) {
		this.dataprev = dataprev;
	}

	public Date getDataent() {
		return dataent;
	}

	public void setDataent(Date dataent) {
		this.dataent = dataent;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getLocaldesc() {
		return localdesc;
	}

	public void setLocaldesc(String localdesc) {
		this.localdesc = CaracterUtil.remUpper(localdesc);
	}

	public BigDecimal getVoutros() {
		return voutros;
	}

	public void setVoutros(BigDecimal voutros) {
		this.voutros = voutros;
	}

	public BigDecimal getVfrete() {
		return vfrete;
	}

	public void setVfrete(BigDecimal vfrete) {
		this.vfrete = vfrete;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = CaracterUtil.remUpper(info);
	}

	public String getMotcan() {
		return motcan;
	}

	public void setMotcan(String motcan) {
		this.motcan = CaracterUtil.remUpper(motcan);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<LcRomaItem> getLcromaitem() {
		return lcromaitem;
	}

	public void setLcromaitem(List<LcRomaItem> lcromaitem) {
		this.lcromaitem = lcromaitem;
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
		LcRoma other = (LcRoma) obj;
		return Objects.equals(id, other.id);
	}
}
