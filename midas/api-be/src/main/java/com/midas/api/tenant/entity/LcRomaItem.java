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
@Table(name = "lc_romaitem")
public class LcRomaItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "lcromaitem" }, allowSetters = true)
	@ManyToOne
	private LcRoma lcroma;
	@ManyToOne
	private LcDoc lcdoc;
	private Integer ordem = 0;

	public LcRomaItem() {
	}

	public LcRomaItem(Long id, LcRoma lcroma, LcDoc lcdoc, Integer ordem) {
		super();
		this.id = id;
		this.lcroma = lcroma;
		this.lcdoc = lcdoc;
		this.ordem = ordem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LcRoma getLcroma() {
		return lcroma;
	}

	public void setLcroma(LcRoma lcroma) {
		this.lcroma = lcroma;
	}

	public LcDoc getLcdoc() {
		return lcdoc;
	}

	public void setLcdoc(LcDoc lcdoc) {
		this.lcdoc = lcdoc;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
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
		LcRomaItem other = (LcRomaItem) obj;
		return Objects.equals(id, other.id);
	}
}
