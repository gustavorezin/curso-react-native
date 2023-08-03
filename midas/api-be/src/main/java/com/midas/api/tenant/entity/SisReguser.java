package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "sis_reguser")
public class SisReguser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long cdpessoaemp_id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date horacad = new Date();
	private Integer acesso;
	@Column(length = 120)
	private String descricao;
	private Integer role;
	private Long usuario_id;
	@Column(length = 80)
	private String usuario;
	@Column(length = 100)
	private String login;
	@Column(length = 1)
	private String tipo;
	@Column(length = 500)
	private String onde;

	public SisReguser() {
	}

	public SisReguser(Long id, Long cdpessoaemp_id, Date datacad, Date horacad, Integer acesso, String descricao,
			Integer role, Long usuario_id, String usuario, String login, String tipo, String onde) {
		super();
		this.id = id;
		this.cdpessoaemp_id = cdpessoaemp_id;
		this.datacad = datacad;
		this.horacad = horacad;
		this.acesso = acesso;
		this.descricao = descricao;
		this.role = role;
		this.usuario_id = usuario_id;
		this.usuario = usuario;
		this.login = login;
		this.tipo = tipo;
		this.onde = onde;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCdpessoaemp_id() {
		return cdpessoaemp_id;
	}

	public void setCdpessoaemp_id(Long cdpessoaemp_id) {
		this.cdpessoaemp_id = cdpessoaemp_id;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Date getHoracad() {
		return horacad;
	}

	public void setHoracad(Date horacad) {
		this.horacad = horacad;
	}

	public Integer getAcesso() {
		return acesso;
	}

	public void setAcesso(Integer acesso) {
		this.acesso = acesso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOnde() {
		return onde;
	}

	public void setOnde(String onde) {
		this.onde = onde;
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
		SisReguser other = (SisReguser) obj;
		return Objects.equals(id, other.id);
	}
}
