package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cd_cert")
public class CdCert implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private CdPessoa cdpessoaemp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataexp = new Date();
	@Column(length = 120)
	private String nomearq;
	@Lob
	private byte[] arquivo;
	@Column(length = 40)
	private String senha;

	public CdCert() {
	}

	public CdCert(Integer id, CdPessoa cdpessoaemp, Date datacad, Date dataexp, String nomearq, byte[] arquivo,
			String senha) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.datacad = datacad;
		this.dataexp = dataexp;
		this.nomearq = nomearq;
		this.arquivo = arquivo;
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Date getDataexp() {
		return dataexp;
	}

	public void setDataexp(Date dataexp) {
		this.dataexp = dataexp;
	}

	public String getNomearq() {
		return nomearq;
	}

	public void setNomearq(String nomearq) {
		this.nomearq = nomearq;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		CdCert other = (CdCert) obj;
		return Objects.equals(id, other.id);
	}
}
