package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cd_produto_cor_rel")
public class CdProdutoCorRel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long cdproduto_id;
	private Integer cdprodutocor_id = 0;

	public CdProdutoCorRel() {
	}

	public CdProdutoCorRel(Long id, Long cdproduto_id, Integer cdprodutocor_id) {
		super();
		this.id = id;
		this.cdproduto_id = cdproduto_id;
		this.cdprodutocor_id = cdprodutocor_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCdproduto_id() {
		return cdproduto_id;
	}

	public void setCdproduto_id(Long cdproduto_id) {
		this.cdproduto_id = cdproduto_id;
	}

	public Integer getCdprodutocor_id() {
		return cdprodutocor_id;
	}

	public void setCdprodutocor_id(Integer cdprodutocor_id) {
		this.cdprodutocor_id = cdprodutocor_id;
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
		CdProdutoCorRel other = (CdProdutoCorRel) obj;
		return Objects.equals(id, other.id);
	}
}
