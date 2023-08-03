package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_produto_tab")
public class CdProdutoTab implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdProdutoMarkup cdprodutomarkup;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@Column(length = 80)
	private String nome;
	@Column(length = 2)
	private String ufaplic;
	@Column(length = 5000)
	private String info;
	@Column(length = 1)
	private String usa_pdv = "N";
	@Column(length = 8)
	private String status_loja = "INATIVO";
	@Column(length = 8)
	private String status;
	/*
	 * 
	 * Desativado Daniel - 07-06-2023
	 * 
	 * @JsonIgnoreProperties(value = { "cdprodutotab" }, allowSetters = true)
	 * 
	 * @OneToMany(mappedBy = "cdprodutotab", orphanRemoval = true, fetch =
	 * FetchType.EAGER)
	 * 
	 * @Fetch(value = FetchMode.SUBSELECT)
	 * 
	 * @OrderBy("id ASC") private List<CdProdutoPreco> cdprodutotabprecositem = new
	 * ArrayList<CdProdutoPreco>();
	 * 
	 * 
	 */

	public CdProdutoTab() {
	}

	public CdProdutoTab(Integer id, CdPessoa cdpessoaemp, CdProdutoMarkup cdprodutomarkup, Date datacad, String nome,
			String ufaplic, String info, String usa_pdv, String status_loja, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdprodutomarkup = cdprodutomarkup;
		this.datacad = datacad;
		this.nome = nome;
		this.ufaplic = ufaplic;
		this.info = info;
		this.usa_pdv = usa_pdv;
		this.status_loja = status_loja;
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

	public CdProdutoMarkup getCdprodutomarkup() {
		return cdprodutomarkup;
	}

	public void setCdprodutomarkup(CdProdutoMarkup cdprodutomarkup) {
		this.cdprodutomarkup = cdprodutomarkup;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getUfaplic() {
		return ufaplic;
	}

	public void setUfaplic(String ufaplic) {
		this.ufaplic = ufaplic;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = CaracterUtil.remUpper(info);
	}

	public String getUsa_pdv() {
		return usa_pdv;
	}

	public void setUsa_pdv(String usa_pdv) {
		this.usa_pdv = usa_pdv;
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
		CdProdutoTab other = (CdProdutoTab) obj;
		return Objects.equals(id, other.id);
	}
}
