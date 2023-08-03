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
@Table(name = "fs_mdfeaverb")
public class FsMdfeAverb implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "fsmdfeaverbitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfe fsmdfe;
	@Column(length = 40)
	private String numaverb;

	public FsMdfeAverb() {
	}

	public FsMdfeAverb(Integer id, FsMdfe fsmdfe, String numaverb) {
		super();
		this.id = id;
		this.fsmdfe = fsmdfe;
		this.numaverb = numaverb;
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
	
	public String getNumaverb() {
		return numaverb;
	}

	public void setNumaverb(String numaverb) {
		this.numaverb = numaverb;
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
		FsMdfeAverb other = (FsMdfeAverb) obj;
		return Objects.equals(id, other.id);
	}
}
