package com.midas.api.mt.entity;

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
@Table(name = "cliente_modulo")
public class ClienteModulo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "moduloclienteitem" }, allowSetters = true)
	@ManyToOne
	private Tenant tenant;
	@ManyToOne
	private CdModulo cdmodulo;

	public ClienteModulo() {
	}

	public ClienteModulo(Integer id, Tenant tenant, CdModulo cdmodulo) {
		super();
		this.id = id;
		this.tenant = tenant;
		this.cdmodulo = cdmodulo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public CdModulo getCdmodulo() {
		return cdmodulo;
	}

	public void setCdmodulo(CdModulo cdmodulo) {
		this.cdmodulo = cdmodulo;
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
		ClienteModulo other = (ClienteModulo) obj;
		return Objects.equals(id, other.id);
	}
}
