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
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_inut")
public class FsInut implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	private Integer modelo;
	private Integer serie;
	private Integer numero;
	@Column(length = 60)
	private String motivo;
	private String tpamb;

	public FsInut() {
		super();
	}

	public FsInut(Integer id, CdPessoa cdpessoaemp, Date datacad, Integer modelo, Integer serie, Integer numero,
			String motivo, String tpamb) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.datacad = datacad;
		this.modelo = modelo;
		this.serie = serie;
		this.numero = numero;
		this.motivo = motivo;
		this.tpamb = tpamb;
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

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = CaracterUtil.remUpper(motivo);
	}

	public String getTpamb() {
		return tpamb;
	}

	public void setTpamb(String tpamb) {
		this.tpamb = tpamb;
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
		FsInut other = (FsInut) obj;
		return Objects.equals(id, other.id);
	}
}
