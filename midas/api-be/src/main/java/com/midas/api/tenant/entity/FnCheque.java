package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fn_cheque")
public class FnCheque implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fnchequeitem" }, allowSetters = true)
	@ManyToOne
	private FnCxmv fncxmv;
	@ManyToOne(fetch = FetchType.LAZY)
	private CdPessoa cdpessoaempatual;
	@ManyToOne
	private CdCaixa cdcaixaatual;
	@Column(length = 10)
	private String cdbancos_id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Column(length = 15)
	private String agencia;
	@Column(length = 15)
	private String conta;
	@Column(length = 15)
	private String serie;
	@Column(length = 20)
	private String num;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date vence = new Date();
	@Column(length = 60)
	private String emissor;
	@Column(length = 20)
	private String cpfcnpj;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vsub;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vdesc;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vjuro;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vtot;
	@Column(length = 2000)
	private String info;
	@Column(length = 2)
	private String tipo;
	private Integer transacao;
	@Column(length = 2)
	private String status;
	@Transient
	@Column(length = 1)
	private String selemcaixatroco;
	@Transient
	@Column(length = 1)
	private String selemcaixaop;
	@JsonIgnoreProperties(value = { "fncheque" }, allowSetters = true)
	@OneToMany(mappedBy = "fncheque", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FnChequeHist> fnchequehistitem = new ArrayList<FnChequeHist>();

	public FnCheque() {
	}

	public FnCheque(Long id, FnCxmv fncxmv, CdPessoa cdpessoaempatual, CdCaixa cdcaixaatual, String cdbancos_id,
			Date datacad, Date dataat, String agencia, String conta, String serie, String num, Date data, Date vence,
			String emissor, String cpfcnpj, @Digits(integer = 18, fraction = 2) BigDecimal vsub,
			@Digits(integer = 18, fraction = 2) BigDecimal vdesc, @Digits(integer = 18, fraction = 2) BigDecimal vjuro,
			@Digits(integer = 18, fraction = 2) BigDecimal vtot, String info, String tipo, Integer transacao,
			String status, String selemcaixatroco, String selemcaixaop, List<FnChequeHist> fnchequehistitem) {
		super();
		this.id = id;
		this.fncxmv = fncxmv;
		this.cdpessoaempatual = cdpessoaempatual;
		this.cdcaixaatual = cdcaixaatual;
		this.cdbancos_id = cdbancos_id;
		this.datacad = datacad;
		this.dataat = dataat;
		this.agencia = agencia;
		this.conta = conta;
		this.serie = serie;
		this.num = num;
		this.data = data;
		this.vence = vence;
		this.emissor = emissor;
		this.cpfcnpj = cpfcnpj;
		this.vsub = vsub;
		this.vdesc = vdesc;
		this.vjuro = vjuro;
		this.vtot = vtot;
		this.info = info;
		this.tipo = tipo;
		this.transacao = transacao;
		this.status = status;
		this.selemcaixatroco = selemcaixatroco;
		this.selemcaixaop = selemcaixaop;
		this.fnchequehistitem = fnchequehistitem;
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

	public CdPessoa getCdpessoaempatual() {
		return cdpessoaempatual;
	}

	public void setCdpessoaempatual(CdPessoa cdpessoaempatual) {
		this.cdpessoaempatual = cdpessoaempatual;
	}

	public CdCaixa getCdcaixaatual() {
		return cdcaixaatual;
	}

	public void setCdcaixaatual(CdCaixa cdcaixaatual) {
		this.cdcaixaatual = cdcaixaatual;
	}

	public String getCdbancos_id() {
		return cdbancos_id;
	}

	public void setCdbancos_id(String cdbancos_id) {
		this.cdbancos_id = cdbancos_id;
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

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = CaracterUtil.remUpper(agencia);
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = CaracterUtil.remUpper(conta);
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = CaracterUtil.remUpper(serie);
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = CaracterUtil.remUpper(num);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getVence() {
		return vence;
	}

	public void setVence(Date vence) {
		this.vence = vence;
	}

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = CaracterUtil.remUpper(emissor);
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = CaracterUtil.remUpper(cpfcnpj);
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = CaracterUtil.remUpper(info);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getSelemcaixatroco() {
		return selemcaixatroco;
	}

	public void setSelemcaixatroco(String selemcaixatroco) {
		this.selemcaixatroco = selemcaixatroco;
	}

	public String getSelemcaixaop() {
		return selemcaixaop;
	}

	public void setSelemcaixaop(String selemcaixaop) {
		this.selemcaixaop = selemcaixaop;
	}

	public List<FnChequeHist> getFnchequehistitem() {
		return fnchequehistitem;
	}

	public void setFnchequehistitem(List<FnChequeHist> fnchequehistitem) {
		this.fnchequehistitem = fnchequehistitem;
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
		FnCheque other = (FnCheque) obj;
		return Objects.equals(id, other.id);
	}
}
