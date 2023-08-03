package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cd_cidade")
public class CdCidade implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdEstado cdestado;
	@Column(length = 40)
	private String nome;
	@Column(length = 7)
	private String ibge;

	public CdCidade() {
	}

	public CdCidade(Integer id, CdEstado cdestado, String nome, String ibge) {
		super();
		this.id = id;
		this.cdestado = cdestado;
		this.nome = nome;
		this.ibge = ibge;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdEstado getCdestado() {
		return cdestado;
	}

	public void setCdestado(CdEstado cdestado) {
		this.cdestado = cdestado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
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
		CdCidade other = (CdCidade) obj;
		return Objects.equals(id, other.id);
	}
}
