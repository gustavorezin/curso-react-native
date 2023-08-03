package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_ccusto")
public class CdCcusto implements Serializable {
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
	private Date dataat = new Date();
	@Column(length = 120)
	private String nome;
	@Column(length = 2000)
	private String info;
	@Column(length = 1)
	private String usa_padrao = "N";
	@Column(length = 8)
	private String status;

	public CdCcusto() {
	}

	public CdCcusto(Integer id, CdPessoa cdpessoaemp, Date datacad, Date dataat, String nome, String info,
			String usa_padrao, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.datacad = datacad;
		this.dataat = dataat;
		this.nome = nome;
		this.info = info;
		this.usa_padrao = usa_padrao;
		this.status = status;
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

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = CaracterUtil.remUpper(info);
	}

	public String getUsa_padrao() {
		return usa_padrao;
	}

	public void setUsa_padrao(String usa_padrao) {
		this.usa_padrao = usa_padrao;
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
		CdCcusto other = (CdCcusto) obj;
		return Objects.equals(id, other.id);
	}
}
