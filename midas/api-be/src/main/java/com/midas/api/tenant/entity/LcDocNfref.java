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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lc_docnfref")
public class LcDocNfref implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	@ManyToOne
	private LcDoc lcdoc;
	@Column(length = 44)
	private String refnf;

	public LcDocNfref() {
	}

	public LcDocNfref(Integer id, LcDoc lcdoc, String refnf) {
		super();
		this.id = id;
		this.lcdoc = lcdoc;
		this.refnf = refnf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LcDoc getLcdoc() {
		return lcdoc;
	}

	public void setLcdoc(LcDoc lcdoc) {
		this.lcdoc = lcdoc;
	}

	public String getRefnf() {
		return refnf;
	}

	public void setRefnf(String refnf) {
		this.refnf = refnf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LcDocNfref other = (LcDocNfref) obj;
		return Objects.equals(id, other.id);
	}
}
