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
@Table(name = "fs_nferef")
public class FsNfeRef implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	@ManyToOne
	private FsNfe fsnfe;
	@Column(length = 44)
	private String refnfe;

	public FsNfeRef() {
	}

	public FsNfeRef(Integer id, FsNfe fsnfe, String refnfe) {
		super();
		this.id = id;
		this.fsnfe = fsnfe;
		this.refnfe = refnfe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FsNfe getFsnfe() {
		return fsnfe;
	}

	public void setFsnfe(FsNfe fsnfe) {
		this.fsnfe = fsnfe;
	}

	public String getRefnfe() {
		return refnfe;
	}

	public void setRefnfe(String refnfe) {
		this.refnfe = refnfe;
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
		FsNfeRef other = (FsNfeRef) obj;
		return Objects.equals(id, other.id);
	}
}
