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
@Table(name = "cd_email")
public class CdEmail implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private CdPessoa cdpessoaemp;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataat = new Date();
	@Column(length = 100)
	private String nome;
	@Column(length = 100)
	private String email;
	@Column(length = 100)
	private String login;
	@Column(length = 40)
	private String senha;
	@Column(length = 60)
	private String smtp;
	@Column(length = 5)
	private String portasmtp;
	@Column(length = 1)
	private String sslsmtp = "N";
	@Column(length = 1)
	private String requerauth = "N";
	@Column(columnDefinition = "TEXT")
	private String assinatura;
	@Column(length = 2)
	private String localenvio;
	@Column(length = 20)
	private String tipo;

	public CdEmail() {
	}

	public CdEmail(Integer id, CdPessoa cdpessoaemp, Date datacad, Date dataat, String nome, String email, String login,
			String senha, String smtp, String portasmtp, String sslsmtp, String requerauth, String assinatura,
			String localenvio, String tipo) {
		super();
		this.id = id;
		this.cdpessoaemp = cdpessoaemp;
		this.datacad = datacad;
		this.dataat = dataat;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.smtp = smtp;
		this.portasmtp = portasmtp;
		this.sslsmtp = sslsmtp;
		this.requerauth = requerauth;
		this.assinatura = assinatura;
		this.localenvio = localenvio;
		this.tipo = tipo;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getPortasmtp() {
		return portasmtp;
	}

	public void setPortasmtp(String portasmtp) {
		this.portasmtp = portasmtp;
	}

	public String getSslsmtp() {
		return sslsmtp;
	}

	public void setSslsmtp(String sslsmtp) {
		this.sslsmtp = sslsmtp;
	}

	public String getRequerauth() {
		return requerauth;
	}

	public void setRequerauth(String requerauth) {
		this.requerauth = requerauth;
	}

	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}

	public String getLocalenvio() {
		return localenvio;
	}

	public void setLocalenvio(String localenvio) {
		this.localenvio = localenvio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		CdEmail other = (CdEmail) obj;
		return Objects.equals(id, other.id);
	}
}
