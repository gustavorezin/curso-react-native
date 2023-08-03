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
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_nfefrete_vol")
public class FsNfeFreteVol implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	private FsNfeFrete fsnfefrete;
	@Digits(integer = 12, fraction = 2)
	private BigDecimal qvol = new BigDecimal(0);
	@Column(length = 60)
	private String esp = "";
	@Column(length = 60)
	private String marca = "";
	@Column(length = 60)
	private String nvol = "";
	@Digits(integer = 12, fraction = 3)
	private BigDecimal pesol = new BigDecimal(0);
	@Digits(integer = 12, fraction = 3)
	private BigDecimal pesob = new BigDecimal(0);

	public FsNfeFreteVol() {
	}

	public FsNfeFreteVol(Long id, FsNfeFrete fsnfefrete, @Digits(integer = 12, fraction = 2) BigDecimal qvol,
			String esp, String marca, String nvol, @Digits(integer = 12, fraction = 3) BigDecimal pesol,
			@Digits(integer = 12, fraction = 3) BigDecimal pesob) {
		super();
		this.id = id;
		this.fsnfefrete = fsnfefrete;
		this.qvol = qvol;
		this.esp = esp;
		this.marca = marca;
		this.nvol = nvol;
		this.pesol = pesol;
		this.pesob = pesob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsNfeFrete getFsnfefrete() {
		return fsnfefrete;
	}

	public void setFsnfefrete(FsNfeFrete fsnfefrete) {
		this.fsnfefrete = fsnfefrete;
	}

	public BigDecimal getQvol() {
		return qvol;
	}

	public void setQvol(BigDecimal qvol) {
		this.qvol = qvol;
	}

	public String getEsp() {
		return esp;
	}

	public void setEsp(String esp) {
		this.esp = CaracterUtil.remUpper(esp);
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = CaracterUtil.remUpper(marca);
	}

	public String getNvol() {
		return nvol;
	}

	public void setNvol(String nvol) {
		this.nvol = CaracterUtil.remUpper(nvol);
	}

	public BigDecimal getPesol() {
		return pesol;
	}

	public void setPesol(BigDecimal pesol) {
		this.pesol = pesol;
	}

	public BigDecimal getPesob() {
		return pesob;
	}

	public void setPesob(BigDecimal pesob) {
		this.pesob = pesob;
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
		FsNfeFreteVol other = (FsNfeFreteVol) obj;
		return Objects.equals(id, other.id);
	}
}
