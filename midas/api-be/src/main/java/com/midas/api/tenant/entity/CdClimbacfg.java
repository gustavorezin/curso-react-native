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
@Table(name = "cd_climbacfg")
public class CdClimbacfg implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@Column(length = 350)
	private String admin_link;
	@Column(length = 50)
	private String admin_user;
	@Column(length = 50)
	private String admin_senha;
	@Column(length = 50)
	private String loja_nome;
	@Column(length = 350)
	private String loja_link;
	@Column(length = 350)
	private String api_link;
	@Column(length = 350)
	private String api_token;
	@Column(length = 8)
	private String status;

	public CdClimbacfg() {
	}

	public CdClimbacfg(Integer id, CdPessoa cdpessoaemp, String admin_link, String admin_user, String admin_senha,
			String loja_nome, String loja_link, String api_link, String api_token, String status) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.admin_link = admin_link;
		this.admin_user = admin_user;
		this.admin_senha = admin_senha;
		this.loja_nome = loja_nome;
		this.loja_link = loja_link;
		this.api_link = api_link;
		this.api_token = api_token;
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

	public String getAdmin_link() {
		return admin_link;
	}

	public void setAdmin_link(String admin_link) {
		this.admin_link = admin_link;
	}

	public String getAdmin_user() {
		return admin_user;
	}

	public void setAdmin_user(String admin_user) {
		this.admin_user = admin_user;
	}

	public String getAdmin_senha() {
		return admin_senha;
	}

	public void setAdmin_senha(String admin_senha) {
		this.admin_senha = admin_senha;
	}

	public String getLoja_nome() {
		return loja_nome;
	}

	public void setLoja_nome(String loja_nome) {
		this.loja_nome = CaracterUtil.remUpper(loja_nome);
	}

	public String getLoja_link() {
		return loja_link;
	}

	public void setLoja_link(String loja_link) {
		this.loja_link = loja_link;
	}

	public String getApi_link() {
		return api_link;
	}

	public void setApi_link(String api_link) {
		this.api_link = api_link;
	}

	public String getApi_token() {
		return api_token;
	}

	public void setApi_token(String api_token) {
		this.api_token = api_token;
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
		CdClimbacfg other = (CdClimbacfg) obj;
		return Objects.equals(id, other.id);
	}
}
