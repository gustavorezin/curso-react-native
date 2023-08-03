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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_mdfecontr")
public class FsMdfeContr implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "fsmdfecontritems" }, allowSetters = true)
	@ManyToOne
	private FsMdfe fsmdfe;
	@Column(length = 60)
	private String nome;
	@Column(length = 14)
	private String cpfcnpj;
	@Column(length = 20)
	private String idest;

	public FsMdfeContr() {
	}

	public FsMdfeContr(Integer id, FsMdfe fsmdfe, String nome, String cpfcnpj, String idest) {
		super();
		this.id = id;
		this.fsmdfe = fsmdfe;
		this.nome = nome;
		this.cpfcnpj = cpfcnpj;
		this.idest = idest;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FsMdfe getFsmdfe() {
		return fsmdfe;
	}

	public void setFsmdfe(FsMdfe fsmdfe) {
		this.fsmdfe = fsmdfe;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getIdest() {
		return idest;
	}

	public void setIdest(String idest) {
		this.idest = idest;
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
		FsMdfeContr other = (FsMdfeContr) obj;
		return Objects.equals(id, other.id);
	}
}
