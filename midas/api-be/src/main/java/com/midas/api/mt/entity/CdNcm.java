package com.midas.api.mt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "cd_ncm")
public class CdNcm implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 2)
	private String classe;
	@Column(columnDefinition = "TEXT")
	private String classe_desc;
	@Column(length = 4)
	private String subclasse;
	@Column(columnDefinition = "TEXT")
	private String subclasse_desc;
	@Column(length = 10)
	private String ncm;
	@Column(length = 9)
	private String cest;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	@Digits(integer = 5, fraction = 2)
	private BigDecimal ibpt_nacional = new BigDecimal(0);
	@Digits(integer = 5, fraction = 2)
	private BigDecimal ibpt_importado = new BigDecimal(0);
	@Digits(integer = 5, fraction = 2)
	private BigDecimal ibpt_estadual = new BigDecimal(0);
	@Digits(integer = 5, fraction = 2)
	private BigDecimal ibpt_municipal = new BigDecimal(0);

	public CdNcm() {
	}

	public CdNcm(Integer id, String classe, String classe_desc, String subclasse, String subclasse_desc, String ncm,
			String cest, String descricao, @Digits(integer = 5, fraction = 2) BigDecimal ibpt_nacional,
			@Digits(integer = 5, fraction = 2) BigDecimal ibpt_importado,
			@Digits(integer = 5, fraction = 2) BigDecimal ibpt_estadual,
			@Digits(integer = 5, fraction = 2) BigDecimal ibpt_municipal) {
		super();
		this.id = id;
		this.classe = classe;
		this.classe_desc = classe_desc;
		this.subclasse = subclasse;
		this.subclasse_desc = subclasse_desc;
		this.ncm = ncm;
		this.cest = cest;
		this.descricao = descricao;
		this.ibpt_nacional = ibpt_nacional;
		this.ibpt_importado = ibpt_importado;
		this.ibpt_estadual = ibpt_estadual;
		this.ibpt_municipal = ibpt_municipal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getClasse_desc() {
		return classe_desc;
	}

	public void setClasse_desc(String classe_desc) {
		this.classe_desc = classe_desc;
	}

	public String getSubclasse() {
		return subclasse;
	}

	public void setSubclasse(String subclasse) {
		this.subclasse = subclasse;
	}

	public String getSubclasse_desc() {
		return subclasse_desc;
	}

	public void setSubclasse_desc(String subclasse_desc) {
		this.subclasse_desc = subclasse_desc;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getCest() {
		return cest;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getIbpt_nacional() {
		return ibpt_nacional;
	}

	public void setIbpt_nacional(BigDecimal ibpt_nacional) {
		this.ibpt_nacional = ibpt_nacional;
	}

	public BigDecimal getIbpt_importado() {
		return ibpt_importado;
	}

	public void setIbpt_importado(BigDecimal ibpt_importado) {
		this.ibpt_importado = ibpt_importado;
	}

	public BigDecimal getIbpt_estadual() {
		return ibpt_estadual;
	}

	public void setIbpt_estadual(BigDecimal ibpt_estadual) {
		this.ibpt_estadual = ibpt_estadual;
	}

	public BigDecimal getIbpt_municipal() {
		return ibpt_municipal;
	}

	public void setIbpt_municipal(BigDecimal ibpt_municipal) {
		this.ibpt_municipal = ibpt_municipal;
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
		CdNcm other = (CdNcm) obj;
		return Objects.equals(id, other.id);
	}
}
