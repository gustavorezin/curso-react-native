package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "es_estext")
public class EsEstExt implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	@Digits(integer = 18, fraction = 6)
	private BigDecimal qtd = BigDecimal.ZERO;
	@Column(length = 120)
	private String descricao;
	@ManyToOne
	private CdProduto cdproduto;
	@ManyToOne
	private CdPessoa cdpessoavendedor;
	
	public EsEstExt() {
	}

	public EsEstExt(Long id, Date data, @Digits(integer = 18, fraction = 6) BigDecimal qtd, String descricao,
			CdProduto cdproduto, CdPessoa cdpessoavendedor) {
		super();
		this.id = id;
		this.data = data;
		this.qtd = qtd;
		this.descricao = descricao;
		this.cdproduto = cdproduto;
		this.cdpessoavendedor = cdpessoavendedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = CaracterUtil.remUpper(descricao);
	}

	public CdProduto getCdproduto() {
		return cdproduto;
	}

	public void setCdproduto(CdProduto cdproduto) {
		this.cdproduto = cdproduto;
	}

	public CdPessoa getCdpessoavendedor() {
		return cdpessoavendedor;
	}

	public void setCdpessoavendedor(CdPessoa cdpessoavendedor) {
		this.cdpessoavendedor = cdpessoavendedor;
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
		EsEstExt other = (EsEstExt) obj;
		return Objects.equals(id, other.id);
	}
}
