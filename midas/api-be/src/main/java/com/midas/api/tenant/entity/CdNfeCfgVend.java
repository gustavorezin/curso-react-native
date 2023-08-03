package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cd_nfecfg_vend")
public class CdNfeCfgVend implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdnfecfgvenditems" }, allowSetters = true)
	@ManyToOne
	private CdPessoa cdpessoavend;
	private Integer cdnfecfg;
	private Long cdpessoaemp;

	public CdNfeCfgVend() {
	}

	public CdNfeCfgVend(Integer id, CdPessoa cdpessoavend, Integer cdnfecfg, Long cdpessoaemp) {
		super();
		this.id = id;
		this.cdpessoavend = cdpessoavend;
		this.cdnfecfg = cdnfecfg;
		this.cdpessoaemp = cdpessoaemp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPessoa getCdpessoavend() {
		return cdpessoavend;
	}

	public void setCdpessoavend(CdPessoa cdpessoavend) {
		this.cdpessoavend = cdpessoavend;
	}

	public Integer getCdnfecfg() {
		return cdnfecfg;
	}

	public void setCdnfecfg(Integer cdnfecfg) {
		this.cdnfecfg = cdnfecfg;
	}

	public Long getCdpessoaemp() {
		return cdpessoaemp;
	}

	public void setCdpessoaemp(Long cdpessoaemp) {
		this.cdpessoaemp = cdpessoaemp;
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
		CdNfeCfgVend other = (CdNfeCfgVend) obj;
		return Objects.equals(id, other.id);
	}
}
