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
@Table(name = "cd_produto_ns")
public class CdProdutoNs implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 5)
	private String pum = "";
	@Column(length = 5)
	private String pdois = "";
	private Integer contador = 0;
	@Column(length = 8)
	private String status;

	public CdProdutoNs() {
	}

	public CdProdutoNs(Integer id, CdPessoa cdpessoaemp, String pum, String pdois, Integer contador, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.pum = pum;
		this.pdois = pdois;
		this.contador = contador;
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

	public String getPum() {
		return pum;
	}

	public void setPum(String pum) {
		this.pum = CaracterUtil.remUpper(pum);
	}

	public String getPdois() {
		return pdois;
	}

	public void setPdois(String pdois) {
		this.pdois = CaracterUtil.remUpper(pdois);
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
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
		CdProdutoNs other = (CdProdutoNs) obj;
		return Objects.equals(id, other.id);
	}
}
