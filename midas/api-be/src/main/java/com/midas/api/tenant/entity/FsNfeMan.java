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
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "fs_nfeman")
public class FsNfeMan implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacons = new Date();
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date horacons = new Date();
	@Column(length = 44)
	private String chave;
	@Column(length = 20)
	private String nprot;
	@Column(length = 60)
	private String nome;
	@Column(length = 20)
	private String cpfcnpj;
	@Column(length = 20)
	private String ie;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dhemi = new Date();
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date dhemihr = new Date();
	@Column(length = 9)
	private String nnf;
	@Digits(integer = 18, fraction = 2)
	private BigDecimal vnf = new BigDecimal(0);
	@Column(length = 1)
	private String csitnfe;
	@Column(length = 20)
	private String nsu;
	@Column(length = 1)
	private String st_imp;
	private Integer st;
	@OneToMany(mappedBy = "fsnfeman", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfeManEvento> fsnfemanevento = new ArrayList<FsNfeManEvento>();

	public FsNfeMan() {
	}

	public FsNfeMan(Long id, CdPessoa cdpessoaemp, Date datacons, Date horacons, String chave, String nprot,
			String nome, String cpfcnpj, String ie, Date dhemi, Date dhemihr, String nnf,
			@Digits(integer = 18, fraction = 2) BigDecimal vnf, String csitnfe, String nsu, String st_imp, Integer st,
			List<FsNfeManEvento> fsnfemanevento) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.datacons = datacons;
		this.horacons = horacons;
		this.chave = chave;
		this.nprot = nprot;
		this.nome = nome;
		this.cpfcnpj = cpfcnpj;
		this.ie = ie;
		this.dhemi = dhemi;
		this.dhemihr = dhemihr;
		this.nnf = nnf;
		this.vnf = vnf;
		this.csitnfe = csitnfe;
		this.nsu = nsu;
		this.st_imp = st_imp;
		this.st = st;
		this.fsnfemanevento = fsnfemanevento;
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

	public Date getDatacons() {
		return datacons;
	}

	public void setDatacons(Date datacons) {
		this.datacons = datacons;
	}

	public Date getHoracons() {
		return horacons;
	}

	public void setHoracons(Date horacons) {
		this.horacons = horacons;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getNprot() {
		return nprot;
	}

	public void setNprot(String nprot) {
		this.nprot = nprot;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public Date getDhemi() {
		return dhemi;
	}

	public void setDhemi(Date dhemi) {
		this.dhemi = dhemi;
	}

	public Date getDhemihr() {
		return dhemihr;
	}

	public void setDhemihr(Date dhemihr) {
		this.dhemihr = dhemihr;
	}

	public String getNnf() {
		return nnf;
	}

	public void setNnf(String nnf) {
		this.nnf = nnf;
	}

	public BigDecimal getVnf() {
		return vnf;
	}

	public void setVnf(BigDecimal vnf) {
		this.vnf = vnf;
	}

	public String getCsitnfe() {
		return csitnfe;
	}

	public void setCsitnfe(String csitnfe) {
		this.csitnfe = csitnfe;
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	public String getSt_imp() {
		return st_imp;
	}

	public void setSt_imp(String st_imp) {
		this.st_imp = st_imp;
	}

	public Integer getSt() {
		return st;
	}

	public void setSt(Integer st) {
		this.st = st;
	}

	public List<FsNfeManEvento> getFsnfemanevento() {
		return fsnfemanevento;
	}

	public void setFsnfemanevento(List<FsNfeManEvento> fsnfemanevento) {
		this.fsnfemanevento = fsnfemanevento;
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
		FsNfeMan other = (FsNfeMan) obj;
		return Objects.equals(id, other.id);
	}
}
