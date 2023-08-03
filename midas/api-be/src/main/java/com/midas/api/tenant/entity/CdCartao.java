package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_cartao")
public class CdCartao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@ManyToOne
	private CdCaixa cdcaixa;
	@Column(length = 2)
	private String tipo;
	@Column(length = 40)
	private String nome;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal taxa;
	private Integer dias;
	@Column(length = 8)
	private String status;

	public CdCartao() {
	}

	public CdCartao(Integer id, CdPessoa cdpessoaemp, CdCaixa cdcaixa, String tipo, String nome,
			@Digits(integer = 3, fraction = 2) BigDecimal taxa, Integer dias, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.cdcaixa = cdcaixa;
		this.tipo = tipo;
		this.nome = nome;
		this.taxa = taxa;
		this.dias = dias;
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

	public CdCaixa getCdcaixa() {
		return cdcaixa;
	}

	public void setCdcaixa(CdCaixa cdcaixa) {
		this.cdcaixa = cdcaixa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
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
		CdCartao other = (CdCartao) obj;
		return Objects.equals(id, other.id);
	}
}
