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
@Table(name = "fs_nfeevento")
public class FsNfeEvento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsNfe fsnfe;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	private Time hora = new Time(new Date().getTime());
	private Integer numseq;
	@Column(length = 10)
	private String tpevento;
	@Column(length = 200)
	private String xjust;
	@Column(columnDefinition = "TEXT")
	private String xml;

	public FsNfeEvento() {
	}

	public FsNfeEvento(Long id, FsNfe fsnfe, Date data, Time hora, Integer numseq, String tpevento, String xjust,
			String xml) {
		super();
		this.id = id;
		this.fsnfe = fsnfe;
		this.data = data;
		this.hora = hora;
		this.numseq = numseq;
		this.tpevento = tpevento;
		this.xjust = xjust;
		this.xml = xml;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsNfe getFsnfe() {
		return fsnfe;
	}

	public void setFsnfe(FsNfe fsnfe) {
		this.fsnfe = fsnfe;
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

	public Integer getNumseq() {
		return numseq;
	}

	public void setNumseq(Integer numseq) {
		this.numseq = numseq;
	}

	public String getTpevento() {
		return tpevento;
	}

	public void setTpevento(String tpevento) {
		this.tpevento = tpevento;
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
		FsNfeEvento other = (FsNfeEvento) obj;
		return Objects.equals(id, other.id);
	}
}
