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
@Table(name = "fs_nfsetot")
public class FsNfseTot implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long fsnfse_id;
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
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vinss = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vir = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vcofins = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vcsll = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vpis = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vservicos = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vdeducoes = BigDecimal.ZERO;
	@Digits(integer = 16, fraction = 4)
	private BigDecimal vliquido = BigDecimal.ZERO;
	
	public FsNfseTot() {
	}

	public FsNfseTot(Long id, Long fsnfse_id, @Digits(integer = 16, fraction = 4) BigDecimal vbc,
			@Digits(integer = 16, fraction = 4) BigDecimal vbcretido,
			@Digits(integer = 16, fraction = 4) BigDecimal vredbcretido,
			@Digits(integer = 16, fraction = 4) BigDecimal viss,
			@Digits(integer = 16, fraction = 4) BigDecimal vissretido,
			@Digits(integer = 16, fraction = 4) BigDecimal vinss, @Digits(integer = 16, fraction = 4) BigDecimal vir,
			@Digits(integer = 16, fraction = 4) BigDecimal vcofins,
			@Digits(integer = 16, fraction = 4) BigDecimal vcsll, @Digits(integer = 16, fraction = 4) BigDecimal vpis,
			@Digits(integer = 16, fraction = 4) BigDecimal vservicos,
			@Digits(integer = 16, fraction = 4) BigDecimal vdeducoes,
			@Digits(integer = 16, fraction = 4) BigDecimal vliquido) {
		super();
		this.id = id;
		this.fsnfse_id = fsnfse_id;
		this.vbc = vbc;
		this.vbcretido = vbcretido;
		this.vredbcretido = vredbcretido;
		this.viss = viss;
		this.vissretido = vissretido;
		this.vinss = vinss;
		this.vir = vir;
		this.vcofins = vcofins;
		this.vcsll = vcsll;
		this.vpis = vpis;
		this.vservicos = vservicos;
		this.vdeducoes = vdeducoes;
		this.vliquido = vliquido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFsnfse_id() {
		return fsnfse_id;
	}

	public void setFsnfse_id(Long fsnfse_id) {
		this.fsnfse_id = fsnfse_id;
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

	public BigDecimal getVinss() {
		return vinss;
	}

	public void setVinss(BigDecimal vinss) {
		this.vinss = vinss;
	}

	public BigDecimal getVir() {
		return vir;
	}

	public void setVir(BigDecimal vir) {
		this.vir = vir;
	}

	public BigDecimal getVcofins() {
		return vcofins;
	}

	public void setVcofins(BigDecimal vcofins) {
		this.vcofins = vcofins;
	}

	public BigDecimal getVcsll() {
		return vcsll;
	}

	public void setVcsll(BigDecimal vcsll) {
		this.vcsll = vcsll;
	}

	public BigDecimal getVpis() {
		return vpis;
	}

	public void setVpis(BigDecimal vpis) {
		this.vpis = vpis;
	}

	public BigDecimal getVservicos() {
		return vservicos;
	}

	public void setVservicos(BigDecimal vservicos) {
		this.vservicos = vservicos;
	}

	public BigDecimal getVdeducoes() {
		return vdeducoes;
	}

	public void setVdeducoes(BigDecimal vdeducoes) {
		this.vdeducoes = vdeducoes;
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
		FsNfseTot other = (FsNfseTot) obj;
		return Objects.equals(id, other.id);
	}
}
