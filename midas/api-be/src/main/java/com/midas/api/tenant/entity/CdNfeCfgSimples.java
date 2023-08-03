package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "cd_nfecfg_simples")
public class CdNfeCfgSimples implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private CdPessoa cdpessoaemp;
	@Digits(integer = 5, fraction = 4)
	private BigDecimal aliq = new BigDecimal(0);

	public CdNfeCfgSimples() {
	}

	public CdNfeCfgSimples(Integer id, CdPessoa cdpessoaemp, @Digits(integer = 5, fraction = 4) BigDecimal aliq) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.aliq = aliq;
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

	public BigDecimal getAliq() {
		return aliq;
	}

	public void setAliq(BigDecimal aliq) {
		this.aliq = aliq;
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
		CdNfeCfgSimples other = (CdNfeCfgSimples) obj;
		return Objects.equals(id, other.id);
	}
}
