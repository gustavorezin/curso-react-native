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
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_produto_subgrupo")
public class CdProdutoSubgrupo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdprodutosubgrupoitem" }, allowSetters = true)
	@ManyToOne
	private CdProdutoGrupo cdprodutogrupo;
	@Column(length = 80)
	private String nome;
	@Column(length = 8)
	private String status;

	public CdProdutoSubgrupo() {
	}

	public CdProdutoSubgrupo(Integer id, CdProdutoGrupo cdprodutogrupo, String nome, String status) {
		super();
		this.id = id;
		this.cdprodutogrupo = cdprodutogrupo;
		this.nome = nome;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdProdutoGrupo getCdprodutogrupo() {
		return cdprodutogrupo;
	}

	public void setCdprodutogrupo(CdProdutoGrupo cdprodutogrupo) {
		this.cdprodutogrupo = cdprodutogrupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
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
		CdProdutoSubgrupo other = (CdProdutoSubgrupo) obj;
		return Objects.equals(id, other.id);
	}
}
