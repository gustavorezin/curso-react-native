package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fs_mdfereboq")
public class FsMdfeReboq implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fsmdfereboqitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfe fsmdfe;
	@OneToOne
	private CdVeiculo cdveiculo;
	@Column(length = 7)
	private String placa;
	@Column(length = 2)
	private String ufplaca;
	@Column(length = 9)
	private String antt;

	public FsMdfeReboq() {
		super();
	}

	public FsMdfeReboq(Long id, FsMdfe fsmdfe, CdVeiculo cdveiculo, String placa, String ufplaca, String antt) {
		super();
		this.id = id;
		this.fsmdfe = fsmdfe;
		this.cdveiculo = cdveiculo;
		this.placa = placa;
		this.ufplaca = ufplaca;
		this.antt = antt;
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

	public CdVeiculo getCdveiculo() {
		return cdveiculo;
	}

	public void setCdveiculo(CdVeiculo cdveiculo) {
		this.cdveiculo = cdveiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getUfplaca() {
		return ufplaca;
	}

	public void setUfplaca(String ufplaca) {
		this.ufplaca = ufplaca;
	}

	public String getAntt() {
		return antt;
	}

	public void setAntt(String antt) {
		this.antt = antt;
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
		FsMdfeReboq other = (FsMdfeReboq) obj;
		return Objects.equals(id, other.id);
	}
}
