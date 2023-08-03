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

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_maqequip")
public class CdMaqequip implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdPessoa cdpessoafunc;
	@Column(length = 120)
	private String nome;
	@Column(length = 120)
	private String posto;
	@Column(length = 120)
	private String tarefas;
	@Column(length = 8)
	private String status;

	public CdMaqequip() {
	}

	public CdMaqequip(Integer id, CdPessoa cdpessoaemp, CdPessoa cdpessoafunc, String nome, String posto,
			String tarefas, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdpessoafunc = cdpessoafunc;
		this.nome = nome;
		this.posto = posto;
		this.tarefas = tarefas;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPessoa getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(CdPessoa cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
	}

	public CdPessoa getCdpessoafunc() {
		return cdpessoafunc;
	}

	public void setCdpessoafunc(CdPessoa cdpessoafunc) {
		this.cdpessoafunc = cdpessoafunc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getPosto() {
		return posto;
	}

	public void setPosto(String posto) {
		this.posto = CaracterUtil.remUpper(posto);
	}

	public String getTarefas() {
		return tarefas;
	}

	public void setTarefas(String tarefas) {
		this.tarefas = CaracterUtil.remUpper(tarefas);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		CdMaqequip other = (CdMaqequip) obj;
		return Objects.equals(id, other.id);
	}
}
