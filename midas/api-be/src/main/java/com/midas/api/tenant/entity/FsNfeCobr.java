package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "fs_nfecobr")
public class FsNfeCobr implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 60)
	private String nfat;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vorig = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vdesc = new BigDecimal(0);
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vliq = new BigDecimal(0);
	@OneToMany(mappedBy = "fsnfecobr", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfeCobrDup> fsnfecobrdups = new ArrayList<FsNfeCobrDup>();

	public FsNfeCobr() {
	}

	public FsNfeCobr(Long id, String nfat, @Digits(integer = 15, fraction = 2) BigDecimal vorig,
			@Digits(integer = 15, fraction = 2) BigDecimal vdesc, @Digits(integer = 15, fraction = 2) BigDecimal vliq,
			List<FsNfeCobrDup> fsnfecobrdups) {
		super();
		this.id = id;
		this.nfat = nfat;
		this.vorig = vorig;
		this.vdesc = vdesc;
		this.vliq = vliq;
		this.fsnfecobrdups = fsnfecobrdups;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNfat() {
		return nfat;
	}

	public void setNfat(String nfat) {
		this.nfat = nfat;
	}

	public BigDecimal getVorig() {
		return vorig;
	}

	public void setVorig(BigDecimal vorig) {
		this.vorig = vorig;
	}

	public BigDecimal getVdesc() {
		return vdesc;
	}

	public void setVdesc(BigDecimal vdesc) {
		this.vdesc = vdesc;
	}

	public BigDecimal getVliq() {
		return vliq;
	}

	public void setVliq(BigDecimal vliq) {
		this.vliq = vliq;
	}

	public List<FsNfeCobrDup> getFsnfecobrdups() {
		return fsnfecobrdups;
	}

	public void setFsnfecobrdups(List<FsNfeCobrDup> fsnfecobrdups) {
		this.fsnfecobrdups = fsnfecobrdups;
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
		FsNfeCobr other = (FsNfeCobr) obj;
		return Objects.equals(id, other.id);
	}
}
