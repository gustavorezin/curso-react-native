package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fs_nfepag")
public class FsNfePag implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsNfe fsnfe;
	@Column(length = 2)
	private String tpag;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vpag = new BigDecimal(0);

	public FsNfePag() {
	}

	public FsNfePag(Long id, FsNfe fsnfe, String tpag, @Digits(integer = 15, fraction = 2) BigDecimal vpag) {
		super();
		this.id = id;
		this.fsnfe = fsnfe;
		this.tpag = tpag;
		this.vpag = vpag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsNfe getFsnfe() {
		return fsnfe;
	}

	public void setFsnfe(FsNfe fsnfe) {
		this.fsnfe = fsnfe;
	}

	public String getTpag() {
		return tpag;
	}

	public void setTpag(String tpag) {
		this.tpag = tpag;
	}

	public BigDecimal getVpag() {
		return vpag;
	}

	public void setVpag(BigDecimal vpag) {
		this.vpag = vpag;
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
		FsNfePag other = (FsNfePag) obj;
		return Objects.equals(id, other.id);
	}
}
