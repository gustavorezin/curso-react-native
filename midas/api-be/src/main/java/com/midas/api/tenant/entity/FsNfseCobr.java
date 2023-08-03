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
@Table(name = "fs_nfsecobr")
public class FsNfseCobr implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 60)
	private String nfat;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vorig = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vdesc = BigDecimal.ZERO;
	@Digits(integer = 15, fraction = 2)
	private BigDecimal vliq = BigDecimal.ZERO;
	@OneToMany(mappedBy = "fsnfsecobr", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfseCobrDup> fsnfsecobrdups = new ArrayList<FsNfseCobrDup>();

	public FsNfseCobr() {
	}

	public FsNfseCobr(Long id, String nfat, @Digits(integer = 15, fraction = 2) BigDecimal vorig,
			@Digits(integer = 15, fraction = 2) BigDecimal vdesc, @Digits(integer = 15, fraction = 2) BigDecimal vliq,
			List<FsNfseCobrDup> fsnfsecobrdups) {
		super();
		this.id = id;
		this.nfat = nfat;
		this.vorig = vorig;
		this.vdesc = vdesc;
		this.vliq = vliq;
		this.fsnfsecobrdups = fsnfsecobrdups;
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

	public List<FsNfseCobrDup> getFsnfsecobrdups() {
		return fsnfsecobrdups;
	}

	public void setFsnfsecobrdups(List<FsNfseCobrDup> fsnfsecobrdups) {
		this.fsnfsecobrdups = fsnfsecobrdups;
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
		FsNfseCobr other = (FsNfseCobr) obj;
		return Objects.equals(id, other.id);
	}
}
