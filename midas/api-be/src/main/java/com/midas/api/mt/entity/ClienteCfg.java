package com.midas.api.mt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cliente_cfg")
public class ClienteCfg implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int itensporpagina;
	private String planofundo;
	private String cortema;
	private String modulo;
	@Column(length = 1)
	private String tamanhofonte = "5";

	public ClienteCfg() {
	}

	public ClienteCfg(Long id, int itensporpagina, String planofundo, String cortema, String modulo,
			String tamanhofonte) {
		super();
		this.id = id;
		this.itensporpagina = itensporpagina;
		this.planofundo = planofundo;
		this.cortema = cortema;
		this.modulo = modulo;
		this.tamanhofonte = tamanhofonte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getItensporpagina() {
		return itensporpagina;
	}

	public void setItensporpagina(int itensporpagina) {
		this.itensporpagina = itensporpagina;
	}

	public String getPlanofundo() {
		return planofundo;
	}

	public void setPlanofundo(String planofundo) {
		this.planofundo = planofundo;
	}

	public String getCortema() {
		return cortema;
	}

	public void setCortema(String cortema) {
		this.cortema = cortema;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = CaracterUtil.remUpper(modulo);
	}

	public String getTamanhofonte() {
		return tamanhofonte;
	}

	public void setTamanhofonte(String tamanhofonte) {
		this.tamanhofonte = tamanhofonte;
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
		ClienteCfg other = (ClienteCfg) obj;
		return Objects.equals(id, other.id);
	}
}
