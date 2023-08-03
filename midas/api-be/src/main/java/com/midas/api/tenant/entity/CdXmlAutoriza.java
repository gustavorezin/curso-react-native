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
@Table(name = "cd_xmlautoriza")
public class CdXmlAutoriza implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 60)
	private String nome;
	@Column(length = 14)
	private String cnpj;
	@Column(length = 2)
	private String tpdoc;
	@Column(length = 1)
	private String addauto;

	public CdXmlAutoriza() {
	}

	public CdXmlAutoriza(Integer id, CdPessoa cdpessoaemp, String nome, String cnpj, String tpdoc, String addauto) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.nome = nome;
		this.cnpj = cnpj;
		this.tpdoc = tpdoc;
		this.addauto = addauto;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTpdoc() {
		return tpdoc;
	}

	public void setTpdoc(String tpdoc) {
		this.tpdoc = tpdoc;
	}

	public String getAddauto() {
		return addauto;
	}

	public void setAddauto(String addauto) {
		this.addauto = addauto;
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
		CdXmlAutoriza other = (CdXmlAutoriza) obj;
		return Objects.equals(id, other.id);
	}
}
