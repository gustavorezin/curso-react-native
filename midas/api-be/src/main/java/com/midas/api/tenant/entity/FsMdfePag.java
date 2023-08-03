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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "fs_mdfepag")
public class FsMdfePag implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "fsmdfepagitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfe fsmdfe;
	@Column(length = 60)
	private String nome;
	@Column(length = 14)
	private String cpfcnpj;
	@Column(length = 20)
	private String idest;
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vcontrato;
	private Integer indpag;
	@Column(length = 5)
	private String banco;
	@Column(length = 10)
	private String agencia;
	@Column(length = 60)
	private String pix;
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vadiant;
	@JsonIgnoreProperties(value = { "fsmdfepag" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfepag", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	private List<FsMdfePagComp> fsmdfepagcompitems = new ArrayList<FsMdfePagComp>();
	@JsonIgnoreProperties(value = { "fsmdfepag" }, allowSetters = true)
	@OneToMany(mappedBy = "fsmdfepag", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("dvenc ASC")
	private List<FsMdfePagPrazo> fsmdfepagprazoitems = new ArrayList<FsMdfePagPrazo>();
	
	public FsMdfePag() {
	}

	public FsMdfePag(Integer id, FsMdfe fsmdfe, String nome, String cpfcnpj, String idest,
			@Digits(integer = 13, fraction = 2) BigDecimal vcontrato, Integer indpag, String banco, String agencia,
			String pix, @Digits(integer = 13, fraction = 2) BigDecimal vadiant, List<FsMdfePagComp> fsmdfepagcompitems,
			List<FsMdfePagPrazo> fsmdfepagprazoitems) {
		super();
		this.id = id;
		this.fsmdfe = fsmdfe;
		this.nome = nome;
		this.cpfcnpj = cpfcnpj;
		this.idest = idest;
		this.vcontrato = vcontrato;
		this.indpag = indpag;
		this.banco = banco;
		this.agencia = agencia;
		this.pix = pix;
		this.vadiant = vadiant;
		this.fsmdfepagcompitems = fsmdfepagcompitems;
		this.fsmdfepagprazoitems = fsmdfepagprazoitems;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FsMdfe getFsmdfe() {
		return fsmdfe;
	}

	public void setFsmdfe(FsMdfe fsmdfe) {
		this.fsmdfe = fsmdfe;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getIdest() {
		return idest;
	}

	public void setIdest(String idest) {
		this.idest = idest;
	}

	public BigDecimal getVcontrato() {
		return vcontrato;
	}

	public void setVcontrato(BigDecimal vcontrato) {
		this.vcontrato = vcontrato;
	}

	public Integer getIndpag() {
		return indpag;
	}

	public void setIndpag(Integer indpag) {
		this.indpag = indpag;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getPix() {
		return pix;
	}

	public void setPix(String pix) {
		this.pix = pix;
	}

	public BigDecimal getVadiant() {
		return vadiant;
	}

	public void setVadiant(BigDecimal vadiant) {
		this.vadiant = vadiant;
	}

	public List<FsMdfePagComp> getFsmdfepagcompitems() {
		return fsmdfepagcompitems;
	}

	public void setFsmdfepagcompitems(List<FsMdfePagComp> fsmdfepagcompitems) {
		this.fsmdfepagcompitems = fsmdfepagcompitems;
	}

	public List<FsMdfePagPrazo> getFsmdfepagprazoitems() {
		return fsmdfepagprazoitems;
	}

	public void setFsmdfepagprazoitems(List<FsMdfePagPrazo> fsmdfepagprazoitems) {
		this.fsmdfepagprazoitems = fsmdfepagprazoitems;
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
		FsMdfePag other = (FsMdfePag) obj;
		return Objects.equals(id, other.id);
	}
}
