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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fs_nfseevento")
public class FsNfseEvento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsNfse fsnfse;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	private Time hora = new Time(new Date().getTime());
	@Column(length = 200)
	private String xjust;
	@Column(columnDefinition = "TEXT")
	private String xml;

	public FsNfseEvento() {
	}

	public FsNfseEvento(Long id, FsNfse fsnfse, Date data, Time hora, String xjust, String xml) {
		super();
		this.id = id;
		this.fsnfse = fsnfse;
		this.data = data;
		this.hora = hora;
		this.xjust = xjust;
		this.xml = xml;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsNfse getFsnfse() {
		return fsnfse;
	}

	public void setFsnfse(FsNfse fsnfse) {
		this.fsnfse = fsnfse;
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

	public String getXjust() {
		return xjust;
	}

	public void setXjust(String xjust) {
		this.xjust = xjust;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
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
		FsNfseEvento other = (FsNfseEvento) obj;
		return Objects.equals(id, other.id);
	}
}
