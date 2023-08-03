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
@Table(name = "cd_veiculo_rel")
public class CdVeiculoRel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdveiculorelitems" }, allowSetters = true)
	@ManyToOne
	private CdPessoa cdpessoamotora;
	@ManyToOne
	private CdVeiculo cdveiculo;

	public CdVeiculoRel() {
	}

	public CdVeiculoRel(Integer id, CdPessoa cdpessoamotora, CdVeiculo cdveiculo) {
		super();
		this.id = id;
		this.cdpessoamotora = cdpessoamotora;
		this.cdveiculo = cdveiculo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPessoa getCdpessoamotora() {
		return cdpessoamotora;
	}

	public void setCdpessoamotora(CdPessoa cdpessoamotora) {
		this.cdpessoamotora = cdpessoamotora;
	}

	public CdVeiculo getCdveiculo() {
		return cdveiculo;
	}

	public void setCdveiculo(CdVeiculo cdveiculo) {
		this.cdveiculo = cdveiculo;
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
		CdVeiculoRel other = (CdVeiculoRel) obj;
		return Objects.equals(id, other.id);
	}
}
