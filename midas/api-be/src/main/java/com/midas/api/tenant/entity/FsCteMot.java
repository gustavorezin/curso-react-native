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
@Table(name = "fs_ctemot")
public class FsCteMot implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fsctemots" }, allowSetters = true)
	@ManyToOne
	private FsCte fscte;
	@ManyToOne
	private CdVeiculo cdveiculo;
	@ManyToOne
	private CdPessoa cdpessoamot;
	@Column(length = 60)
	private String nome;
	@Column(length = 14)
	private String cpfcnpj;
	@Column(length = 7)
	private String v_placa;
	@Column(length = 2)
	private String v_ufplaca;
	@Column(length = 9)
	private String v_antt;

	public FsCteMot() {
	}
	
	public FsCteMot(Long id, FsCte fscte, CdVeiculo cdveiculo, CdPessoa cdpessoamot, String nome, String cpfcnpj,
			String v_placa, String v_ufplaca, String v_antt) {
		super();
		this.id = id;
		this.fscte = fscte;
		this.cdveiculo = cdveiculo;
		this.cdpessoamot = cdpessoamot;
		this.nome = nome;
		this.cpfcnpj = cpfcnpj;
		this.v_placa = v_placa;
		this.v_ufplaca = v_ufplaca;
		this.v_antt = v_antt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsCte getFscte() {
		return fscte;
	}

	public void setFscte(FsCte fscte) {
		this.fscte = fscte;
	}

	public CdVeiculo getCdveiculo() {
		return cdveiculo;
	}

	public void setCdveiculo(CdVeiculo cdveiculo) {
		this.cdveiculo = cdveiculo;
	}

	public CdPessoa getCdpessoamot() {
		return cdpessoamot;
	}

	public void setCdpessoamot(CdPessoa cdpessoamot) {
		this.cdpessoamot = cdpessoamot;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getV_placa() {
		return v_placa;
	}

	public void setV_placa(String v_placa) {
		this.v_placa = v_placa;
	}

	public String getV_ufplaca() {
		return v_ufplaca;
	}

	public void setV_ufplaca(String v_ufplaca) {
		this.v_ufplaca = v_ufplaca;
	}

	public String getV_antt() {
		return v_antt;
	}

	public void setV_antt(String v_antt) {
		this.v_antt = v_antt;
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
		FsCteMot other = (FsCteMot) obj;
		return Objects.equals(id, other.id);
	}
}
