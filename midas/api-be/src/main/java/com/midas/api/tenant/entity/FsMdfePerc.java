package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fs_mdfeperc")
public class FsMdfePerc implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fsmdfepercitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfe fsmdfe;
	@ManyToOne
	private CdEstado cdestado;

	public FsMdfePerc() {
		super();
	}

	public FsMdfePerc(Long id, FsMdfe fsmdfe, CdEstado cdestado) {
		super();
		this.id = id;
		this.fsmdfe = fsmdfe;
		this.cdestado = cdestado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public FsMdfe getFsmdfe() {
		return fsmdfe;
	}

	public void setFsmdfe(FsMdfe fsmdfe) {
		this.fsmdfe = fsmdfe;
	}

	public CdEstado getCdestado() {
		return cdestado;
	}

	public void setCdestado(CdEstado cdestado) {
		this.cdestado = cdestado;
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
		FsMdfePerc other = (FsMdfePerc) obj;
		return Objects.equals(id, other.id);
	}
}
