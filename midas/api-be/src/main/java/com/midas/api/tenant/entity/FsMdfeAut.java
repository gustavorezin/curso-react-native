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

@Entity
@Table(name = "fs_mdfeaut")
public class FsMdfeAut implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "fsmdfeautitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfe fsmdfe;
	@Column(length = 14)
	private String cpfcnpj;

	public FsMdfeAut() {
	}

	public FsMdfeAut(Integer id, FsMdfe fsmdfe, String cpfcnpj) {
		super();
		this.id = id;
		this.fsmdfe = fsmdfe;
		this.cpfcnpj = cpfcnpj;
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
		FsMdfeAut other = (FsMdfeAut) obj;
		return Objects.equals(id, other.id);
	}
}
