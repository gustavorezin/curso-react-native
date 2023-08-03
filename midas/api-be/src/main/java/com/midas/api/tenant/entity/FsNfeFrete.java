package com.midas.api.tenant.entity;

import java.io.Serializable;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_nfefrete")
public class FsNfeFrete implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 2)
	private String modfrete;
	@Column(length = 8)
	private String c_placa = "";
	@Column(length = 2)
	private String c_uf = "XX";
	@Column(length = 20)
	private String c_rntc = "";
	@OneToMany(mappedBy = "fsnfefrete", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsNfeFreteVol> fsnfefretevols = new ArrayList<FsNfeFreteVol>();

	public FsNfeFrete() {
	}

	public FsNfeFrete(Long id, String modfrete, String c_placa, String c_uf, String c_rntc,
			List<FsNfeFreteVol> fsnfefretevols) {
		super();
		this.id = id;
		this.modfrete = modfrete;
		this.c_placa = c_placa;
		this.c_uf = c_uf;
		this.c_rntc = c_rntc;
		this.fsnfefretevols = fsnfefretevols;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModfrete() {
		return modfrete;
	}

	public void setModfrete(String modfrete) {
		this.modfrete = modfrete;
	}

	public String getC_placa() {
		return c_placa;
	}

	public void setC_placa(String c_placa) {
		this.c_placa = CaracterUtil.remUpper(c_placa);
	}

	public String getC_uf() {
		return c_uf;
	}

	public void setC_uf(String c_uf) {
		this.c_uf = c_uf;
	}

	public String getC_rntc() {
		return c_rntc;
	}

	public void setC_rntc(String c_rntc) {
		this.c_rntc = c_rntc;
	}

	public List<FsNfeFreteVol> getFsnfefretevols() {
		return fsnfefretevols;
	}

	public void setFsnfefretevols(List<FsNfeFreteVol> fsnfefretevols) {
		this.fsnfefretevols = fsnfefretevols;
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
		FsNfeFrete other = (FsNfeFrete) obj;
		return Objects.equals(id, other.id);
	}
}
