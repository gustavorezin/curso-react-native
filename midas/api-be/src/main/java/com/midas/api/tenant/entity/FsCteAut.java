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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fs_cteaut")
public class FsCteAut implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	@ManyToOne
	private FsCte fscte;
	@Column(length = 14)
	private String cpfcnpj;

	public FsCteAut() {
	}

	public FsCteAut(Integer id, FsCte fscte, String cpfcnpj) {
		super();
		this.id = id;
		this.fscte = fscte;
		this.cpfcnpj = cpfcnpj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FsCte getFscte() {
		return fscte;
	}

	public void setFscte(FsCte fscte) {
		this.fscte = fscte;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
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
		FsCteAut other = (FsCteAut) obj;
		return Objects.equals(id, other.id);
	}
}
