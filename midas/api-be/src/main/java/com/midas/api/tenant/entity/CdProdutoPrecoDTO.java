package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class CdProdutoPrecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private CdProduto cdproduto;
	private Date datacad;
	private Date dataat;
	private BigDecimal vvenda;

	public CdProdutoPrecoDTO() {
	}

	public CdProdutoPrecoDTO(Long id, CdProduto cdproduto, Date datacad, Date dataat, BigDecimal vvenda) {
		super();
		this.id = id;
		this.cdproduto = cdproduto;
		this.datacad = datacad;
		this.dataat = dataat;
		this.vvenda = vvenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CdProduto getCdproduto() {
		return cdproduto;
	}

	public void setCdproduto(CdProduto cdproduto) {
		this.cdproduto = cdproduto;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Date getDataat() {
		return dataat;
	}

	public void setDataat(Date dataat) {
		this.dataat = dataat;
	}

	public BigDecimal getVvenda() {
		return vvenda;
	}

	public void setVvenda(BigDecimal vvenda) {
		this.vvenda = vvenda;
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
		CdProdutoPrecoDTO other = (CdProdutoPrecoDTO) obj;
		return Objects.equals(id, other.id);
	}
}
