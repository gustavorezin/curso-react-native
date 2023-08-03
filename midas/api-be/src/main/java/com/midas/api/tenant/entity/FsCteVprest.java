package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "fs_ctevprest")
public class FsCteVprest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vtprest = new BigDecimal(0);
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vrec = new BigDecimal(0);

	public FsCteVprest() {
	}

	public FsCteVprest(Long id, @Digits(integer = 13, fraction = 2) BigDecimal vtprest,
			@Digits(integer = 13, fraction = 2) BigDecimal vrec) {
		super();
		this.id = id;
		this.vtprest = vtprest;
		this.vrec = vrec;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getVtprest() {
		return vtprest;
	}

	public void setVtprest(BigDecimal vtprest) {
		this.vtprest = vtprest;
	}

	public BigDecimal getVrec() {
		return vrec;
	}

	public void setVrec(BigDecimal vrec) {
		this.vrec = vrec;
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
		FsCteVprest other = (FsCteVprest) obj;
		return Objects.equals(id, other.id);
	}
}
