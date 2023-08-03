package com.midas.api.tenant.entity;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fs_cteman_evento")
public class FsCteManEvento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsCteMan fscteman;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dhevento = new Date();
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date dheventohr = new Date();
	@Column(length = 6)
	private String tpevento;
	private Integer nseqevento;
	@Column(length = 60)
	private String xevento;
	@Column(length = 44)
	private String chave;
	@Column(length = 20)
	private String nprot;

	public FsCteManEvento() {
	}

	public FsCteManEvento(Long id, FsCteMan fscteman, CdPessoa cdpessoaemp, Date dhevento, Date dheventohr,
			String tpevento, Integer nseqevento, String xevento, String chave, String nprot) {
		super();
		this.id = id;
		this.fscteman = fscteman;
		this.cdpessoaemp = cdpessoaemp;
		this.dhevento = dhevento;
		this.dheventohr = dheventohr;
		this.tpevento = tpevento;
		this.nseqevento = nseqevento;
		this.xevento = xevento;
		this.chave = chave;
		this.nprot = nprot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsCteMan getFscteman() {
		return fscteman;
	}

	public void setFscteman(FsCteMan fscteman) {
		this.fscteman = fscteman;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public Date getDhevento() {
		return dhevento;
	}

	public void setDhevento(Date dhevento) {
		this.dhevento = dhevento;
	}

	public Date getDheventohr() {
		return dheventohr;
	}

	public void setDheventohr(Date dheventohr) {
		this.dheventohr = dheventohr;
	}

	public String getTpevento() {
		return tpevento;
	}

	public void setTpevento(String tpevento) {
		this.tpevento = tpevento;
	}

	public Integer getNseqevento() {
		return nseqevento;
	}

	public void setNseqevento(Integer nseqevento) {
		this.nseqevento = nseqevento;
	}

	public String getXevento() {
		return xevento;
	}

	public void setXevento(String xevento) {
		this.xevento = xevento;
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
		FsCteManEvento other = (FsCteManEvento) obj;
		return Objects.equals(id, other.id);
	}
}
