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
@Table(name = "fs_cteinf_docemi_cte")
public class FsCteInfDocEmiCte implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsCteInfDocEmi fscteinfdocemi;
	@Column(length = 44)
	private String chave;

	public FsCteInfDocEmiCte() {
	}

	public FsCteInfDocEmiCte(Long id, FsCteInfDocEmi fscteinfdocemi, String chave) {
		super();
		this.id = id;
		this.fscteinfdocemi = fscteinfdocemi;
		this.chave = chave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsCteInfDocEmi getFscteinfdocemi() {
		return fscteinfdocemi;
	}

	public void setFscteinfdocemi(FsCteInfDocEmi fscteinfdocemi) {
		this.fscteinfdocemi = fscteinfdocemi;
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
		FsCteInfDocEmiCte other = (FsCteInfDocEmiCte) obj;
		return Objects.equals(id, other.id);
	}
}
