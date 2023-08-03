package com.midas.api.mt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cd_codserv")
public class CdCodserv implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 12)
	private String cnae;
	@Column(length = 800)
	private String cnae_desc;
	@Column(length = 5)
	private String codserv;
	@Column(columnDefinition = "TEXT")
	private String codserv_desc;

	public CdCodserv() {
	}

	public CdCodserv(Integer id, String cnae, String cnae_desc, String codserv, String codserv_desc) {
		super();
		this.id = id;
		this.cnae = cnae;
		this.cnae_desc = cnae_desc;
		this.codserv = codserv;
		this.codserv_desc = codserv_desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getCnae_desc() {
		return cnae_desc;
	}

	public void setCnae_desc(String cnae_desc) {
		this.cnae_desc = cnae_desc;
	}

	public String getCodserv() {
		return codserv;
	}

	public void setCodserv(String codserv) {
		this.codserv = codserv;
	}

	public String getCodserv_desc() {
		return codserv_desc;
	}

	public void setCodserv_desc(String codserv_desc) {
		this.codserv_desc = codserv_desc;
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
		CdCodserv other = (CdCodserv) obj;
		return Objects.equals(id, other.id);
	}
}
