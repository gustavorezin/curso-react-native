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
@Table(name = "fs_cteinf_nfe")
public class FsCteInfNfe implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsCteInf fscteinf;
	@Column(length = 44)
	private String chave;

	public FsCteInfNfe() {
	}

	public FsCteInfNfe(Long id, FsCteInf fscteinf, String chave) {
		super();
		this.id = id;
		this.fscteinf = fscteinf;
		this.chave = chave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsCteInf getFscteinf() {
		return fscteinf;
	}

	public void setFscteinf(FsCteInf fscteinf) {
		this.fscteinf = fscteinf;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
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
		FsCteInfNfe other = (FsCteInfNfe) obj;
		return Objects.equals(id, other.id);
	}
}
