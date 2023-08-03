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
@Table(name = "cd_modulo")
public class CdModulo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 80)
	private String nome;
	@Column(length = 300)
	private String descricao;
	@Column(length = 2)
	private String tipo;
	@Digits(integer = 10, fraction = 2)
	private BigDecimal vadd = new BigDecimal(0);
	@Digits(integer = 10, fraction = 2)
	private BigDecimal vtot = new BigDecimal(0);

	public CdModulo() {
	}

	public CdModulo(Integer id, String nome, String descricao, String tipo,
			@Digits(integer = 10, fraction = 2) BigDecimal vadd, @Digits(integer = 10, fraction = 2) BigDecimal vtot) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.vadd = vadd;
		this.vtot = vtot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getVadd() {
		return vadd;
	}

	public void setVadd(BigDecimal vadd) {
		this.vadd = vadd;
	}

	public BigDecimal getVtot() {
		return vtot;
	}

	public void setVtot(BigDecimal vtot) {
		this.vtot = vtot;
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
		CdModulo other = (CdModulo) obj;
		return Objects.equals(id, other.id);
	}
}
