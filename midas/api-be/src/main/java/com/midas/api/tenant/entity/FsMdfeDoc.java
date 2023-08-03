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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fs_mdfe_doc")
public class FsMdfeDoc implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = { "fsmdfedocitems" }, allowSetters = true)
	@ManyToOne
	private FsMdfe fsmdfe;
	@ManyToOne
	private CdCidade cdcidade;
	@Column(length = 44)
	private String chave;
	@Digits(integer = 21, fraction = 2)
	private BigDecimal valor = new BigDecimal(0);
	@Digits(integer = 21, fraction = 4)
	private BigDecimal peso = new BigDecimal(0);
	@Column(length = 1)
	private String tipo;

	public FsMdfeDoc() {
		super();
	}

	public FsMdfeDoc(Long id, FsMdfe fsmdfe, CdCidade cdcidade, String chave,
			@Digits(integer = 21, fraction = 2) BigDecimal valor, @Digits(integer = 21, fraction = 4) BigDecimal peso,
			String tipo) {
		super();
		this.id = id;
		this.fsmdfe = fsmdfe;
		this.cdcidade = cdcidade;
		this.chave = chave;
		this.valor = valor;
		this.peso = peso;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FsMdfe getFsmdfe() {
		return fsmdfe;
	}

	public void setFsmdfe(FsMdfe fsmdfe) {
		this.fsmdfe = fsmdfe;
	}

	public CdCidade getCdcidade() {
		return cdcidade;
	}

	public void setCdcidade(CdCidade cdcidade) {
		this.cdcidade = cdcidade;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		FsMdfeDoc other = (FsMdfeDoc) obj;
		return Objects.equals(id, other.id);
	}
}
