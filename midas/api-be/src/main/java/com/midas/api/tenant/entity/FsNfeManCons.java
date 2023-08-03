package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.sql.Time;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fs_nfeman_cons")
public class FsNfeManCons implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	@OneToOne(orphanRemoval = true)
	private CdPessoa cdpessoaemp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	@JsonFormat(pattern = "HH:mm:ss")
	private Time hora = new Time(new Date().getTime());
	@Column(length = 20)
	private String ultimonsu;

	public FsNfeManCons() {
	}

	public FsNfeManCons(Integer id, CdPessoa cdpessoaemp, Date data, Time hora, String ultimonsu) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.data = data;
		this.hora = hora;
		this.ultimonsu = ultimonsu;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getUltimonsu() {
		return ultimonsu;
	}

	public void setUltimonsu(String ultimonsu) {
		this.ultimonsu = ultimonsu;
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
		FsNfeManCons other = (FsNfeManCons) obj;
		return Objects.equals(id, other.id);
	}
}
