package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sis_acesso")
public class SisAcesso implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer role;
	private Integer acesso;
	@Column(length = 120)
	private String nome;
	@Column(length = 2)
	private String local;

	public SisAcesso() {
	}

	public SisAcesso(Long id, Integer role, Integer acesso, String nome, String local) {
		super();
		this.id = id;
		this.role = role;
		this.acesso = acesso;
		this.nome = nome;
		this.local = local;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getAcesso() {
		return acesso;
	}

	public void setAcesso(Integer acesso) {
		this.acesso = acesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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
		SisAcesso other = (SisAcesso) obj;
		return Objects.equals(id, other.id);
	}
}
