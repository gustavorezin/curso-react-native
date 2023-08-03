package com.midas.api.tenant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.midas.api.util.CaracterUtil;

@Entity
@Table(name = "cd_plcon_micro")
public class CdPlconMicro implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnoreProperties(value = { "cdplconmicro" }, allowSetters = true)
	@ManyToOne
	private CdPlconMacro cdplconmacro;
	private Integer masc;
	@Column(length = 60)
	private String nome;
	@Column(length = 7)
	private String cor;
	@Column(length = 600)
	private String explica;
	@Column(length = 1)
	private String tipo;
	@Column(length = 1)
	private String exibe;
	@Column(length = 12)
	private String mascfinal;
	@Column(length = 2)
	private String tipolocal = "00";
	@Column(length = 1)
	private String exibedocprod = "N";

	public CdPlconMicro() {
	}

	public CdPlconMicro(Integer id, CdPlconMacro cdplconmacro, Integer masc, String nome, String cor, String explica,
			String tipo, String exibe, String mascfinal, String tipolocal, String exibedocprod) {
		super();
		this.id = id;
		this.cdplconmacro = cdplconmacro;
		this.masc = masc;
		this.nome = nome;
		this.cor = cor;
		this.explica = explica;
		this.tipo = tipo;
		this.exibe = exibe;
		this.mascfinal = mascfinal;
		this.tipolocal = tipolocal;
		this.exibedocprod = exibedocprod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CdPlconMacro getCdplconmacro() {
		return cdplconmacro;
	}

	public void setCdplconmacro(CdPlconMacro cdplconmacro) {
		this.cdplconmacro = cdplconmacro;
	}

	public Integer getMasc() {
		return masc;
	}

	public void setMasc(Integer masc) {
		this.masc = masc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaracterUtil.remUpper(nome);
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getExplica() {
		return explica;
	}

	public void setExplica(String explica) {
		this.explica = CaracterUtil.remUpper(explica);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getExibe() {
		return exibe;
	}

	public void setExibe(String exibe) {
		this.exibe = exibe;
	}

	public String getMascfinal() {
		return mascfinal;
	}

	public void setMascfinal(String mascfinal) {
		this.mascfinal = mascfinal;
	}

	public String getTipolocal() {
		return tipolocal;
	}

	public void setTipolocal(String tipolocal) {
		this.tipolocal = tipolocal;
	}

	public String getExibedocprod() {
		return exibedocprod;
	}

	public void setExibedocprod(String exibedocprod) {
		this.exibedocprod = exibedocprod;
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
		CdPlconMicro other = (CdPlconMicro) obj;
		return Objects.equals(id, other.id);
	}
}
