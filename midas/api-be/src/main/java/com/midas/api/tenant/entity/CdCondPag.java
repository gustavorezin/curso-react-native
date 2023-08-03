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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_condpag")
public class CdCondPag implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 60)
	private String nome;
	private Integer qtd;
	private Integer inicial;
	private Integer intervalo;
	@Column(length = 1)
	private String libvendedor;
	@Column(length = 8)
	private String status;
	@JsonIgnoreProperties(value = { "cdcondpag" }, allowSetters = true)
	@OneToMany(mappedBy = "cdcondpag", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<CdCondPagDia> cdcondpagdiaitem = new ArrayList<CdCondPagDia>();

	public CdCondPag() {
	}

	public CdCondPag(Integer id, String nome, Integer qtd, Integer inicial, Integer intervalo, String libvendedor,
			String status, List<CdCondPagDia> cdcondpagdiaitem) {
		super();
		this.id = id;
		this.nome = nome;
		this.qtd = qtd;
		this.inicial = inicial;
		this.intervalo = intervalo;
		this.libvendedor = libvendedor;
		this.status = status;
		this.cdcondpagdiaitem = cdcondpagdiaitem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Integer getInicial() {
		return inicial;
	}

	public void setInicial(Integer inicial) {
		this.inicial = inicial;
	}

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public String getLibvendedor() {
		return libvendedor;
	}

	public void setLibvendedor(String libvendedor) {
		this.libvendedor = libvendedor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CdCondPagDia> getCdcondpagdiaitem() {
		return cdcondpagdiaitem;
	}

	public void setCdcondpagdiaitem(List<CdCondPagDia> cdcondpagdiaitem) {
		this.cdcondpagdiaitem = cdcondpagdiaitem;
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
		CdCondPag other = (CdCondPag) obj;
		return Objects.equals(id, other.id);
	}
}
