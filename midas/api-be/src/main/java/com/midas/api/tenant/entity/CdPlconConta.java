package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_plcon_conta")
public class CdPlconConta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private CdPessoa cdpessoaemp;
	private Integer masc;
	@Column(length = 60)
	private String nome;
	@Column(length = 7)
	private String cor;
	@Column(length = 1)
	private String tipo;
	@Column(length = 1)
	private String exibe;
	@OneToMany(mappedBy = "cdplconconta", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("masc ASC")
	private List<CdPlconMacro> cdplconmacro = new ArrayList<CdPlconMacro>();

	public CdPlconConta() {
	}

	public CdPlconConta(Integer id, CdPessoa cdpessoaemp, Integer masc, String nome, String cor, String tipo,
			String exibe, List<CdPlconMacro> cdplconmacro) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.masc = masc;
		this.nome = nome;
		this.cor = cor;
		this.tipo = tipo;
		this.exibe = exibe;
		this.cdplconmacro = cdplconmacro;
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

	public Integer getMasc() {
		return masc;
	}

	public void setMasc(Integer masc) {
		this.masc = masc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getExibe() {
		return exibe;
	}

	public void setExibe(String exibe) {
		this.exibe = exibe;
	}

	public List<CdPlconMacro> getCdplconmacro() {
		return cdplconmacro;
	}

	public void setCdplconmacro(List<CdPlconMacro> cdplconmacro) {
		this.cdplconmacro = cdplconmacro;
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
		CdPlconConta other = (CdPlconConta) obj;
		return Objects.equals(id, other.id);
	}
}
