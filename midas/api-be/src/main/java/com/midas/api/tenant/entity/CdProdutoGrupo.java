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
@Table(name = "cd_produto_grupo")
public class CdProdutoGrupo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdprodutogrupoitem" }, allowSetters = true)
	@ManyToOne
	private CdProdutoCat cdprodutocat;
	@Column(length = 80)
	private String nome;
	@Column(length = 180)
	private String info;
	@Column(length = 8)
	private String status_loja;
	@Column(length = 8)
	private String status;
	@OneToMany(mappedBy = "cdprodutogrupo", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("nome ASC")
	private List<CdProdutoSubgrupo> cdprodutosubgrupoitem = new ArrayList<CdProdutoSubgrupo>();

	public CdProdutoGrupo() {
	}

	public CdProdutoGrupo(Integer id, CdProdutoCat cdprodutocat, String nome, String info, String status_loja,
			String status, List<CdProdutoSubgrupo> cdprodutosubgrupoitem) {
		super();
		this.id = id;
		this.cdprodutocat = cdprodutocat;
		this.nome = nome;
		this.info = info;
		this.status_loja = status_loja;
		this.status = status;
		this.cdprodutosubgrupoitem = cdprodutosubgrupoitem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdProdutoCat getCdprodutocat() {
		return cdprodutocat;
	}

	public void setCdprodutocat(CdProdutoCat cdprodutocat) {
		this.cdprodutocat = cdprodutocat;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CdProdutoSubgrupo> getCdprodutosubgrupoitem() {
		return cdprodutosubgrupoitem;
	}

	public void setCdprodutosubgrupoitem(List<CdProdutoSubgrupo> cdprodutosubgrupoitem) {
		this.cdprodutosubgrupoitem = cdprodutosubgrupoitem;
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
		CdProdutoGrupo other = (CdProdutoGrupo) obj;
		return Objects.equals(id, other.id);
	}
}
