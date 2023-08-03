package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_cteinf")
public class FsCteInf implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vcarga = new BigDecimal(0);
	@Column(length = 60)
	private String prodpred;
	@Column(length = 8)
	private String rntrc;
	@Column(length = 44)
	private String chcte_sub;
	@Column(length = 44)
	private String refnfe_sub;
	@Column(length = 44)
	private String refcte_sub;
	@Column(length = 44)
	private String refcteanu_sub;
	@Column(length = 44)
	private String chcte_comp;
	@Column(length = 44)
	private String chcte_anu;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date demi_anu = new Date();
	@OneToMany(mappedBy = "fscteinf", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsCteInfCarga> fscteinfcarga = new ArrayList<FsCteInfCarga>();
	@OneToMany(mappedBy = "fscteinf", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsCteInfNfe> fscteinfnfe = new ArrayList<FsCteInfNfe>();
	@OneToMany(mappedBy = "fscteinf", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsCteInfDocEmi> fscteinfdocemi = new ArrayList<FsCteInfDocEmi>();

	public FsCteInf() {
	}

	public FsCteInf(Long id, @Digits(integer = 13, fraction = 2) BigDecimal vcarga, String prodpred, String rntrc,
			String chcte_sub, String refnfe_sub, String refcte_sub, String refcteanu_sub, String chcte_comp,
			String chcte_anu, Date demi_anu, List<FsCteInfCarga> fscteinfcarga, List<FsCteInfNfe> fscteinfnfe,
			List<FsCteInfDocEmi> fscteinfdocemi) {
		super();
		this.id = id;
		this.vcarga = vcarga;
		this.prodpred = prodpred;
		this.rntrc = rntrc;
		this.chcte_sub = chcte_sub;
		this.refnfe_sub = refnfe_sub;
		this.refcte_sub = refcte_sub;
		this.refcteanu_sub = refcteanu_sub;
		this.chcte_comp = chcte_comp;
		this.chcte_anu = chcte_anu;
		this.demi_anu = demi_anu;
		this.fscteinfcarga = fscteinfcarga;
		this.fscteinfnfe = fscteinfnfe;
		this.fscteinfdocemi = fscteinfdocemi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getVcarga() {
		return vcarga;
	}

	public void setVcarga(BigDecimal vcarga) {
		this.vcarga = vcarga;
	}

	public String getProdpred() {
		return prodpred;
	}

	public void setProdpred(String prodpred) {
		this.prodpred = CaracterUtil.remUpper(prodpred);
	}

	public String getRntrc() {
		return rntrc;
	}

	public void setRntrc(String rntrc) {
		this.rntrc = rntrc;
	}

	public String getChcte_sub() {
		return chcte_sub;
	}

	public void setChcte_sub(String chcte_sub) {
		this.chcte_sub = chcte_sub;
	}

	public String getRefnfe_sub() {
		return refnfe_sub;
	}

	public void setRefnfe_sub(String refnfe_sub) {
		this.refnfe_sub = refnfe_sub;
	}

	public String getRefcte_sub() {
		return refcte_sub;
	}

	public void setRefcte_sub(String refcte_sub) {
		this.refcte_sub = refcte_sub;
	}

	public String getRefcteanu_sub() {
		return refcteanu_sub;
	}

	public void setRefcteanu_sub(String refcteanu_sub) {
		this.refcteanu_sub = refcteanu_sub;
	}

	public String getChcte_comp() {
		return chcte_comp;
	}

	public void setChcte_comp(String chcte_comp) {
		this.chcte_comp = chcte_comp;
	}

	public String getChcte_anu() {
		return chcte_anu;
	}

	public void setChcte_anu(String chcte_anu) {
		this.chcte_anu = chcte_anu;
	}

	public Date getDemi_anu() {
		return demi_anu;
	}

	public void setDemi_anu(Date demi_anu) {
		this.demi_anu = demi_anu;
	}

	public List<FsCteInfCarga> getFscteinfcarga() {
		return fscteinfcarga;
	}

	public void setFscteinfcarga(List<FsCteInfCarga> fscteinfcarga) {
		this.fscteinfcarga = fscteinfcarga;
	}

	public List<FsCteInfNfe> getFscteinfnfe() {
		return fscteinfnfe;
	}

	public void setFscteinfnfe(List<FsCteInfNfe> fscteinfnfe) {
		this.fscteinfnfe = fscteinfnfe;
	}

	public List<FsCteInfDocEmi> getFscteinfdocemi() {
		return fscteinfdocemi;
	}

	public void setFscteinfdocemi(List<FsCteInfDocEmi> fscteinfdocemi) {
		this.fscteinfdocemi = fscteinfdocemi;
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
		FsCteInf other = (FsCteInf) obj;
		return Objects.equals(id, other.id);
	}
}
