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
@Table(name = "fs_nfseitem_trib")
public class FsNfseItemTrib implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Digits(integer = 8, fraction = 4)
	private BigDecimal valiqiss = BigDecimal.ZERO;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal valiqissret = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vbc = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vbcretido = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vredbcretido = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal viss = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vissretido = BigDecimal.ZERO;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal valiqinss = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vinss = BigDecimal.ZERO;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal valiqir = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vir = BigDecimal.ZERO;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal valiqcofins = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vcofins = BigDecimal.ZERO;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal valiqcsll = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vcsll = BigDecimal.ZERO;
	@Digits(integer = 6, fraction = 4)
	private BigDecimal valiqpis = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vpis = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vliquido = BigDecimal.ZERO;

	public FsNfseItemTrib() {
	}

	public FsNfseItemTrib(Long id, 
			@Digits(integer = 8, fraction = 4) BigDecimal valiqiss,
			@Digits(integer = 6, fraction = 4) BigDecimal valiqissret,
			@Digits(integer = 16, fraction = 4) BigDecimal vbc,
			@Digits(integer = 16, fraction = 4) BigDecimal vbcretido,
			@Digits(integer = 16, fraction = 4) BigDecimal vredbcretido,
			@Digits(integer = 16, fraction = 4) BigDecimal viss,
			@Digits(integer = 16, fraction = 4) BigDecimal vissretido,
			@Digits(integer = 6, fraction = 4) BigDecimal valiqinss,
			@Digits(integer = 16, fraction = 4) BigDecimal vinss, @Digits(integer = 6, fraction = 4) BigDecimal valiqir,
			@Digits(integer = 16, fraction = 4) BigDecimal vir,
			@Digits(integer = 6, fraction = 4) BigDecimal valiqcofins,
			@Digits(integer = 16, fraction = 4) BigDecimal vcofins,
			@Digits(integer = 6, fraction = 4) BigDecimal valiqcsll,
			@Digits(integer = 16, fraction = 4) BigDecimal vcsll,
			@Digits(integer = 6, fraction = 4) BigDecimal valiqpis, @Digits(integer = 16, fraction = 4) BigDecimal vpis,
			@Digits(integer = 16, fraction = 4) BigDecimal vliquido) {
		super();
		this.id = id;
		this.valiqiss = valiqiss;
		this.valiqissret = valiqissret;
		this.vbc = vbc;
		this.vbcretido = vbcretido;
		this.vredbcretido = vredbcretido;
		this.viss = viss;
		this.vissretido = vissretido;
		this.valiqinss = valiqinss;
		this.vinss = vinss;
		this.valiqir = valiqir;
		this.vir = vir;
		this.valiqcofins = valiqcofins;
		this.vcofins = vcofins;
		this.valiqcsll = valiqcsll;
		this.vcsll = vcsll;
		this.valiqpis = valiqpis;
		this.vpis = vpis;
		this.vliquido = vliquido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValiqiss() {
		return valiqiss;
	}

	public void setValiqiss(BigDecimal valiqiss) {
		this.valiqiss = valiqiss;
	}

	public BigDecimal getValiqissret() {
		return valiqissret;
	}

	public void setValiqissret(BigDecimal valiqissret) {
		this.valiqissret = valiqissret;
	}

	public BigDecimal getVbc() {
		return vbc;
	}

	public void setVbc(BigDecimal vbc) {
		this.vbc = vbc;
	}

	public BigDecimal getVbcretido() {
		return vbcretido;
	}

	public void setVbcretido(BigDecimal vbcretido) {
		this.vbcretido = vbcretido;
	}

	public BigDecimal getVredbcretido() {
		return vredbcretido;
	}

	public void setVredbcretido(BigDecimal vredbcretido) {
		this.vredbcretido = vredbcretido;
	}

	public BigDecimal getViss() {
		return viss;
	}

	public void setViss(BigDecimal viss) {
		this.viss = viss;
	}

	public BigDecimal getVissretido() {
		return vissretido;
	}

	public void setVissretido(BigDecimal vissretido) {
		this.vissretido = vissretido;
	}

	public BigDecimal getValiqinss() {
		return valiqinss;
	}

	public void setValiqinss(BigDecimal valiqinss) {
		this.valiqinss = valiqinss;
	}

	public BigDecimal getVinss() {
		return vinss;
	}

	public void setVinss(BigDecimal vinss) {
		this.vinss = vinss;
	}

	public BigDecimal getValiqir() {
		return valiqir;
	}

	public void setValiqir(BigDecimal valiqir) {
		this.valiqir = valiqir;
	}

	public BigDecimal getVir() {
		return vir;
	}

	public void setVir(BigDecimal vir) {
		this.vir = vir;
	}

	public BigDecimal getValiqcofins() {
		return valiqcofins;
	}

	public void setValiqcofins(BigDecimal valiqcofins) {
		this.valiqcofins = valiqcofins;
	}

	public BigDecimal getVcofins() {
		return vcofins;
	}

	public void setVcofins(BigDecimal vcofins) {
		this.vcofins = vcofins;
	}

	public BigDecimal getValiqcsll() {
		return valiqcsll;
	}

	public void setValiqcsll(BigDecimal valiqcsll) {
		this.valiqcsll = valiqcsll;
	}

	public BigDecimal getVcsll() {
		return vcsll;
	}

	public void setVcsll(BigDecimal vcsll) {
		this.vcsll = vcsll;
	}

	public BigDecimal getValiqpis() {
		return valiqpis;
	}

	public void setValiqpis(BigDecimal valiqpis) {
		this.valiqpis = valiqpis;
	}

	public BigDecimal getVpis() {
		return vpis;
	}

	public void setVpis(BigDecimal vpis) {
		this.vpis = vpis;
	}

	public BigDecimal getVliquido() {
		return vliquido;
	}

	public void setVliquido(BigDecimal vliquido) {
		this.vliquido = vliquido;
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
		FsNfseItemTrib other = (FsNfseItemTrib) obj;
		return Objects.equals(id, other.id);
	}
}
