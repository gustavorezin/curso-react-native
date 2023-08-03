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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_plcon_macro")
public class CdPlconMacro implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdplconmacro" }, allowSetters = true)
	@ManyToOne
	private CdPlconConta cdplconconta;
	private Integer masc;
	@Column(length = 60)
	private String nome;
	@Column(length = 7)
	private String cor;
	@Column(length = 1)
	private String tipo;
	@Column(length = 1)
	private String exibe;
	@OneToMany(mappedBy = "cdplconmacro", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("masc ASC")
	private List<CdPlconMicro> cdplconmicro = new ArrayList<CdPlconMicro>();

	public CdPlconMacro() {
	}

	public CdPlconMacro(Integer id, CdPlconConta cdplconconta, Integer masc, String nome, String cor, String tipo,
			String exibe, List<CdPlconMicro> cdplconmicro) {
		super();
		this.id = id;
		this.cdplconconta = cdplconconta;
		this.masc = masc;
		this.nome = nome;
		this.cor = cor;
		this.tipo = tipo;
		this.exibe = exibe;
		this.cdplconmicro = cdplconmicro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPlconConta getCdplconconta() {
		return cdplconconta;
	}

	public void setCdplconconta(CdPlconConta cdplconconta) {
		this.cdplconconta = cdplconconta;
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

	public List<CdPlconMicro> getCdplconmicro() {
		return cdplconmicro;
	}

	public void setCdplconmicro(List<CdPlconMicro> cdplconmicro) {
		this.cdplconmicro = cdplconmicro;
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
		CdPlconMacro other = (CdPlconMacro) obj;
		return Objects.equals(id, other.id);
	}
}
