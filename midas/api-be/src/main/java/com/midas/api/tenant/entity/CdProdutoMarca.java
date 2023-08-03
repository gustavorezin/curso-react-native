package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_produto_marca")
public class CdProdutoMarca implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 80)
	private String nome;
	@Column(length = 180)
	private String site;
	@Column(length = 180)
	private String info;
	@Column(length = 8)
	private String status_loja = "INATIVO";

	public CdProdutoMarca() {
	}

	public CdProdutoMarca(Integer id, String nome, String site, String info, String status_loja) {
		super();
		this.id = id;
		this.nome = nome;
		this.site = site;
		this.info = info;
		this.status_loja = status_loja;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatus_loja() {
		return status_loja;
	}

	public void setStatus_loja(String status_loja) {
		this.status_loja = status_loja;
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
		CdProdutoMarca other = (CdProdutoMarca) obj;
		return Objects.equals(id, other.id);
	}
}
