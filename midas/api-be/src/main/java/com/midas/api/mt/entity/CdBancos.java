package com.midas.api.mt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cd_bancos")
public class CdBancos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 10)
	private String codigo;
	@Column(length = 60)
	private String nome;
	@Column(length = 100)
	private String nomecompleto;
	@Column(length = 1)
	private String bolhom = "N";

	public CdBancos() {
	}

	public CdBancos(Integer id, String codigo, String nome, String nomecompleto, String bolhom) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.nomecompleto = nomecompleto;
		this.bolhom = bolhom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomecompleto() {
		return nomecompleto;
	}

	public void setNomecompleto(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}

	public String getBolhom() {
		return bolhom;
	}

	public void setBolhom(String bolhom) {
		this.bolhom = bolhom;
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
		CdBancos other = (CdBancos) obj;
		return Objects.equals(id, other.id);
	}
}
